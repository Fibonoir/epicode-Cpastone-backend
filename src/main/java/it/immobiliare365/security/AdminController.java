package it.immobiliare365.security;

import it.immobiliare365.excpetions.BadRequestException;
import it.immobiliare365.security.DTOs.*;
import it.immobiliare365.security.utils.JwtTools;
import it.immobiliare365.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtTools jwt;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<AuthResponseDTO> login(@RequestBody LoginDTO credentials, BindingResult validationResult) {
        if (validationResult.hasErrors())
            throw new BadRequestException(validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toString());

        Admin admin = adminService.getAdminByEmail(credentials.email());

        if (passwordEncoder.matches(credentials.password(), admin.getPassword())) {

            String accessToken = jwt.createToken(credentials.email());
            String refreshToken = jwt.generateRefreshToken(credentials.email());

            refreshTokenService.storeRefreshToken(refreshToken, credentials.email());

            return new APIResponse<>(
                    APIStatus.SUCCESS,
                    new AuthResponseDTO(accessToken, refreshToken),
                    "Logged in successfully"
            );

        } else throw new BadRequestException("Invalid credentials");

    }



    @GetMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    public Admin getDetails(@AuthenticationPrincipal Admin details){
        return details;
    }

}
