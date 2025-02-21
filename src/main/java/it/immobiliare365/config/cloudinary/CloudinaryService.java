package it.immobiliare365.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.immobiliare365.models.SavedImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public SavedImage uploadFile(MultipartFile file) {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String secureUrl = (String) uploadResult.get("secure_url");
            String publicId = (String) uploadResult.get("public_id");
            return new SavedImage(secureUrl, publicId);
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary upload failed", e);
        }
    }

    public String deleteFile(String publicId) {
        try {
            Map<String, Object> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return (String) result.get("result");
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary delete failed", e);
        }
    }

}
