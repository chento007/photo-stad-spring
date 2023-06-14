package co.istad.photostad.api.requesttutorials.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModifyRequestTutorialDto(
        @NotNull(message = "userId is require")
        Integer userId,
        @NotBlank(message = "description is require")
        String description
) { }
