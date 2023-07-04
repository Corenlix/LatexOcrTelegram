package ru.corenlix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record PhotoDto(@NotBlank String telegramFileId,
                       @NotEmpty Integer fileSize,
                       @NonNull Long chatId) {
}
