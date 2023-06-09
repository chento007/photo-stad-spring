package co.istad.photostad.api.image.web;

import jakarta.validation.constraints.NotBlank;

public record ModifyImageDto(
        @NotBlank(message = "Name is require !")
        String name,
        @NotBlank(message = "Image Type is require !")
        String type
) { }
