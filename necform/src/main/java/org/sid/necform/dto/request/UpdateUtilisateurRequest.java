package org.sid.necform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sid.necform.entity.TypeUtilisateur;

public record UpdateUtilisateurRequest(

        @NotBlank
        String nom,

        @NotBlank
        String prenom,

        @NotBlank
        @Email
        String email,

        @NotNull
        TypeUtilisateur type
) {
}