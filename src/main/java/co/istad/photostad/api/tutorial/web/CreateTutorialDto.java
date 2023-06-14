package co.istad.photostad.api.tutorial.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTutorialDto(
        @NotBlank(message = "title is require")
        String title,
        @NotNull(message = "thumbnail is require")
        Integer thumbnail,
        @NotNull(message = "createdBy is require")
        Integer createdBy
) { }
