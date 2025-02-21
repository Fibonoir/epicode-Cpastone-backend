package it.immobiliare365.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.immobiliare365.DTOs.InsertionRequestDTO;
import it.immobiliare365.DTOs.InsertionResponseDTO;
import it.immobiliare365.DTOs.InsertionUpdateRequestDTO;
import it.immobiliare365.DTOs.mapper.InsertionMapper;
import it.immobiliare365.models.Insertion;
import it.immobiliare365.services.InsertionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/insertions")
@RequiredArgsConstructor
public class InsertionController {

    private final InsertionService insertionService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InsertionResponseDTO> createInsertion(
            @ModelAttribute InsertionRequestDTO insertionRequestDTO) {
        Insertion createdInsertion = insertionService.createInsertion(insertionRequestDTO);
        InsertionResponseDTO responseDTO = InsertionMapper.toResponseDTO(createdInsertion);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdInsertion.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsertionResponseDTO> getInsertion(@PathVariable Long id) {
        Insertion insertion = insertionService.getInsertionById(id);
        return ResponseEntity.ok(InsertionMapper.toResponseDTO(insertion));
    }

    @GetMapping
    public ResponseEntity<List<Insertion>> getAllInsertions() {
        List<Insertion> insertions = new ArrayList<>(insertionService.getAllInsertions());
        return ResponseEntity.ok(insertions);
    }
    

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InsertionResponseDTO> updateInsertion(
            @PathVariable Long id,
            @RequestPart("dto") InsertionUpdateRequestDTO dto,
            @RequestPart(value = "newPhotos", required = false) List<MultipartFile> newPhotos) {
        dto.setNewPhotos(newPhotos);

        Insertion updatedInsertion = insertionService.updateInsertion(id, dto);
        InsertionResponseDTO responseDTO = InsertionMapper.toResponseDTO(updatedInsertion);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsertion(@PathVariable Long id) {
        insertionService.deleteInsertion(id);
        return ResponseEntity.noContent().build();
    }
}
