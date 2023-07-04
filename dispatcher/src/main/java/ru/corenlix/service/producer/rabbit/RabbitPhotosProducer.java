package ru.corenlix.service.producer.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.corenlix.configuration.ApplicationConfig;
import ru.corenlix.dto.PhotoDto;
import ru.corenlix.service.producer.PhotosProducer;

@Service
public class RabbitPhotosProducer implements PhotosProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public RabbitPhotosProducer(ApplicationConfig applicationConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = applicationConfig.getRabbit().getPhotoMessageQueue();
    }

    @Override
    public void produce(PhotoDto photosDto) {
        rabbitTemplate.convertAndSend(queueName, photosDto);
    }
}
