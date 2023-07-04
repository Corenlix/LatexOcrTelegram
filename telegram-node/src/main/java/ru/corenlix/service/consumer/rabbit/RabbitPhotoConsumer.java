package ru.corenlix.service.consumer.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.corenlix.client.LatexClient;
import ru.corenlix.dto.AnswerDto;
import ru.corenlix.dto.LatexAnswerDto;
import ru.corenlix.dto.PhotoDto;
import ru.corenlix.service.consumer.PhotoConsumer;
import ru.corenlix.service.producer.AnswerProducer;
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
        byte[] imageBytes = fileDownloader.downloadFileById(photoDto.telegramFileId()); // Replace this with your own logic to obtain the byte content of the image
        LatexAnswerDto answer = latexClient.latexCode(imageBytes);
        answerProducer.produce(new AnswerDto(photoDto.chatId(), answer.latex()));
    }

    private void x(PhotoDto photoDto) {
      /*  // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create the byte content of the image
        byte[] imageBytes = fileDownloader.downloadFileById(photoDto.telegramFileId()); // Replace this with your own logic to obtain the byte content of the image

        // Create the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the request payload as a MultiValueMap
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(imageBytes) {
            @Override
            public String getFilename() {
                return "image.jpg";
            }
        });

        // Create the request entity with the payload and headers
        RequestEntity<MultiValueMap<String, Object>> requestEntity = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(url));

        // Send the request and retrieve the response
        ResponseEntity<LatexAnswerDto> responseEntity = restTemplate.exchange(requestEntity, LatexAnswerDto.class);

        // Extract the filename from the response body
        LatexAnswerDto filename = responseEntity.getBody();

        // Do something with the filename
        System.out.println("Uploaded filename: " + filename);


        log.debug("NODE: Photos is received");
        answerProducer.produce(new AnswerDto(photoDto.chatId(), filename.filename()));*/
    }
}
