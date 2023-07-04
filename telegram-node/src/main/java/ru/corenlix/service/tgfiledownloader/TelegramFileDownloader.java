package ru.corenlix.service.tgfiledownloader;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.corenlix.configuration.ApplicationConfig;
import ru.corenlix.exception.UploadFileException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class TelegramFileDownloader {
    private final String fileInfoUri;
    private final String fileStorageUri;
    private final String token;

    public TelegramFileDownloader(ApplicationConfig applicationConfig) {
        this.fileInfoUri = applicationConfig.getTelegram().getFileInfoUri();
        this.fileStorageUri = applicationConfig.getTelegram().getFileStorageUri();
        this.token = applicationConfig.getTelegram().getToken();
    }

    public byte[] downloadFileById(String fileId) {
        ResponseEntity<String> response = getFilePath(fileId);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject jsonObject = new JSONObject(response.getBody());
                String filePath = String.valueOf(jsonObject
                        .getJSONObject("result")
                        .getString("file_path"));
                return downloadFileByPath(filePath);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new UploadFileException("Bad response from telegram service: " + response);
        }
    }

    private ResponseEntity<String> getFilePath(String fileId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                fileInfoUri,
                HttpMethod.GET,
                request,
                String.class,
                token,
                fileId
        );
    }

    private byte[] downloadFileByPath(String filePath) {
        String fullUri = fileStorageUri
                .replace("{token}", token)
                .replace("{filePath}", filePath);
        URL url;
        try {
            url = new URL(fullUri);
        } catch (MalformedURLException e) {
            throw new UploadFileException(e);
        }

        try (InputStream inputStream = url.openStream()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new UploadFileException(e);
        }
    }
}
