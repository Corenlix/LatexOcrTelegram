package ru.corenlix.service.bot.messagehandler.commandhandler;

public interface Command {
    String name();

    String description();

    String process(Long telegramId);
}
