package ru.corenlix.exception;

import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandlerNotFoundException extends RuntimeException {
    public MessageHandlerNotFoundException(Message message) {
        super("Handler for " + message + "not found");
    }
}
