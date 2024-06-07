package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.dto.payload.response.MessageResponse;
import com.chatopbackend.chatopbackend.dto.payload.response.RentalListResponse;
import com.chatopbackend.chatopbackend.mapping.RentalMapping;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        return new RentalDto(rental);
    }

    @PutMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editRentalById(@PathVariable Integer id, @RequestBody Rental request) {
        Rental rental = rentalService.getRentalById(id);
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found with ID: " + id);
        }

        if (!request.getName().isEmpty()) {
            rental.setName(request.getName());
        }
        if (!Objects.equals(request.getSurface(), rental.getSurface())) {
            rental.setSurface(request.getSurface());
        }
        if (!Objects.equals(request.getPrice(), rental.getPrice())) {
            rental.setPrice(request.getPrice());
        }
        if (!request.getDescription().isEmpty()) {
            rental.setDescription(request.getDescription());
        }
        // update rental
        return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }

    //TODO a mettre dans un utils
    public static String generateStringFromDate(String ext){
        return new SimpleDateFormat("yyyyMMddhhmmss'."+ext+"'").format(new Date());
    }

    //TODO a mettre dans un utils
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
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
        String fName = generateStringFromDate(getExtensionByStringHandling(fileName).orElse(null));

        Rental createdRental = rentalService.createRental(name, Float.parseFloat(surface), Float.parseFloat(price), fName, description, owner);

        String uploadDir = dirName + "/" + createdRental.getId();
        FileUploadUtil.saveFile(uploadDir, fName, picture);

        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }

    private RentalListResponse convertListToDto(List<Rental> rentals) {
        return new RentalListResponse(rentals.stream().map(rentalMapping::mapRentalToRentalDto).collect(Collectors.toList()));
    }
}
