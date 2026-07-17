package org.sid.necform.service;


import org.sid.necform.dto.request.CreateFormationRequest;
import org.sid.necform.dto.response.FormationResponse;

import java.util.List;
import java.util.UUID;

public interface FormationService {

    FormationResponse create(
            CreateFormationRequest request
    );

    List<FormationResponse> getAll();

    FormationResponse getById(
            UUID id
    );

    void delete(
            UUID id
    );

}