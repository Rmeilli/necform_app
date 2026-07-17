package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.request.CreateUtilisateurRequest;
import org.sid.necform.dto.request.UpdateUtilisateurRequest;
import org.sid.necform.dto.response.UtilisateurResponse;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.mapper.UtilisateurMapper;
import org.sid.necform.repository.UtilisateurRepository;
import org.sid.necform.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

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
    public void delete(UUID id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        }
        utilisateurRepository.deleteById(id);
    }
}
