package org.sid.necform.dto.request;

import jakarta.validation.constraints.NotNull;
import org.sid.necform.entity.StatutDemande;

import java.time.LocalDate;
import java.util.UUID;

public record CreateDemandeFormationRequest(

        @NotNull
        LocalDate dateDemande,

        @NotNull
        Integer nombreParticipants,

        String besoin,

        @NotNull
        StatutDemande statut,

        @NotNull
        UUID entrepriseId,

        @NotNull
        UUID formationId
) {
}