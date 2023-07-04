package ru.corenlix.service.consumer;

import ru.corenlix.dto.AnswerDto;

public interface AnswerConsumer {
    void consume(AnswerDto answerDto);
}
