package ru.corenlix.service.bot.messagehandler;

import org.junit.jupiter.api.Order;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnknownMessageTypeHandler implements MessageHandler {
    private static final String RESPONSE = "Unknown message type, use /help for more information";

    @Override
    public Optional<SendMessage> handle(Message message) {
        return Optional.of(new SendMessage(message.getChatId().toString(), RESPONSE));
    }
}
