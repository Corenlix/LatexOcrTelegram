package ru.corenlix.service.consumer.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.corenlix.dto.AnswerDto;
import ru.corenlix.service.bot.TelegramBot;
import ru.corenlix.service.consumer.AnswerConsumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitAnswerConsumer implements AnswerConsumer {
    private final TelegramBot tgBot;

    @Override
    @RabbitListener(queues = "#{applicationConfig.rabbit.answerMessageQueue}")
    public void consume(AnswerDto answerDto) {
        tgBot.send(new SendMessage(answerDto.chatId().toString(), answerDto.message()));
    }
}
