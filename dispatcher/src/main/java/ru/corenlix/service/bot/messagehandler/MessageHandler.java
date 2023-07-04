package ru.corenlix.service.bot.messagehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface MessageHandler {
    Optional<SendMessage> handle(Message message);
}
