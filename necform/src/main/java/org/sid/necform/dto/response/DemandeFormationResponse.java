package org.sid.necform.dto.response;

import org.sid.necform.entity.StatutDemande;

import java.time.LocalDate;
import java.util.UUID;

public record DemandeFormationResponse(

        UUID id,

        LocalDate dateDemande,

        Integer nombreParticipants,

        String besoin,

        StatutDemande statut,

        UUID entrepriseId,

        String entrepriseRaisonSociale,

        UUID formationId,

        String formationTitre
) {
}