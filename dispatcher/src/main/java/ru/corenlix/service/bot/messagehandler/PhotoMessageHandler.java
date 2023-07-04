package ru.corenlix.service.bot.messagehandler;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.corenlix.dto.PhotoDto;
import ru.corenlix.service.producer.PhotosProducer;

import java.util.Optional;

@Component
@Order(0)
@RequiredArgsConstructor
public class PhotoMessageHandler implements MessageHandler {
    private static final String RECEIVE_MESSAGE_TEXT = "Handling photos...";
    private final PhotosProducer photoProducer;

    @Override
    public Optional<SendMessage> handle(Message message) {
        if (!message.hasPhoto())
            return Optional.empty();

        photoProducer.produce(buildDto(message));
        return Optional.of(new SendMessage(message.getChatId().toString(), RECEIVE_MESSAGE_TEXT));
    }

    private PhotoDto buildDto(Message message) {
        var photos = message.getPhoto();
        var lastPhoto = photos.get(photos.size() - 1);

        return PhotoDto.builder()
                .fileSize(lastPhoto.getFileSize())
                .telegramFileId(lastPhoto.getFileId())
                .chatId(message.getChatId())
                .build();
    }
}
