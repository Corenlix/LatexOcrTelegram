package ru.corenlix.service.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.corenlix.exception.MessageHandlerNotFoundException;
import ru.corenlix.service.bot.messagehandler.MessageHandlerChain;
import ru.corenlix.configuration.ApplicationConfig;

@Service
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private static final String ERROR_MESSAGE = "Internal error happened";
    private final MessageHandlerChain messageHandlerChain;
    private final String botName;

    public TelegramBot(ApplicationConfig applicationConfig, MessageHandlerChain messageHandlerChain) {
        super(applicationConfig.getBot().getToken());
        this.botName = applicationConfig.getBot().getName();
        this.messageHandlerChain = messageHandlerChain;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage responseMessage;
        try {
            responseMessage = messageHandlerChain.handle(update.getMessage())
                    .orElseThrow(() -> new MessageHandlerNotFoundException(update.getMessage()));
        } catch (RuntimeException e) {
            log.error(e.toString());
            responseMessage = new SendMessage(update.getMessage().getMessageId().toString(), ERROR_MESSAGE);
        }

        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    public void send(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
