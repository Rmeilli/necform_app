package org.sid.necform.service;

import org.sid.necform.dto.request.CreateDocumentRequest;
import org.sid.necform.dto.response.DocumentResponse;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    DocumentResponse create(
            CreateDocumentRequest request
    );

    List<DocumentResponse> getAll();

    DocumentResponse getById(
            UUID id
    );

    void delete(
            UUID id
    );

}