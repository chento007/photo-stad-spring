package co.istad.photostad.api.certificate.web;

import co.istad.photostad.api.json.Design;
import jakarta.validation.constraints.NotNull;

public record CreateCertificateDto(
        @NotNull(message = "data edit is require !!")
        Design editorJson,
        @NotNull(message = "user ID id is require !!")
        Integer createdBy
) {
}
