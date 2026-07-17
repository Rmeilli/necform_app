package org.sid.necform.service;

import org.sid.necform.dto.request.CreateEntrepriseRequest;
import org.sid.necform.dto.request.UpdateEntrepriseRequest;
import org.sid.necform.dto.response.EntrepriseResponse;

import java.util.List;
import java.util.UUID;

public interface EntrepriseService {

    EntrepriseResponse create(CreateEntrepriseRequest request);

    List<EntrepriseResponse> getAll();

    EntrepriseResponse getById(UUID id);

    EntrepriseResponse update(UUID id, UpdateEntrepriseRequest request);

    void delete(UUID id);
}