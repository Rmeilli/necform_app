package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateEntrepriseRequest;
import org.sid.necform.dto.request.UpdateEntrepriseRequest;
import org.sid.necform.dto.response.EntrepriseResponse;
import org.sid.necform.entity.Entreprise;
import org.sid.necform.mapper.EntrepriseMapper;
import org.sid.necform.repository.EntrepriseRepository;
import org.sid.necform.service.EntrepriseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Override
    public EntrepriseResponse create(CreateEntrepriseRequest request) {
        Entreprise entreprise = EntrepriseMapper.toEntity(request);
        entreprise = entrepriseRepository.save(entreprise);
        return EntrepriseMapper.toResponse(entreprise);
    }

    @Override
    public List<EntrepriseResponse> getAll() {
        return entrepriseRepository.findAll()
                .stream()
                .map(EntrepriseMapper::toResponse)
                .toList();
    }

    @Override
    public EntrepriseResponse getById(UUID id) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise introuvable"));

        return EntrepriseMapper.toResponse(entreprise);
    }

    @Override
    public EntrepriseResponse update(UUID id, UpdateEntrepriseRequest request) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise introuvable"));

        entreprise.setRaisonSociale(request.raisonSociale());
        entreprise.setAdresse(request.adresse());
        entreprise.setTelephone(request.telephone());
        entreprise.setEmail(request.email());

        entreprise = entrepriseRepository.save(entreprise);

        return EntrepriseMapper.toResponse(entreprise);
    }

    @Override
    public void delete(UUID id) {
        if (!entrepriseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise introuvable");
        }
        entrepriseRepository.deleteById(id);
    }
}