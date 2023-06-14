package co.istad.photostad.api.tutorial.web;

import co.istad.photostad.api.image.Image;
import co.istad.photostad.api.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

public record TutorialDto(
        String uuid,
        String title,
        String name,
        String slug,
        String description,
        Image thumbnail,
        String htmlContent,
        Integer viewCount,
        Timestamp createdAt,
        Integer createdBy,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Timestamp updatedAt
) { }
