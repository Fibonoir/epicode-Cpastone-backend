package it.immobiliare365.excpetions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String name) {
        super(String.valueOf(name) + " not found");
    }
}
