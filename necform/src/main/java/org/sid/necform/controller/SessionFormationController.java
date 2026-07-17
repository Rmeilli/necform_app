package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateSessionFormationRequest;
import org.sid.necform.dto.response.SessionFormationResponse;
import org.sid.necform.service.SessionFormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionFormationController {

    private final SessionFormationService sessionService;

    @PostMapping
    public SessionFormationResponse create(@Valid @RequestBody CreateSessionFormationRequest request) {
        return sessionService.create(request);
    }

    @GetMapping
    public List<SessionFormationResponse> getAll() {
        return sessionService.getAll();
    }

    @GetMapping("/{id}")
    public SessionFormationResponse getById(@PathVariable UUID id) {
        return sessionService.getById(id);
    }

    @PutMapping("/{id}")
    public SessionFormationResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody CreateSessionFormationRequest request
    ) {
        return sessionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        sessionService.delete(id);
    }
}