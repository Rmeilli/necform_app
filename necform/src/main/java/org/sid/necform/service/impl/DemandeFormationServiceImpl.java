package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateDemandeFormationRequest;
import org.sid.necform.dto.request.UpdateDemandeFormationRequest;
import org.sid.necform.dto.response.DemandeFormationResponse;
import org.sid.necform.entity.DemandeFormation;
import org.sid.necform.entity.Entreprise;
import org.sid.necform.entity.Formation;
import org.sid.necform.mapper.DemandeFormationMapper;
import org.sid.necform.repository.DemandeFormationRepository;
import org.sid.necform.repository.EntrepriseRepository;
import org.sid.necform.repository.FormationRepository;
import org.sid.necform.service.DemandeFormationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DemandeFormationServiceImpl implements DemandeFormationService {

    private final DemandeFormationRepository demandeRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final FormationRepository formationRepository;

    @Override
    public DemandeFormationResponse create(CreateDemandeFormationRequest request) {
        Entreprise entreprise = entrepriseRepository.findById(request.entrepriseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise introuvable"));

        Formation formation = formationRepository.findById(request.formationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation introuvable"));

        DemandeFormation demande = DemandeFormation.builder()
                .dateDemande(request.dateDemande())
                .nombreParticipants(request.nombreParticipants())
                .besoin(request.besoin())
                .statut(request.statut())
                .entreprise(entreprise)
                .formation(formation)
                .build();

        demande = demandeRepository.save(demande);

        return DemandeFormationMapper.toResponse(demande);
    }

    @Override
    public List<DemandeFormationResponse> getAll() {
        return demandeRepository.findAll()
                .stream()
                .map(DemandeFormationMapper::toResponse)
                .toList();
    }

    @Override
    public DemandeFormationResponse getById(UUID id) {
        DemandeFormation demande = demandeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande introuvable"));

        return DemandeFormationMapper.toResponse(demande);
    }

    @Override
    public DemandeFormationResponse update(UUID id, UpdateDemandeFormationRequest request) {
        DemandeFormation demande = demandeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande introuvable"));

        demande.setNombreParticipants(request.nombreParticipants());
        demande.setBesoin(request.besoin());
        demande.setStatut(request.statut());

        demande = demandeRepository.save(demande);

        return DemandeFormationMapper.toResponse(demande);
    }

    @Override
    public void delete(UUID id) {
        if (!demandeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande introuvable");
        }
        demandeRepository.deleteById(id);
    }
}