package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateDemandeFormationRequest;
import org.sid.necform.dto.request.UpdateDemandeFormationRequest;
import org.sid.necform.dto.response.DemandeFormationResponse;
import org.sid.necform.service.DemandeFormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/demandes-formations")
@RequiredArgsConstructor
public class DemandeFormationController {

    private final DemandeFormationService demandeService;

    @PostMapping
    public DemandeFormationResponse create(@Valid @RequestBody CreateDemandeFormationRequest request) {
        return demandeService.create(request);
    }

    @GetMapping
    public List<DemandeFormationResponse> getAll() {
        return demandeService.getAll();
    }

    @GetMapping("/{id}")
    public DemandeFormationResponse getById(@PathVariable UUID id) {
        return demandeService.getById(id);
    }

    @PutMapping("/{id}")
    public DemandeFormationResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDemandeFormationRequest request
    ) {
        return demandeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        demandeService.delete(id);
    }
}