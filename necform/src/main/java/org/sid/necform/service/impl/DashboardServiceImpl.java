package org.sid.necform.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.necform.dto.response.DashboardResponse;
import org.sid.necform.repository.*;
import org.sid.necform.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final FormationRepository formationRepository;

    private final SessionFormationRepository sessionRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final InscriptionRepository inscriptionRepository;

    private final EntrepriseRepository entrepriseRepository;

    private final DemandeFormationRepository demandeRepository;

    @Override
    public DashboardResponse getDashboard() {

        return new DashboardResponse(

                formationRepository.count(),

                sessionRepository.count(),

                utilisateurRepository.count(),

                inscriptionRepository.count(),

                entrepriseRepository.count(),

                demandeRepository.count()

        );

    }

}