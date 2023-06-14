package co.istad.photostad.api.requesttutorials.web;


public record RequestTutorialDto(
        String uuid,
        Integer userId,
        String description,
        Boolean isRead
) { }
