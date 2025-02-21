package it.immobiliare365.security.DTOs;

public record APIResponse<T>(
    APIStatus status,
    T data,
    String message
){}
