package org.sid.necform.dto.request;

import jakarta.validation.constraints.NotNull;
import org.sid.necform.entity.StatutDemande;

public record UpdateDemandeFormationRequest(

        @NotNull
        Integer nombreParticipants,

        String besoin,

        @NotNull
        StatutDemande statut
) {
}