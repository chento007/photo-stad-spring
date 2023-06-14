package co.istad.photostad.api.watermarkdownload.web;

import java.sql.Timestamp;

public record WatermarkDownloadDto(
        String uuid,
        Integer watermarkId,
        String format,
        String compression,
        Timestamp downloadAt
) { }
