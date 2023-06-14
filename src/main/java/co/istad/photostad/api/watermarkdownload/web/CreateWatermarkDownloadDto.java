package co.istad.photostad.api.watermarkdownload.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWatermarkDownloadDto(
        @NotNull(message = "watermarkId is require")
        Integer watermarkId,
        @NotBlank(message = "format is require")
        String format,
        @NotBlank(message = "compression is require")
        String compression
) {
}
