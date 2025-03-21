package dev.hack14.colmena.dtos;

import java.util.Date;

public record AdDto(
    Long id,
    String title,
    String description,
    String category,
    Date datePosted,
    Long adminId,
    String adminUsername,
    String imageUrl
) {}
