package dev.hack14.colmena.dtos;

import java.time.LocalDateTime;

public record NotificationDto(
    Long id,
    Long userId,
    String message,
    boolean read,
    LocalDateTime createdAt,
    Long adId,
    String adTitle,
    Long contactId
) {}
