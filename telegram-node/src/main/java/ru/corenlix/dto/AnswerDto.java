package ru.corenlix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record AnswerDto(@NonNull Long chatId, @NotBlank String message) {
}
