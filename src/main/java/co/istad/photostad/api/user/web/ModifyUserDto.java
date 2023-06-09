package co.istad.photostad.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ModifyUserDto(
        @NotBlank(message = "username is require !")
        String username,
        @NotBlank(message = "family name is require")
        String familyName,
        @NotBlank(message = "given name is require")
        String givenName,
        @NotBlank(message = "gender is require")
        String gender,
        // check
        @NotNull(message = "date of birth is require")
        LocalDate dob,
        // check
        @NotNull(message = "avatar is require")
        Integer avatar,
        @NotBlank(message = "phone number is require")
        String phoneNumber,
        @NotBlank(message = "address is require")
        String address,
        @NotBlank(message = "biography is require")
        String biography
) {
}
