package co.istad.photostad.api.certificatedownload.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCertificateDownloadDto(
        @NotNull(message = "certificateId is require")
        Integer certificateId,
        @NotBlank(message = "format is require")
        String format,
        @NotBlank(message = "compression is require")
        String compression
) {
}
