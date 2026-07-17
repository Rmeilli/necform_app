package org.sid.necform.dto.response;

import org.sid.necform.entity.SessionStatut;

import java.time.LocalDate;
import java.util.UUID;

public record SessionFormationResponse(

        UUID id,

        LocalDate dateDebut,

        LocalDate dateFin,

        String lieu,

        SessionStatut statut,

        UUID formationId,

        String formationTitre,

        UUID formateurId,

        String formateurNom,

        String formateurPrenom
) {
}