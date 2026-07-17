package org.sid.necform.mapper;

import org.sid.necform.dto.request.CreateUtilisateurRequest;
import org.sid.necform.dto.response.UtilisateurResponse;
import org.sid.necform.entity.Utilisateur;

public class UtilisateurMapper {

    public static Utilisateur toEntity(CreateUtilisateurRequest request) {
        return Utilisateur.builder()
                .keycloakUserId(request.keycloakUserId())
                .nom(request.nom())
                .prenom(request.prenom())
                .email(request.email())
                .type(request.type())
                .build();
    }

    public static UtilisateurResponse toResponse(Utilisateur utilisateur) {
        return new UtilisateurResponse(
                utilisateur.getId(),
                utilisateur.getKeycloakUserId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getType()
        );
    }
}
