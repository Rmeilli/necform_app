package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateInscriptionRequest;
import org.sid.necform.dto.request.UpdateInscriptionRequest;
import org.sid.necform.dto.response.InscriptionResponse;
import org.sid.necform.entity.Inscription;
import org.sid.necform.entity.SessionFormation;
import org.sid.necform.entity.TypeUtilisateur;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.mapper.InscriptionMapper;
import org.sid.necform.repository.InscriptionRepository;
import org.sid.necform.repository.SessionFormationRepository;
import org.sid.necform.repository.UtilisateurRepository;
import org.sid.necform.service.InscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final SessionFormationRepository sessionFormationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public InscriptionResponse create(CreateInscriptionRequest request) {
        SessionFormation session = sessionFormationRepository.findById(request.sessionFormationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session introuvable"));

        Utilisateur apprenant = utilisateurRepository.findById(request.apprenantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Apprenant introuvable"));

        if (apprenant.getType() != TypeUtilisateur.APPRENANT) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur sélectionné n'est pas un apprenant");
        }

        Inscription inscription = Inscription.builder()
                .dateInscription(request.dateInscription())
                .statut(request.statut())
                .sessionFormation(session)
                .apprenant(apprenant)
                .build();

        inscription = inscriptionRepository.save(inscription);

        return InscriptionMapper.toResponse(inscription);
    }

    @Override
    public List<InscriptionResponse> getAll() {
        return inscriptionRepository.findAll()
                .stream()
                .map(InscriptionMapper::toResponse)
                .toList();
    }

    @Override
    public InscriptionResponse getById(UUID id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscription introuvable"));

        return InscriptionMapper.toResponse(inscription);
    }

    @Override
    public InscriptionResponse update(UUID id, UpdateInscriptionRequest request) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscription introuvable"));

        inscription.setStatut(request.statut());
        inscription = inscriptionRepository.save(inscription);

        return InscriptionMapper.toResponse(inscription);
    }

    @Override
    public void delete(UUID id) {
        if (!inscriptionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscription introuvable");
        }
        inscriptionRepository.deleteById(id);
    }
}