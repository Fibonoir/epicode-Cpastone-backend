package it.immobiliare365.security.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(

        @NotNull(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email String email,

        @NotNull(message = "Password is required")
        @NotBlank(message = "Password is required")
        String password) {
}
