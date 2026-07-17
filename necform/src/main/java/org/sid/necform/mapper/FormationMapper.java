package org.sid.necform.mapper;


import org.sid.necform.dto.request.CreateFormationRequest;
import org.sid.necform.dto.response.FormationResponse;
import org.sid.necform.entity.Formation;

public class FormationMapper {

    public static Formation toEntity(
            CreateFormationRequest dto
    ){

        return Formation.builder()

                .titre(dto.titre())
                .categorie(dto.categorie())
                .description(dto.description())
                .dureeHeures(dto.dureeHeures())
                .prix(dto.prix())

                .build();
    }

    public static FormationResponse toResponse(
            Formation f
    ){

        return new FormationResponse(

                f.getId(),
                f.getTitre(),
                f.getCategorie(),
                f.getDescription(),
                f.getDureeHeures(),
                f.getPrix()
        );

    }

}
