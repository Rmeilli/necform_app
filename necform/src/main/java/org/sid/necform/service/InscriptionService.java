package org.sid.necform.service;

import org.sid.necform.dto.request.CreateInscriptionRequest;
import org.sid.necform.dto.request.UpdateInscriptionRequest;
import org.sid.necform.dto.response.InscriptionResponse;

import java.util.List;
import java.util.UUID;

public interface InscriptionService {

    InscriptionResponse create(CreateInscriptionRequest request);

    List<InscriptionResponse> getAll();

    InscriptionResponse getById(UUID id);

    InscriptionResponse update(UUID id, UpdateInscriptionRequest request);

    void delete(UUID id);
}