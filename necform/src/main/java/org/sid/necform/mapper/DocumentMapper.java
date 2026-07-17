package org.sid.necform.mapper;

import org.sid.necform.dto.response.DocumentResponse;
import org.sid.necform.entity.Document;

public class DocumentMapper {

    public static DocumentResponse toResponse(Document doc){

        return new DocumentResponse(
                doc.getId(),
                doc.getType(),
                doc.getNomFichier(),
                doc.getCheminFichier(),
                doc.getDateGeneration(),

                doc.getSessionFormation()!=null
                        ? doc.getSessionFormation().getId()
                        : null,

                doc.getUtilisateur()!=null
                        ? doc.getUtilisateur().getId()
                        : null
        );

    }

}