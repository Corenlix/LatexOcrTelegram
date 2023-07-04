package ru.corenlix.service.bot.messagehandler;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.corenlix.dto.TextDto;
import ru.corenlix.service.producer.TextProducer;

import java.util.Optional;

@Component
@Order(3)
@RequiredArgsConstructor
public class TextMessageHandler implements MessageHandler {
    private static final String RECEIVED_MESSAGE_TEXT = "Handling message...";
    private final TextProducer textProducer;

    @Override
    public Optional<SendMessage> handle(Message message) {
        if (!message.hasText())
            return Optional.empty();

        textProducer.produce(new TextDto(message.getChatId(), message.getText()));
        return Optional.of(new SendMessage(message.getChatId().toString(), RECEIVED_MESSAGE_TEXT));
    }
}
