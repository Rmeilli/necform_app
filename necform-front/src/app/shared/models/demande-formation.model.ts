import { StatutDemande } from './enums';

// Interface correspondant à DemandeFormationResponse du backend
// C'est ce que l'API nous renvoie quand on demande une demande de formation
export interface DemandeFormation {
  id: string; // UUID converti en string en TypeScript
  dateDemande: string; // LocalDate converti en string (format ISO)
  nombreParticipants: number;
  besoin: string;
  statut: StatutDemande;
  entrepriseId: string;
  entrepriseRaisonSociale: string;
  formationId: string;
  formationTitre: string;
}

// Interface pour créer une nouvelle demande de formation
// Correspond à CreateDemandeFormationRequest du backend
export interface CreateDemandeFormation {
  dateDemande: string;
  nombreParticipants: number;
  besoin: string;
  statut: StatutDemande;
  entrepriseId: string;
  formationId: string;
}

// Interface pour mettre à jour une demande de formation
// Correspond à UpdateDemandeFormationRequest du backend
export interface UpdateDemandeFormation {
  nombreParticipants?: number;
  besoin?: string;
  statut?: StatutDemande;
}
