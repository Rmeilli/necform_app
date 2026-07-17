package org.sid.necform.mapper;

import org.sid.necform.dto.response.SessionFormationResponse;
import org.sid.necform.entity.SessionFormation;

public class SessionFormationMapper {

    public static SessionFormationResponse toResponse(SessionFormation session) {
        return new SessionFormationResponse(
                session.getId(),
                session.getDateDebut(),
                session.getDateFin(),
                session.getLieu(),
                session.getStatut(),
                session.getFormation() != null ? session.getFormation().getId() : null,
                session.getFormation() != null ? session.getFormation().getTitre() : null,
                session.getFormateur() != null ? session.getFormateur().getId() : null,
                session.getFormateur() != null ? session.getFormateur().getNom() : null,
                session.getFormateur() != null ? session.getFormateur().getPrenom() : null
        );
    }
}