package ru.corenlix.producer.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.corenlix.producer.AnswerProducer;
import ru.corenlix.configuration.ApplicationConfig;
import ru.corenlix.dto.AnswerDto;

@Service
public class RabbitAnswerProducer implements AnswerProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public RabbitAnswerProducer(RabbitTemplate rabbitTemplate, ApplicationConfig applicationConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = applicationConfig.getRabbit().getAnswerMessageQueue();
    }

    @Override
    public void produce(AnswerDto answerDto) {
        rabbitTemplate.convertAndSend(queueName, answerDto);
    }
}
