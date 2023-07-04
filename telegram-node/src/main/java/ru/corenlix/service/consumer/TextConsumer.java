package ru.corenlix.service.consumer;

import ru.corenlix.dto.TextDto;

public interface TextConsumer {
    void consume(TextDto textDto);
}
