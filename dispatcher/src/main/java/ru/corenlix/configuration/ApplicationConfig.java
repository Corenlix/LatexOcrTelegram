package ru.corenlix.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@Validated
@Data
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
@Import({TelegramBotStarterConfiguration.class})
public class ApplicationConfig {
    private Bot bot;
    private Rabbit rabbit;

    @Validated
    @Data
    public static class Bot {
        @NotBlank
        private final String name;

        @NotBlank
        private final String token;
    }

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
}
