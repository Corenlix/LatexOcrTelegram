package ru.corenlix.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
@Data
@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class ApplicationConfig {
    private Rabbit rabbit;
    private Telegram telegram;
    private Latex latex;

    @Validated
    @Data
    public static class Rabbit {
        @NotBlank
        private final String textMessageQueue;

        @NotBlank
        private final String photoMessageQueue;

        @NotBlank
        private final String answerMessageQueue;
    }

    @Validated
    @Data
    public static class Telegram {
        @NotBlank
        private final String token;

        @NotBlank
        private final String fileInfoUri;

        @NotBlank
        private final String fileStorageUri;
    }

    @Validated
    @Data
    public static class Latex {
        @NonNull
        private final URI path;
    }
}