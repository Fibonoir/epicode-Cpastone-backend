package it.immobiliare365.DTOs;

import it.immobiliare365.models.SavedImage;
import it.immobiliare365.models.enums.InsertionStatus;
import it.immobiliare365.models.enums.ListingType;
import it.immobiliare365.models.enums.PropertyCondition;
import it.immobiliare365.models.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertionResponseDTO {

    private Long id;

    private String title;

    private String description;

    private PropertyType propertyType;

    private ListingType listingType;

    private BigDecimal price;

    // Location details
    private String address;
    private String city;
    private String province;
    private String postalCode;

    // Property size
    private Double totalArea;
    private Double livingArea;

    // Rooms
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer additionalRooms;

    // Floor Information
    private Integer floorNumber;
    private Integer totalFloors;

    private Integer yearBuilt;

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

    private List<SavedImage> photos;

    // Managed internally
    private Date dateListed;
    private InsertionStatus status;
}

