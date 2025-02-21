package it.immobiliare365.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.immobiliare365.models.SavedImage;
import it.immobiliare365.models.enums.ListingType;
import it.immobiliare365.models.enums.PropertyCondition;
import it.immobiliare365.models.enums.PropertyType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InsertionUpdateRequestDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;

    @NotNull(message = "Listing type is required")
    private ListingType listingType;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Province is mandatory")
    private String province;

    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;

    @NotNull(message = "Total area is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total area must be positive")
    private Double totalArea;

    private Double livingArea;

    @NotNull(message = "Bedrooms count is required")
    @Min(value = 0, message = "Bedrooms must be non-negative")
    private Integer bedrooms;

    @NotNull(message = "Bathrooms count is required")
    @Min(value = 0, message = "Bathrooms must be non-negative")
    private Integer bathrooms;

    private Integer additionalRooms;
    private Integer floorNumber;
    private Integer totalFloors;
    private Integer yearBuilt;

    @NotNull(message = "Property condition is required")
    private PropertyCondition condition;

    // Other amenities and fields (e.g. hasHeating, energyRating, etc.)
    private String energyRating;

    // Amenities
    private Boolean hasHeating;
    private Boolean hasAirConditioning;
    private Boolean hasParking;
    private Boolean hasElevator;
    private Boolean hasBalcony;
    private Boolean hasGarden;
    private Boolean hasSwimmingPool;
    private Boolean isFurnished;

    // --- IMAGE HANDLING FIELDS ---
    // The ordered list of URLs that the client wants to keep.
    private List<SavedImage> existingPhotos;
    // New images to upload.
    @JsonIgnore
    private List<MultipartFile> newPhotos;
}
