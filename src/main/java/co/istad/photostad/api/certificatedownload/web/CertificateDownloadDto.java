package co.istad.photostad.api.certificatedownload.web;

import java.sql.Timestamp;

public record CertificateDownloadDto(
        String uuid,
        Integer certificateId,
        String format,
        String compression,
        Timestamp downloadAt
) {
}
