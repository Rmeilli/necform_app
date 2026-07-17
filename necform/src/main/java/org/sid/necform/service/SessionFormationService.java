package org.sid.necform.service;

import org.sid.necform.dto.request.CreateSessionFormationRequest;
import org.sid.necform.dto.response.SessionFormationResponse;

import java.util.List;
import java.util.UUID;

public interface SessionFormationService {

    SessionFormationResponse create(CreateSessionFormationRequest request);

    List<SessionFormationResponse> getAll();

    SessionFormationResponse getById(UUID id);

    SessionFormationResponse update(UUID id, CreateSessionFormationRequest request);

    void delete(UUID id);
}