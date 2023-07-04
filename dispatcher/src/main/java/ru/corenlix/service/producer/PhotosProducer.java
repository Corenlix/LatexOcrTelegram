package ru.corenlix.service.producer;

import ru.corenlix.dto.PhotoDto;

public interface PhotosProducer {
    void produce(PhotoDto photosDto);
}