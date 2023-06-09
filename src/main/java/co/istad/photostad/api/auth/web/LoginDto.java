package co.istad.photostad.api.auth.web;

import co.istad.photostad.api.user.validation.password.Password;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "email is require")
        String email,
        @NotBlank(message = "password is require")
        @Password
        String password
) {
}
