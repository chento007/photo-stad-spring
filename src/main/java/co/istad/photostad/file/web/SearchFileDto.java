package co.istad.photostad.file.web;

import java.io.File;

public record SearchFileDto(
        File file,
        boolean status
) {
}
