package ru.corenlix.consumer.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.corenlix.producer.AnswerProducer;
import ru.corenlix.dto.AnswerDto;
import ru.corenlix.dto.TextDto;
import ru.corenlix.consumer.TextConsumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitTextConsumer implements TextConsumer {
    private final AnswerProducer answerProducer;

    @Override
    @RabbitListener(queues = "#{applicationConfig.rabbit.textMessageQueue}")
    public void consume(TextDto textDto) {
        log.debug("NODE: Text is received");
        answerProducer.produce(new AnswerDto(textDto.chatId(), "Text is received!"));
    }
}
