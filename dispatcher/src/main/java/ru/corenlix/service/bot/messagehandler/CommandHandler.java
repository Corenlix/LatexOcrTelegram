package ru.corenlix.service.bot.messagehandler;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.corenlix.service.bot.messagehandler.commandhandler.CommandChain;

import java.util.Optional;

@Component
@Order(0)
@RequiredArgsConstructor
public class CommandHandler implements MessageHandler {
    private final CommandChain commandChain;

    @Override
    public Optional<SendMessage> handle(Message message) {
        if (!message.hasText())
            return Optional.empty();

        Optional<String> result = commandChain.handleCommand(message.getFrom().getId(), message.getText());
        return result.map(s -> new SendMessage(message.getChatId().toString(), s));
    }
}
