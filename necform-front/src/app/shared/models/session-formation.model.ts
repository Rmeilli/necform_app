import { SessionStatut } from './enums';

// Interface correspondant à SessionFormationResponse du backend
export interface SessionFormation {
  id: string;
  dateDebut: string;
  dateFin: string;
  lieu: string;
  statut: SessionStatut;
  formationId: string;
  formationTitre: string;
  formateurId: string;
  formateurNom: string;
  formateurPrenom: string;
}

// Interface pour créer une session de formation
export interface CreateSessionFormation {
  dateDebut: string;
  dateFin: string;
  lieu: string;
  statut: SessionStatut;
  formationId: string;
  formateurId: string;
}
