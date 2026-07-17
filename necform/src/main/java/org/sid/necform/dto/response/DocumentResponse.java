package org.sid.necform.dto.response;

import org.sid.necform.entity.TypeDocument;

import java.time.LocalDate;
import java.util.UUID;

public record DocumentResponse(

        UUID id,

        TypeDocument type,

        String nomFichier,

        String cheminFichier,

        LocalDate dateGeneration,

        UUID sessionId,

        UUID utilisateurId

) {
}