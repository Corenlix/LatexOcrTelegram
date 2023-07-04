package ru.corenlix.service.producer;

import ru.corenlix.dto.TextDto;

public interface TextProducer {
    void produce(TextDto textDto);
}