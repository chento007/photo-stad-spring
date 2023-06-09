package co.istad.photostad.api.user.web;

import co.istad.photostad.api.image.Image;

import java.sql.Timestamp;

public record UserDto(
    String uuid,
    String name,
    String username,
    String familyName,
    String givenName,
    String gender,
    String dob,
    Image avatar,
    String phoneNumber,
    String address,
    String biography,
    String createdAt,
    String loggedInAt,
    String email,
    Boolean isDeleted
) {}
