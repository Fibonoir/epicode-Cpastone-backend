package it.immobiliare365.config.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dvrbwpned");
        config.put("api_key", "572927574363231");
        config.put("api_secret", "QsPKYNQeZPnsj0tbjI4FJoEAtC0");
        return new Cloudinary(config);
    }
}
