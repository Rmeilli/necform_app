package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateSessionFormationRequest;
import org.sid.necform.dto.response.SessionFormationResponse;
import org.sid.necform.entity.Formation;
import org.sid.necform.entity.SessionFormation;
import org.sid.necform.entity.TypeUtilisateur;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.mapper.SessionFormationMapper;
import org.sid.necform.repository.FormationRepository;
import org.sid.necform.repository.SessionFormationRepository;
import org.sid.necform.repository.UtilisateurRepository;
import org.sid.necform.service.SessionFormationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionFormationServiceImpl implements SessionFormationService {

    private final SessionFormationRepository sessionRepository;
    private final FormationRepository formationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public SessionFormationResponse create(CreateSessionFormationRequest request) {
        Formation formation = formationRepository.findById(request.formationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation introuvable"));

        Utilisateur formateur = null;
        if (request.formateurId() != null) {
            formateur = utilisateurRepository.findById(request.formateurId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formateur introuvable"));

            if (formateur.getType() != TypeUtilisateur.FORMATEUR) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur sélectionné n'est pas un formateur");
            }
        }

        SessionFormation session = SessionFormation.builder()
                .dateDebut(request.dateDebut())
                .dateFin(request.dateFin())
                .lieu(request.lieu())
                .statut(request.statut())
                .formation(formation)
                .formateur(formateur)
                .build();

        session = sessionRepository.save(session);

        return SessionFormationMapper.toResponse(session);
    }

    @Override
    public List<SessionFormationResponse> getAll() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionFormationMapper::toResponse)
                .toList();
    }

    @Override
    public SessionFormationResponse getById(UUID id) {
        SessionFormation session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session introuvable"));

        return SessionFormationMapper.toResponse(session);
    }

    @Override
    public SessionFormationResponse update(UUID id, CreateSessionFormationRequest request) {
        SessionFormation session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session introuvable"));

        Formation formation = formationRepository.findById(request.formationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation introuvable"));

        Utilisateur formateur = null;
        if (request.formateurId() != null) {
            formateur = utilisateurRepository.findById(request.formateurId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formateur introuvable"));

            if (formateur.getType() != TypeUtilisateur.FORMATEUR) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur sélectionné n'est pas un formateur");
            }
        }

        session.setDateDebut(request.dateDebut());
        session.setDateFin(request.dateFin());
        session.setLieu(request.lieu());
        session.setStatut(request.statut());
        session.setFormation(formation);
        session.setFormateur(formateur);

        session = sessionRepository.save(session);

        return SessionFormationMapper.toResponse(session);
    }

    @Override
    public void delete(UUID id) {
        if (!sessionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session introuvable");
        }
        sessionRepository.deleteById(id);
    }
}