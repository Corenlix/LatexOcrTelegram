package ru.corenlix.service.producer.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.corenlix.configuration.ApplicationConfig;
import ru.corenlix.dto.TextDto;
import ru.corenlix.service.producer.TextProducer;

@Service
public class RabbitTextProducer implements TextProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public RabbitTextProducer(ApplicationConfig applicationConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = applicationConfig.getRabbit().getTextMessageQueue();
    }

    @Override
    public void produce(TextDto textDto) {
        rabbitTemplate.convertAndSend(queueName, textDto);
    }
}
