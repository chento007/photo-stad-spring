package co.istad.photostad.api.watermark.web;

import co.istad.photostad.api.json.Design;

import java.sql.Timestamp;

public record WatermarkDto(
        String uuid,
        Design editorJson,
        Integer createdBy,
        Timestamp createdAt
) { }
