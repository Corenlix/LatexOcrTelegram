package ru.corenlix.exception;

public class UploadFileException extends RuntimeException {

    public UploadFileException(String message) {
        super(message);
    }

    public UploadFileException(Exception e) {
        super(e);
    }
}
