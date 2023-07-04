package ru.corenlix.service.producer;

import ru.corenlix.dto.AnswerDto;

public interface AnswerProducer {
    void produce(AnswerDto answerDto);
}
