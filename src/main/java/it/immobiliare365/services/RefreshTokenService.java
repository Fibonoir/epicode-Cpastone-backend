package it.immobiliare365.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RefreshTokenService {

    private final Map<String, String> refreshTokenStore = new ConcurrentHashMap<>();

    public void storeRefreshToken(String refreshToken, String email) {
        refreshTokenStore.put(refreshToken, email);
    }

    public boolean isValid(String refreshToken, String email) {
        return email.equals(refreshTokenStore.get(refreshToken));
    }

    public void removeRefreshToken(String refreshToken) {
        refreshTokenStore.remove(refreshToken);
    }

}
