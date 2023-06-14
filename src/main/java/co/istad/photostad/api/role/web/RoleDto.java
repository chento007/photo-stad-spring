package co.istad.photostad.api.role.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleDto(
        @NotNull(message = "id is require")
        Integer id,
        @NotBlank(message = "name is require")
        String name
) {
}
