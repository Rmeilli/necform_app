package org.sid.necform.dto.response;

import java.util.UUID;

public record EntrepriseResponse(

        UUID id,

        String raisonSociale,

        String adresse,

        String telephone,

        String email
) {
}