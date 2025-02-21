package it.immobiliare365.security;

import it.immobiliare365.excpetions.BadRequestException;
import it.immobiliare365.security.DTOs.APIResponse;
import it.immobiliare365.security.DTOs.APIStatus;
import it.immobiliare365.security.DTOs.AuthResponseDTO;
import it.immobiliare365.security.DTOs.TokenDTO;
import it.immobiliare365.security.utils.JwtTools;
import it.immobiliare365.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTools jwt;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<AuthResponseDTO> refreshToken(@RequestBody TokenDTO refreshTokenDTO) {
        try {
            // Validate the refresh token. This will throw an exception if invalid or expired.
            String email = jwt.decodeToken(refreshTokenDTO.token());

            // Validate that the refresh token is in our store.
            if (!refreshTokenService.isValid(refreshTokenDTO.token(), email)) {
                throw new BadRequestException("Invalid refresh token");
            }

            // Invalidate the used refresh token.
            refreshTokenService.removeRefreshToken(refreshTokenDTO.token());

            // Generate new tokens.
            String newAccessToken = jwt.createToken(email);
            String newRefreshToken = jwt.generateRefreshToken(email);

            // Store the new refresh token.
            refreshTokenService.storeRefreshToken(newRefreshToken, email);

            return new APIResponse<>(
                    APIStatus.SUCCESS,
                    new AuthResponseDTO(newAccessToken, newRefreshToken),
                    "Token refreshed successfully"
            );
        } catch (Exception e) {
            throw new BadRequestException("Invalid or expired refresh token");
        }
    }
}
