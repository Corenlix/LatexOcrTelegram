package ru.corenlix.consumer;

import ru.corenlix.dto.TextDto;

public interface TextConsumer {
    void consume(TextDto textDto);
}
