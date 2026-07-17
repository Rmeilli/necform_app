package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateInscriptionRequest;
import org.sid.necform.dto.request.UpdateInscriptionRequest;
import org.sid.necform.dto.response.InscriptionResponse;
import org.sid.necform.service.InscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @PostMapping
    public InscriptionResponse create(@Valid @RequestBody CreateInscriptionRequest request) {
        return inscriptionService.create(request);
    }

    @GetMapping
    public List<InscriptionResponse> getAll() {
        return inscriptionService.getAll();
    }

    @GetMapping("/{id}")
    public InscriptionResponse getById(@PathVariable UUID id) {
        return inscriptionService.getById(id);
    }

    @PutMapping("/{id}")
    public InscriptionResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateInscriptionRequest request
    ) {
        return inscriptionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        inscriptionService.delete(id);
    }
}