package it.immobiliare365.excpetions;

public class ForbiddenException  extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
