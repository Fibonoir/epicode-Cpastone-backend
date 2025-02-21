package it.immobiliare365.services;

import it.immobiliare365.DTOs.InsertionRequestDTO;
import it.immobiliare365.DTOs.InsertionUpdateRequestDTO;
import it.immobiliare365.DTOs.mapper.InsertionMapper;
import it.immobiliare365.models.Insertion;
import it.immobiliare365.models.enums.InsertionStatus;
import it.immobiliare365.repositories.InsertionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InsertionService {

    private final InsertionRepository insertionRepository;
    private final InsertionMapper insertionMapper;

    public Insertion createInsertion(InsertionRequestDTO dto) {
        Insertion insertion = insertionMapper.fromRequestDTO(dto);


        insertion.setDateListed(new Date());
        insertion.setStatus(InsertionStatus.AVAILABLE);


        return insertionRepository.save(insertion);
    }

    public List<Insertion> getAllInsertions() {
        return insertionRepository.findAll();
    }

    public Insertion updateInsertion(Long id, InsertionUpdateRequestDTO dto) {
        Insertion insertion = insertionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Insertion not found"));

        insertion = insertionMapper.updateFromRequestDTO(insertion, dto);
        insertion.setDateListed(new Date());

        return insertionRepository.save(insertion);
    }

    public void deleteInsertion(Long id) {
        Insertion existingInsertion = getInsertionById(id);
        insertionRepository.delete(existingInsertion);
    }

    public Insertion getInsertionById(Long id) {
        return insertionRepository.findById(id).orElse(null);
    }

    public Insertion getInsertionByTitle(String title) {
        return insertionRepository.findByTitle(title).orElse(null);
    }

    public Insertion getInsertionByAddress(String address) {
        return insertionRepository.findByAddress(address).orElse(null);
    }

    public List<Insertion> getInsertionsByCity(String city) {
        return insertionRepository.findByCity(city);
    }

    public List<Insertion> getInsertionsByProvince(String province) {
        return insertionRepository.findByProvince(province);
    }

    public List<Insertion> getInsertionByPrice(Double price) {
        return insertionRepository.findByPrice(price);
    }

    public List<Insertion> getInsertionByPropertyType(String propertyType) {
        return insertionRepository.findByPropertyType(propertyType);
    }

    public List<Insertion> getInsertionByListingType(String listingType) {
        return insertionRepository.findByListingType(listingType);
    }
}
