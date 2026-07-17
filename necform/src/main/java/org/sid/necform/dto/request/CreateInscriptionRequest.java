package org.sid.necform.dto.request;

import org.sid.necform.entity.StatutInscription;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateInscriptionRequest(

        @NotNull
        LocalDate dateInscription,

        @NotNull
        StatutInscription statut,

        @NotNull
        UUID sessionFormationId,

        @NotNull
        UUID apprenantId
) {
}