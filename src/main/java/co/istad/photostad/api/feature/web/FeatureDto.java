package co.istad.photostad.api.feature.web;

import co.istad.photostad.api.json.Design;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeatureDto(
        @NotBlank(message = "name is require")
        String name,
        @NotNull(message = "sample is require")
        Design sample
) {
}
