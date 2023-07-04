package ru.corenlix.service.bot.messagehandler.commandhandler;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandChain {
    private final static String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Use /help";
    private final Map<String, Command> commands;

    public CommandChain(List<Command> commands) {
        this.commands = commands.stream().collect(Collectors.toMap(Command::name, command -> command));
    }

    public Optional<String> handleCommand(Long telegramId, String message) {
        if (!isCommand(message)) {
            return Optional.empty();
        }

        Command command = commands.get(message);
        return Optional.of(command == null ? UNKNOWN_COMMAND_MESSAGE : command.process(telegramId));
    }

    private boolean isCommand(String message) {
        return message.startsWith("/");
    }
}
