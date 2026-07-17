package org.sid.necform.dto.request;

import jakarta.validation.constraints.NotNull;
import org.sid.necform.entity.SessionStatut;

import java.time.LocalDate;
import java.util.UUID;

public record CreateSessionFormationRequest(

        @NotNull
        LocalDate dateDebut,

        @NotNull
        LocalDate dateFin,

        String lieu,

        @NotNull
        SessionStatut statut,

        @NotNull
        UUID formationId,

        UUID formateurId
) {
}