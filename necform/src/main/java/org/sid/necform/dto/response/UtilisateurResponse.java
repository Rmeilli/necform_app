package org.sid.necform.dto.response;

import org.sid.necform.entity.TypeUtilisateur;

import java.util.UUID;

public record UtilisateurResponse(

        UUID id,

        String keycloakUserId,

        String nom,

        String prenom,

        String email,

        TypeUtilisateur type
) {
}
