package org.sid.necform.mapper;

import org.sid.necform.dto.response.InscriptionResponse;
import org.sid.necform.entity.Inscription;

public class InscriptionMapper {

    public static InscriptionResponse toResponse(Inscription inscription) {
        return new InscriptionResponse(
                inscription.getId(),
                inscription.getStatut(),
                inscription.getDateInscription(),
                inscription.getSessionFormation() != null ? inscription.getSessionFormation().getId() : null,
                inscription.getSessionFormation() != null && inscription.getSessionFormation().getFormation() != null
                        ? inscription.getSessionFormation().getFormation().getTitre()
                        : null,
                inscription.getApprenant() != null ? inscription.getApprenant().getId() : null,
                inscription.getApprenant() != null ? inscription.getApprenant().getNom() : null,
                inscription.getApprenant() != null ? inscription.getApprenant().getPrenom() : null,
                inscription.getApprenant() != null ? inscription.getApprenant().getEmail() : null
        );
    }
}