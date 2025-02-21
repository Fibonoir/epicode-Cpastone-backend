package it.immobiliare365.models;

import it.immobiliare365.models.enums.InsertionStatus;
import it.immobiliare365.models.enums.ListingType;
import it.immobiliare365.models.enums.PropertyCondition;
import it.immobiliare365.models.enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "insertion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insertion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
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

    @Enumerated(EnumType.STRING)
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "insertion_photos", joinColumns = @JoinColumn(name = "insertion_id"))
    private List<SavedImage> photos;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateListed;

    @Enumerated(EnumType.STRING)
    private InsertionStatus status;

}
