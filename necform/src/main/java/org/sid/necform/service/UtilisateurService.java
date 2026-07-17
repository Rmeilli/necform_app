package org.sid.necform.service;

import org.sid.necform.dto.request.CreateUtilisateurRequest;
import org.sid.necform.dto.request.UpdateUtilisateurRequest;
import org.sid.necform.dto.response.UtilisateurResponse;

import java.util.List;
import java.util.UUID;

public interface UtilisateurService {

    UtilisateurResponse create(CreateUtilisateurRequest request);

    List<UtilisateurResponse> getAll();

    UtilisateurResponse getById(UUID id);

    UtilisateurResponse update(UUID id, UpdateUtilisateurRequest request);

    void delete(UUID id);
}
