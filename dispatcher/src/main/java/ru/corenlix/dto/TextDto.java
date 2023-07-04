package ru.corenlix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record TextDto(@NonNull Long chatId, @NotBlank String text) {
}
