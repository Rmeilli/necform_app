package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateUtilisateurRequest;
import org.sid.necform.dto.request.UpdateUtilisateurRequest;
import org.sid.necform.dto.response.UtilisateurResponse;
import org.sid.necform.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public UtilisateurResponse create(@Valid @RequestBody CreateUtilisateurRequest request) {
        return utilisateurService.create(request);
    }

    @GetMapping
    public List<UtilisateurResponse> getAll() {
        return utilisateurService.getAll();
    }

    @GetMapping("/{id}")
    public UtilisateurResponse getById(@PathVariable UUID id) {
        return utilisateurService.getById(id);
    }

    @PutMapping("/{id}")
    public UtilisateurResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUtilisateurRequest request
    ) {
        return utilisateurService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        utilisateurService.delete(id);
    }
}
