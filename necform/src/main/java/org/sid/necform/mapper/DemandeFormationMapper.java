package org.sid.necform.mapper;

import org.sid.necform.dto.response.DemandeFormationResponse;
import org.sid.necform.entity.DemandeFormation;

public class DemandeFormationMapper {

    public static DemandeFormationResponse toResponse(DemandeFormation demande) {
        return new DemandeFormationResponse(
                demande.getId(),
                demande.getDateDemande(),
                demande.getNombreParticipants(),
                demande.getBesoin(),
                demande.getStatut(),
                demande.getEntreprise() != null ? demande.getEntreprise().getId() : null,
                demande.getEntreprise() != null ? demande.getEntreprise().getRaisonSociale() : null,
                demande.getFormation() != null ? demande.getFormation().getId() : null,
                demande.getFormation() != null ? demande.getFormation().getTitre() : null
        );
    }
}