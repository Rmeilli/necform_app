package org.sid.necform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEntrepriseRequest(

        @NotBlank
        String raisonSociale,

        String adresse,

        String telephone,

        @Email
        String email
) {
}