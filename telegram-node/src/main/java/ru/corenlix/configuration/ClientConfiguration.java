package ru.corenlix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.corenlix.client.LatexClient;
import ru.corenlix.client.LatexClientImpl;

@Configuration
public class ClientConfiguration {
    @Bean
    LatexClient latexClient(ApplicationConfig applicationConfig) {
        return new LatexClientImpl(applicationConfig.getLatex().getPath(), new RestTemplate());
    }
}
