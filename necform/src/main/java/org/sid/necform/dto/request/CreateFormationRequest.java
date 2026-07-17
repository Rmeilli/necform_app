package org.sid.necform.dto.request;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateFormationRequest(

        @NotBlank
        String titre,

        String categorie,

        String description,

        Integer dureeHeures,

        BigDecimal prix

) {}
