package ru.corenlix.service.bot.messagehandler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.corenlix.exception.MessageHandlerNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageHandlerChain {
    private final List<MessageHandler> handlers;

    public Optional<SendMessage> handle(@NonNull Message message) {
        for (var handler : handlers) {
            var result = handler.handle(message);
            if (result.isPresent()) {
                return  result;
            }
        }

        throw new MessageHandlerNotFoundException(message);
    }
}
