package ru.corenlix.consumer.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.corenlix.client.LatexClient;
import ru.corenlix.consumer.PhotoConsumer;
import ru.corenlix.producer.AnswerProducer;
import ru.corenlix.dto.AnswerDto;
import ru.corenlix.dto.PhotoDto;
import ru.corenlix.service.tgfiledownloader.TelegramFileDownloader;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitPhotoConsumer implements PhotoConsumer {
    private final AnswerProducer answerProducer;
    private final LatexClient latexClient;
    private final TelegramFileDownloader fileDownloader;

    @Override
    @RabbitListener(queues = "#{applicationConfig.rabbit.photoMessageQueue}")
    public void consume(PhotoDto photoDto) {
        byte[] imageBytes = fileDownloader.downloadFileById(photoDto.telegramFileId());
        var response = latexClient.latexCode(imageBytes);
        answerProducer.produce(new AnswerDto(photoDto.chatId(), response.latex()));
    }
}
