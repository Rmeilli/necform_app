package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateEntrepriseRequest;
import org.sid.necform.dto.request.UpdateEntrepriseRequest;
import org.sid.necform.dto.response.EntrepriseResponse;
import org.sid.necform.service.EntrepriseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @PostMapping
    public EntrepriseResponse create(@Valid @RequestBody CreateEntrepriseRequest request) {
        return entrepriseService.create(request);
    }

    @GetMapping
    public List<EntrepriseResponse> getAll() {
        return entrepriseService.getAll();
    }

    @GetMapping("/{id}")
    public EntrepriseResponse getById(@PathVariable UUID id) {
        return entrepriseService.getById(id);
    }

    @PutMapping("/{id}")
    public EntrepriseResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEntrepriseRequest request
    ) {
        return entrepriseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        entrepriseService.delete(id);
    }
}