package it.immobiliare365.excpetions;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            BadRequestException.class,
            MissingServletRequestPartException.class,
            DataIntegrityViolationException.class,
            HttpMediaTypeNotSupportedException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handle400(Exception error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handle401(UnauthorizedException error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDTO handle403(ForbiddenException error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handle404(ElementNotFoundException error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public ErrorDTO handle413(SizeLimitExceededException error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handle500(Exception error) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(Map.of("Error:", error.getMessage()))
                .build();
    }
}
