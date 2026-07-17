package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateDocumentRequest;
import org.sid.necform.dto.response.DocumentResponse;
import org.sid.necform.entity.Document;
import org.sid.necform.entity.SessionFormation;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.mapper.DocumentMapper;
import org.sid.necform.repository.DocumentRepository;
import org.sid.necform.repository.SessionFormationRepository;
import org.sid.necform.repository.UtilisateurRepository;
import org.sid.necform.service.DocumentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl
        implements DocumentService {

    private final DocumentRepository repository;

    private final SessionFormationRepository sessionRepo;

    private final UtilisateurRepository userRepo;

    @Override
    public DocumentResponse create(
            CreateDocumentRequest request
    ) {

        SessionFormation session =
                request.sessionId()!=null
                        ? sessionRepo.findById(
                        request.sessionId()
                ).orElse(null)
                        : null;

        Utilisateur user =
                request.utilisateurId()!=null
                        ? userRepo.findById(
                        request.utilisateurId()
                ).orElse(null)
                        : null;

        Document document =
                Document.builder()
                        .type(request.type())
                        .nomFichier(
                                request.nomFichier()
                        )
                        .cheminFichier(
                                request.cheminFichier()
                        )
                        .dateGeneration(
                                request.dateGeneration()!=null
                                        ? request.dateGeneration()
                                        : LocalDate.now()
                        )
                        .sessionFormation(
                                session
                        )
                        .utilisateur(
                                user
                        )
                        .build();

        repository.save(
                document
        );

        return DocumentMapper
                .toResponse(document);

    }

    @Override
    public List<DocumentResponse>
    getAll() {

        return repository
                .findAll()
                .stream()
                .map(
                        DocumentMapper::toResponse
                )
                .toList();

    }

    @Override
    public DocumentResponse
    getById(
            UUID id
    ) {

        return DocumentMapper
                .toResponse(
                        repository
                                .findById(id)
                                .orElseThrow()
                );

    }

    @Override
    public void delete(
            UUID id
    ) {

        repository.deleteById(
                id
        );

    }

}