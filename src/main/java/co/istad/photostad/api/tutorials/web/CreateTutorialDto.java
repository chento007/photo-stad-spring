package co.istad.photostad.api.tutorials.web;

public record CreateTutorialDto(
        String title,
        Integer thumbnail,
        Integer createdBy
) { }
