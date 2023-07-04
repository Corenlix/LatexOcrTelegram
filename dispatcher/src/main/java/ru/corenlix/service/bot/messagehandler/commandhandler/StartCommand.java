package ru.corenlix.service.bot.messagehandler.commandhandler;

import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    @Override
    public String name() {
        return "/start";
    }

    @Override
    public String description() {
        return "Start";
    }

    @Override
    public String process(Long telegramId) {
        return "Hello! Use /help to get commands list";
    }
}
