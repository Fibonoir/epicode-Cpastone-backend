package it.immobiliare365.DTOs.mapper;

import it.immobiliare365.DTOs.InsertionRequestDTO;
import it.immobiliare365.DTOs.InsertionResponseDTO;
import it.immobiliare365.DTOs.InsertionUpdateRequestDTO;
import it.immobiliare365.config.cloudinary.CloudinaryService;
import it.immobiliare365.models.Insertion;
import it.immobiliare365.models.SavedImage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InsertionMapper {
    private final CloudinaryService cloudinaryService;

    /**
     * Convert an Insertion entity to a response DTO.
     */
    public static InsertionResponseDTO toResponseDTO(Insertion insertion) {
        if (insertion == null) {
            return null;
        }
        InsertionResponseDTO dto = new InsertionResponseDTO();
        dto.setId(insertion.getId());
        dto.setTitle(insertion.getTitle());
        dto.setDescription(insertion.getDescription());
        dto.setPropertyType(insertion.getPropertyType());
        dto.setListingType(insertion.getListingType());
        dto.setPrice(insertion.getPrice());
        dto.setAddress(insertion.getAddress());
        dto.setCity(insertion.getCity());
        dto.setProvince(insertion.getProvince());
        dto.setPostalCode(insertion.getPostalCode());
        dto.setTotalArea(insertion.getTotalArea());
        dto.setLivingArea(insertion.getLivingArea());
        dto.setBedrooms(insertion.getBedrooms());
        dto.setBathrooms(insertion.getBathrooms());
        dto.setAdditionalRooms(insertion.getAdditionalRooms());
        dto.setFloorNumber(insertion.getFloorNumber());
        dto.setTotalFloors(insertion.getTotalFloors());
        dto.setYearBuilt(insertion.getYearBuilt());
        dto.setCondition(insertion.getCondition());
        dto.setHasHeating(insertion.getHasHeating());
        dto.setHasAirConditioning(insertion.getHasAirConditioning());
        dto.setHasParking(insertion.getHasParking());
        dto.setHasElevator(insertion.getHasElevator());
        dto.setHasBalcony(insertion.getHasBalcony());
        dto.setHasGarden(insertion.getHasGarden());
        dto.setHasSwimmingPool(insertion.getHasSwimmingPool());
        dto.setIsFurnished(insertion.getIsFurnished());
        dto.setEnergyRating(insertion.getEnergyRating());
        dto.setPhotos(insertion.getPhotos());
        dto.setDateListed(insertion.getDateListed());
        dto.setStatus(insertion.getStatus());
        return dto;
    }

    /**
     * Convert a request DTO to an Insertion entity.
     *
     * Note: This method does not set fields managed internally (id, dateListed, status).
     */
    public Insertion fromRequestDTO(InsertionRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Insertion insertion = new Insertion();
        insertion.setTitle(dto.getTitle());
        insertion.setDescription(dto.getDescription());
        insertion.setPropertyType(dto.getPropertyType());
        insertion.setListingType(dto.getListingType());
        insertion.setPrice(dto.getPrice());
        insertion.setAddress(dto.getAddress());
        insertion.setCity(dto.getCity());
        insertion.setProvince(dto.getProvince());
        insertion.setPostalCode(dto.getPostalCode());
        insertion.setTotalArea(dto.getTotalArea());
        insertion.setLivingArea(dto.getLivingArea());
        insertion.setBedrooms(dto.getBedrooms());
        insertion.setBathrooms(dto.getBathrooms());
        insertion.setAdditionalRooms(dto.getAdditionalRooms());
        insertion.setFloorNumber(dto.getFloorNumber());
        insertion.setTotalFloors(dto.getTotalFloors());
        insertion.setYearBuilt(dto.getYearBuilt());
        insertion.setCondition(dto.getCondition());
        insertion.setHasHeating(dto.getHasHeating());
        insertion.setHasAirConditioning(dto.getHasAirConditioning());
        insertion.setHasParking(dto.getHasParking());
        insertion.setHasElevator(dto.getHasElevator());
        insertion.setHasBalcony(dto.getHasBalcony());
        insertion.setHasGarden(dto.getHasGarden());
        insertion.setHasSwimmingPool(dto.getHasSwimmingPool());
        insertion.setIsFurnished(dto.getIsFurnished());
        insertion.setEnergyRating(dto.getEnergyRating());

        if (dto.getPhotos() != null && !dto.getPhotos().isEmpty()) {
            List<SavedImage> savedImages = new ArrayList<>();
            for (MultipartFile file : dto.getPhotos()) {
                SavedImage savedImage = cloudinaryService.uploadFile(file);
                savedImages.add(savedImage);
            }
            insertion.setPhotos(savedImages);
        }

        return insertion;
    }


    /**
     * Update an existing Insertion using the values from the update DTO.
     * The CloudinaryService is used to upload new files and delete removed images.
     */
    public Insertion updateFromRequestDTO(Insertion insertion,
                                          InsertionUpdateRequestDTO dto) {
        // Update basic fields.
        insertion.setTitle(dto.getTitle());
        insertion.setDescription(dto.getDescription());
        insertion.setPropertyType(dto.getPropertyType());
        insertion.setListingType(dto.getListingType());
        insertion.setPrice(dto.getPrice());
        insertion.setAddress(dto.getAddress());
        insertion.setCity(dto.getCity());
        insertion.setProvince(dto.getProvince());
        insertion.setPostalCode(dto.getPostalCode());
        insertion.setTotalArea(dto.getTotalArea());
        insertion.setLivingArea(dto.getLivingArea());
        insertion.setBedrooms(dto.getBedrooms());
        insertion.setBathrooms(dto.getBathrooms());
        insertion.setAdditionalRooms(dto.getAdditionalRooms());
        insertion.setFloorNumber(dto.getFloorNumber());
        insertion.setTotalFloors(dto.getTotalFloors());
        insertion.setYearBuilt(dto.getYearBuilt());
        insertion.setCondition(dto.getCondition());
        insertion.setEnergyRating(dto.getEnergyRating());
        insertion.setHasHeating(dto.getHasHeating());
        insertion.setHasAirConditioning(dto.getHasAirConditioning());
        insertion.setHasParking(dto.getHasParking());
        insertion.setHasElevator(dto.getHasElevator());
        insertion.setHasBalcony(dto.getHasBalcony());
        insertion.setHasGarden(dto.getHasGarden());
        insertion.setHasSwimmingPool(dto.getHasSwimmingPool());
        insertion.setIsFurnished(dto.getIsFurnished());
        insertion.setDateListed(new Date());

        // --- IMAGE HANDLING ---
        // Current photos in the entity.
        List<SavedImage> currentPhotos = insertion.getPhotos() != null ? insertion.getPhotos() : new ArrayList<>();

        // List of photos the client wants to keep.
        List<SavedImage> photosToKeep = dto.getExistingPhotos() != null ? dto.getExistingPhotos() : new ArrayList<>();

        // 1. Delete images that are in currentPhotos but NOT in photosToKeep.
        for (SavedImage savedImage : currentPhotos) {
            boolean keep = photosToKeep.stream()
                    .anyMatch(existing -> existing.getPublicId().equals(savedImage.getPublicId()));
            if (!keep) {
                cloudinaryService.deleteFile(savedImage.getPublicId());
            }
        }

        // 2. Upload new images.
        List<SavedImage> newUploadedPhotos = new ArrayList<>();
        if (dto.getNewPhotos() != null && !dto.getNewPhotos().isEmpty()) {
            for (MultipartFile file : dto.getNewPhotos()) {
                if (!file.isEmpty()) {  // Check if the file contains data.
                    SavedImage uploaded = cloudinaryService.uploadFile(file);
                    newUploadedPhotos.add(uploaded);
                }
            }
        }

        // 3. Define the final ordering.
        // Here we assume the client sends the final order for existing photos
        // and new photos will be appended in the order they were uploaded.
        List<SavedImage> finalPhotos = new ArrayList<>();
        finalPhotos.addAll(photosToKeep);
        finalPhotos.addAll(newUploadedPhotos);

        insertion.setPhotos(finalPhotos);

        return insertion;
    }
}


