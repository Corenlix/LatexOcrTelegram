package ru.corenlix.service.bot.messagehandler.commandhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {
    private final List<Command> commandList;

    @Override
    public String name() {
        return "/help";
    }

    @Override
    public String description() {
        return "Get list of commands";
    }

    @Override
    public String process(Long telegramId) {
        return String.join("\n",
                commandList.stream()
                        .map(x -> x.name() + " - " + x.description())
                        .toList());
    }
}
