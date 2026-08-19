package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateUtilisateurRequest;
import org.sid.necform.dto.request.UpdateUtilisateurRequest;
import org.sid.necform.dto.response.UtilisateurResponse;
import org.sid.necform.entity.Document;
import org.sid.necform.entity.Inscription;
import org.sid.necform.entity.SessionFormation;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.mapper.UtilisateurMapper;
import org.sid.necform.repository.DocumentRepository;
import org.sid.necform.repository.InscriptionRepository;
import org.sid.necform.repository.SessionFormationRepository;
import org.sid.necform.repository.UtilisateurRepository;
import org.sid.necform.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final InscriptionRepository inscriptionRepository;
    private final SessionFormationRepository sessionFormationRepository;
    private final DocumentRepository documentRepository;

    @Override
    public UtilisateurResponse create(CreateUtilisateurRequest request) {
        if (utilisateurRepository.existsByKeycloakUserId(request.keycloakUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "keycloakUserId déjà existant");
        }

        if (utilisateurRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email déjà existant");
        }

        Utilisateur utilisateur = UtilisateurMapper.toEntity(request);
        utilisateur = utilisateurRepository.save(utilisateur);

        return UtilisateurMapper.toResponse(utilisateur);
    }

    @Override
    public List<UtilisateurResponse> getAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurMapper::toResponse)
                .toList();
    }

    @Override
    public UtilisateurResponse getById(UUID id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        return UtilisateurMapper.toResponse(utilisateur);
    }

    @Override
    public UtilisateurResponse update(UUID id, UpdateUtilisateurRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        if (!utilisateur.getEmail().equals(request.email()) && utilisateurRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email déjà existant");
        }

        utilisateur.setNom(request.nom());
        utilisateur.setPrenom(request.prenom());
        utilisateur.setEmail(request.email());
        utilisateur.setType(request.type());

        utilisateur = utilisateurRepository.save(utilisateur);

        return UtilisateurMapper.toResponse(utilisateur);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        System.out.println("=== DELETE UTILISATEUR ===");
        System.out.println("ID à supprimer: " + id);
        
        if (!utilisateurRepository.existsById(id)) {
            System.out.println("Utilisateur introuvable");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        }

        // Supprimer ou mettre à null les inscriptions liées à cet utilisateur (apprenant)
        System.out.println("Recherche des inscriptions pour l'apprenant...");
        List<Inscription> inscriptions = inscriptionRepository.findByApprenantId(id);
        System.out.println("Inscriptions trouvées: " + inscriptions.size());
        inscriptionRepository.deleteAll(inscriptions);
        System.out.println("Inscriptions supprimées");

        // Mettre à null les sessions de formation liées à cet utilisateur (formateur)
        System.out.println("Recherche des sessions pour le formateur...");
        List<SessionFormation> sessions = sessionFormationRepository.findByFormateurId(id);
        System.out.println("Sessions trouvées: " + sessions.size());
        for (SessionFormation session : sessions) {
            System.out.println("Session ID: " + session.getId() + ", formateur avant: " + session.getFormateur());
            session.setFormateur(null);
            sessionFormationRepository.save(session);
            System.out.println("Session ID: " + session.getId() + ", formateur après: null");
        }
        System.out.println("Sessions mises à jour");

        // Mettre à null les documents liés à cet utilisateur
        System.out.println("Recherche des documents pour l'utilisateur...");
        List<Document> documents = documentRepository.findByUtilisateurId(id);
        System.out.println("Documents trouvés: " + documents.size());
        for (Document document : documents) {
            document.setUtilisateur(null);
            documentRepository.save(document);
        }
        System.out.println("Documents mis à jour");

        // Maintenant supprimer l'utilisateur
        System.out.println("Suppression de l'utilisateur...");
        utilisateurRepository.deleteById(id);
        System.out.println("Utilisateur supprimé avec succès");
    }
}
