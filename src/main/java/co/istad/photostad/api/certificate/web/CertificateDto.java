package co.istad.photostad.api.certificate.web;

import co.istad.photostad.api.json.Design;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.sql.Timestamp;
@Builder
public record CertificateDto(
         String uuid,
         Design editorJson,
         Integer createdBy,
         Timestamp createdAt
) {
}
