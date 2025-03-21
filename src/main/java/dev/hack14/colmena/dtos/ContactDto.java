package dev.hack14.colmena.dtos;

import java.time.LocalDateTime;

public record ContactDto(
    Long id,
    Long senderId,
    String senderUsername,
    Long adId,
    String adTitle,
    String message,
    String contactEmail,
    String contactPhone,
    LocalDateTime createdAt
) {}