package ru.corenlix.client;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.corenlix.dto.LatexAnswerDto;

@HttpExchange
public interface LatexClient {

    @PostExchange("/latex")
    LatexAnswerDto latexCode(byte[] image);
}
