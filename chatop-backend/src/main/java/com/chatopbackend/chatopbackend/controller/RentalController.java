package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.dto.payload.response.MessageResponse;
import com.chatopbackend.chatopbackend.dto.payload.response.RentalListResponse;
import com.chatopbackend.chatopbackend.exception.RentalNotFoundException;
import com.chatopbackend.chatopbackend.exception.StringNotNumericException;
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
import java.util.Objects;
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

    /**
     * Retrieves all rentals.
     *
     * @return RentalListResponse containing a list of all rentals.
     * @throws RentalNotFoundException if no rentals are found.
     */
    @GetMapping("/rentals")
    @ResponseStatus(HttpStatus.OK)
    public RentalListResponse getAllRentals() {
        return Optional.ofNullable(rentalService.getAllRentals())
            .map(rentalMapping::convertListToDto)
            .orElse(new RentalListResponse());
    }

    /**
     * Retrieves a rental by its ID.
     *
     * @param id the ID of the rental to retrieve.
     * @return RentalDto containing the rental information.
     * @throws RentalNotFoundException if the rental is not found.
     */
    @GetMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto getRentalById(@PathVariable Integer id) {
        final Rental rental = rentalService.getRentalById(id);
        return rentalMapping.mapRentalToRentalDto(rental);
    }

    /**
     * Creates a new rental.
     *
     * @param principal the principal of the current user.
     * @param name the name of the rental.
     * @param surface the surface of the rental.
     * @param price the price of the rental.
     * @param picture the picture of the rental.
     * @param description the description of the rental.
     * @return ResponseEntity with a MessageResponse indicating the creation status.
     * @throws IOException if an I/O error occurs.
     * @throws StringNotNumericException if the surface string is not numeric.
     */
    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> createRental(Principal principal, @RequestPart("name") String name,
                                          @RequestPart("surface") String surface,
                                          @RequestPart("price") String price,
                                          @RequestPart("picture") MultipartFile picture,
                                          @RequestPart("description") String description) throws IOException {
        final User owner = userService.getUserByEmail(principal.getName()).orElse(null);

        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
        final String fName = DateUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(fileName).orElse(null));

        if (!Utils.isNumeric(surface)) {
            throw new StringNotNumericException("String passed is not numeric.");
        }
        if (!Utils.isNumeric(price)) {
            throw new StringNotNumericException("String passed is not numeric.");
        }

        final Rental createdRental = rentalService.createRental(name, Utils.convertToNumeric(surface), Utils.convertToNumeric(price), fName, description, owner);
        final String uploadDir = dirName + "/" + createdRental.getId();
        FileUploadUtil.saveFile(uploadDir, fName, picture);

        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }

    /**
     * Updates a rental by its ID.
     *
     * @param id the ID of the rental to update.
     * @param name the new name of the rental.
     * @param surface the new surface area of the rental.
     * @param price the new price of the rental.
     * @param description the new description of the rental.
     * @return ResponseEntity with a MessageResponse indicating the update status.
     */
    @PutMapping("/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> editRentalById(@PathVariable Integer id, @RequestPart("name") String name,
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
}
