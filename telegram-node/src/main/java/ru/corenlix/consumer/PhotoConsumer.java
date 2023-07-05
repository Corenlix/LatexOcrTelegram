package ru.corenlix.consumer;

import ru.corenlix.dto.PhotoDto;

public interface PhotoConsumer {
    void consume(PhotoDto photoDto);
}
