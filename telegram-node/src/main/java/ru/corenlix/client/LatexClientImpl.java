package ru.corenlix.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.corenlix.dto.LatexResponse;

import java.net.URI;

@RequiredArgsConstructor
public class LatexClientImpl implements LatexClient {

    private final URI uri;
    private final RestTemplate restTemplate;

    @Override
    public LatexResponse latexCode(byte[] photo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("photo", new ByteArrayResource(photo) {
            @Override
            public String getFilename() {
                return "image.jpg";
            }
        });

        RequestEntity<MultiValueMap<String, Object>> requestEntity = new RequestEntity<>(body, headers, HttpMethod.POST, uri);
        ResponseEntity<LatexResponse> responseEntity = restTemplate.exchange(requestEntity, LatexResponse.class);
        return responseEntity.getBody();
    }
}