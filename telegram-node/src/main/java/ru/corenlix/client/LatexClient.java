package ru.corenlix.client;

import ru.corenlix.dto.LatexResponse;

public interface LatexClient {

    LatexResponse latexCode(byte[] photo);
}