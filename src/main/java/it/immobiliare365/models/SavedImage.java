package it.immobiliare365.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedImage {
    @Column(name = "secure_url")
    private String secureUrl;

    @Column(name = "public_id")
    private String publicId;
}
