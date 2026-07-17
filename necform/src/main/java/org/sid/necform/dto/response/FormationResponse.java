package org.sid.necform.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record FormationResponse(

        UUID id,

        String titre,

        String categorie,

        String description,

        Integer dureeHeures,

        BigDecimal prix

) {}
