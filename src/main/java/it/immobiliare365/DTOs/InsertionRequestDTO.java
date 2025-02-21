package it.immobiliare365.DTOs;

import it.immobiliare365.models.enums.ListingType;
import it.immobiliare365.models.enums.PropertyCondition;
import it.immobiliare365.models.enums.PropertyType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertionRequestDTO {

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

    // Location details
    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Province is mandatory")
    private String province;

    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;

    // Property size
    @NotNull(message = "Total area is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total area must be positive")
    private Double totalArea;

    private Double livingArea;

    // Rooms
    @NotNull(message = "Bedrooms count is required")
    @Min(value = 0, message = "Bedrooms must be non-negative")
    private Integer bedrooms;

    @NotNull(message = "Bathrooms count is required")
    @Min(value = 0, message = "Bathrooms must be non-negative")
    private Integer bathrooms;

    private Integer additionalRooms;

    // Floor Information
    private Integer floorNumber;
    private Integer totalFloors;

    private Integer yearBuilt;

    @NotNull(message = "Property condition is required")
    private PropertyCondition condition;

    // Amenities
    private Boolean hasHeating;
    private Boolean hasAirConditioning;
    private Boolean hasParking;
    private Boolean hasElevator;
    private Boolean hasBalcony;
    private Boolean hasGarden;
    private Boolean hasSwimmingPool;
    private Boolean isFurnished;

    private String energyRating;

    private List<MultipartFile> photos;
}
