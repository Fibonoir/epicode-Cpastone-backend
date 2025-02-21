package it.immobiliare365.security;

import it.immobiliare365.excpetions.ElementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin getAdminByEmail(String identifier) {
        return adminRepository.findByUsername(identifier)
                .or(() -> adminRepository.findByEmail(identifier))
                .orElseThrow(() -> new ElementNotFoundException(identifier));
    }
}
