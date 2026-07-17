package org.sid.necform.mapper;

import org.sid.necform.dto.request.CreateEntrepriseRequest;
import org.sid.necform.dto.response.EntrepriseResponse;
import org.sid.necform.entity.Entreprise;

public class EntrepriseMapper {

    public static Entreprise toEntity(CreateEntrepriseRequest request) {
        return Entreprise.builder()
                .raisonSociale(request.raisonSociale())
                .adresse(request.adresse())
                .telephone(request.telephone())
                .email(request.email())
                .build();
    }

    public static EntrepriseResponse toResponse(Entreprise entreprise) {
        return new EntrepriseResponse(
                entreprise.getId(),
                entreprise.getRaisonSociale(),
                entreprise.getAdresse(),
                entreprise.getTelephone(),
                entreprise.getEmail()
        );
    }
}