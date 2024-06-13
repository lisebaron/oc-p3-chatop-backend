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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<Rental> rentals = rentalService.getAllRentals();
        return convertListToDto(rentals);
    }

    @GetMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto getRentalById(@PathVariable Integer id) {
        Rental rental = rentalService.getRentalById(id);
        return rentalMapping.mapRentalToRentalDto(rental);
    }

    @PutMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editRentalById(@PathVariable Integer id, @RequestPart("name") String name,
                                            @RequestPart("surface") String surface,
                                            @RequestPart("price") String price,
                                            @RequestPart("description") String description) {
        Rental rental = rentalService.getRentalById(id);
        RentalDto rentalDto = new RentalDto();
        rentalDto.setName(name);
        rentalDto.setSurface(Float.parseFloat(surface));
        rentalDto.setPrice(Float.parseFloat(price));
        rentalDto.setDescription(description);

        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found with ID: " + id);
        }

        if (!rentalDto.getName().isEmpty()) {
            rental.setName(rentalDto.getName());
        }
        if (!Objects.equals(rentalDto.getSurface(), rental.getSurface())) {
            rental.setSurface(rentalDto.getSurface());
        }
        if (!Objects.equals(rentalDto.getPrice(), rental.getPrice())) {
            rental.setPrice(rentalDto.getPrice());
        }
        if (!rentalDto.getDescription().isEmpty()) {
            rental.setDescription(rentalDto.getDescription());
        }
        // update rental
        return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }

    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRental(Principal principal, @RequestPart("name") String name,
                                          @RequestPart("surface") String surface,
                                          @RequestPart("price") String price,
                                          @RequestPart("picture") MultipartFile picture,
                                          @RequestPart("description") String description) throws IOException {
        User owner = userService.getUserByEmail(principal.getName()).orElse(null);

        String fileName =  StringUtils.cleanPath(picture.getOriginalFilename());
        String fName = DateUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(fileName).orElse(null));

        Rental createdRental = rentalService.createRental(name, Float.parseFloat(surface), Float.parseFloat(price), fName, description, owner);

        String uploadDir = dirName + "/" + createdRental.getId();
        FileUploadUtil.saveFile(uploadDir, fName, picture);

        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }

    private RentalListResponse convertListToDto(List<Rental> rentals) {
        return new RentalListResponse(rentals.stream().map(rentalMapping::mapRentalToRentalDto).collect(Collectors.toList()));
    }
}
