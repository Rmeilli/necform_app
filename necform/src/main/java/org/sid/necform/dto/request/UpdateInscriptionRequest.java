package org.sid.necform.dto.request;

import org.sid.necform.entity.StatutInscription;

import jakarta.validation.constraints.NotNull;

public record UpdateInscriptionRequest(

        @NotNull
        StatutInscription statut
) {
}