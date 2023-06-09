package co.istad.photostad.api.image.web;

import jakarta.validation.constraints.NotBlank;

public record ImageDto(
        String uuid,
        String name,
        String type

) {}
