package co.istad.photostad.api.tutorial.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTutorialDto(
        @NotBlank(message = "tutorial is require")
        String title,
        @NotBlank(message = "name is require")
        String name,
        @NotBlank(message = "slug is require")
        String slug,
        @NotBlank(message = "description is require")
        String description,
        @NotNull(message = "thumbnail is require")
        Integer thumbnail,
        @NotBlank(message = "htmlContent is require")
        String htmlContent
) {
}
