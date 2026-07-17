package org.sid.necform.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sid.necform.entity.TypeDocument;

import java.time.LocalDate;
import java.util.UUID;

public record CreateDocumentRequest(

        @NotNull
        TypeDocument type,

        @NotBlank
        String nomFichier,

        String cheminFichier,

        LocalDate dateGeneration,

        UUID sessionId,

        UUID utilisateurId

) {
}