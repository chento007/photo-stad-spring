package co.istad.photostad.file.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record FileDto(
        String name,
        String url,
        String extension,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String downloadUrl,
        long size
) {}
