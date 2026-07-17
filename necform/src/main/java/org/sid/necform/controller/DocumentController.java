package org.sid.necform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateDocumentRequest;
import org.sid.necform.dto.response.DocumentResponse;
import org.sid.necform.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @PostMapping
    public DocumentResponse create(
            @RequestBody
            @Valid
            CreateDocumentRequest request
    ){

        return service.create(
                request
        );

    }

    @GetMapping
    public List<DocumentResponse>
    getAll(){

        return service.getAll();

    }

    @GetMapping("/{id}")
    public DocumentResponse
    getById(
            @PathVariable
            UUID id
    ){

        return service.getById(
                id
        );

    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable
            UUID id
    ){

        service.delete(
                id
        );

    }

}