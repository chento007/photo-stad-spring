package co.istad.photostad.api.file.web;

import java.io.File;

public record SearchFileDto(
        File file,
        boolean status
) {
}
