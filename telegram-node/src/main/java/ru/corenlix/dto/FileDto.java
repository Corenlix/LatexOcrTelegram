package ru.corenlix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record FileDto(byte @NonNull [] binaryContent,
                      String name,
                      @NotBlank String mimeType) {
}