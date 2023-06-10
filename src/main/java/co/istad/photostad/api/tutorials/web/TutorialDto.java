package co.istad.photostad.api.tutorials.web;

import co.istad.photostad.api.image.Image;
import co.istad.photostad.api.user.User;

import java.sql.Timestamp;

public record TutorialDto(
        String uuid,
        String title,
        String slug,
        String description,
        Image thumbnail,
        String htmlContent,
        Integer viewCount,
        Timestamp createdAt,
        Integer createdBy
) {
}
