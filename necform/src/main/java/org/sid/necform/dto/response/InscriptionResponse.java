package org.sid.necform.dto.response;

import org.sid.necform.entity.StatutInscription;

import java.time.LocalDate;
import java.util.UUID;

public record InscriptionResponse(

        UUID id,

        StatutInscription statut,

        LocalDate dateInscription,

        UUID sessionFormationId,

        String sessionFormationFormationTitre,

        UUID apprenantId,

        String apprenantNom,

        String apprenantPrenom,
        String apprenantEmail
) {
}
