package org.sid.necform.service;

import org.sid.necform.dto.request.CreateDemandeFormationRequest;
import org.sid.necform.dto.request.UpdateDemandeFormationRequest;
import org.sid.necform.dto.response.DemandeFormationResponse;

import java.util.List;
import java.util.UUID;

public interface DemandeFormationService {

    DemandeFormationResponse create(CreateDemandeFormationRequest request);

    List<DemandeFormationResponse> getAll();

    DemandeFormationResponse getById(UUID id);

    DemandeFormationResponse update(UUID id, UpdateDemandeFormationRequest request);

    void delete(UUID id);
}