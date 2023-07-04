package ru.corenlix.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue textMessageQueue(ApplicationConfig applicationConfig) {
        return new Queue(applicationConfig.getRabbit().getTextMessageQueue());
    }

    @Bean
    public Queue photoMessageQueue(ApplicationConfig applicationConfig) {
        return new Queue(applicationConfig.getRabbit().getPhotoMessageQueue());
    }

    @Bean
    public Queue answerMessageQueue(ApplicationConfig applicationConfig) {
        return new Queue(applicationConfig.getRabbit().getAnswerMessageQueue());
    }
}
