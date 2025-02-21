package it.immobiliare365.repositories;

import it.immobiliare365.models.Insertion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsertionRepository extends JpaRepository<Insertion, Long> {
    Optional<Insertion> findById(Long id);
    Optional<Insertion> findByTitle(String title);
    Optional<Insertion> findByAddress(String address);
    List<Insertion> findByCity(String city);
    List<Insertion> findByProvince(String province);
    List<Insertion> findByPrice(Double price);
    List<Insertion> findByPropertyType(String propertyType);
    List<Insertion> findByListingType(String listingType);

}
