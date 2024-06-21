package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.dto.payload.response.MessageResponse;
import com.chatopbackend.chatopbackend.dto.payload.response.RentalListResponse;
import com.chatopbackend.chatopbackend.mapping.RentalMapping;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import com.chatopbackend.chatopbackend.utils.DateUtils;
import com.chatopbackend.chatopbackend.utils.FileUploadUtil;
import com.chatopbackend.chatopbackend.utils.FileUtils;
import com.chatopbackend.chatopbackend.utils.Utils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class RentalController {

    @Value("${file.upload.dir}")
    private String dirName;

    private final RentalService rentalService;
    private final UserService userService;

    private final RentalMapping rentalMapping;

    public RentalController(RentalService rentalService, UserService userService, RentalMapping rentalMapping) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.rentalMapping = rentalMapping;
    }

    @GetMapping("/rentals")
    @ResponseStatus(HttpStatus.OK)
    public RentalListResponse getAllRentals() {
        return Optional.ofNullable(rentalService.getAllRentals())
            .map(rentalMapping::convertListToDto)
            .orElse(new RentalListResponse());
    }

    @GetMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto getRentalById(@PathVariable Integer id) {
        return Optional.ofNullable(rentalService.getRentalById(id)).map(rentalMapping::mapRentalToRentalDto).orElse(new RentalDto());
    }

    @PutMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editRentalById(@PathVariable Integer id, @RequestPart("name") String name,
                                            @RequestPart("surface") String surface,
                                            @RequestPart("price") String price,
                                            @RequestPart("description") String description) {
        final RentalDto rentalDto = RentalDto.builder()
                .id(id)
                .name(name)
                .surface(Utils.convertToNumeric(surface))
                .price(Utils.convertToNumeric(price))
                .description(description)
                .build();

        rentalService.updateRental(id, rentalDto);
        
        return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }


    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRental(Principal principal, @RequestPart("name") String name,
                                          @RequestPart("surface") String surface,
                                          @RequestPart("price") String price,
                                          @RequestPart("picture") MultipartFile picture,
                                          @RequestPart("description") String description) throws IOException {
       final User owner = userService.getUserByEmail(principal.getName()).orElse(null);

        //revoir cette ligne
        final String fileName =  StringUtils.cleanPath(picture.getOriginalFilename());
        final String fName = DateUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(fileName).orElse(null));

        if (!Utils.isNumeric(surface)){
            // Lancer une exception
        }
        /**
         * Float.parseFloat(surface) => mettre cela dans un utils
         */
        final Rental createdRental = rentalService.createRental(name, Utils.convertToNumeric(surface), Utils.convertToNumeric(price), fName, description, owner);

        final String uploadDir = dirName + "/" + createdRental.getId();
        FileUploadUtil.saveFile(uploadDir, fName, picture);

        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }
}
