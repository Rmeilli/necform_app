import { StatutInscription } from './enums';

// Interface correspondant à InscriptionResponse du backend
export interface Inscription {
  id: string;
  statut: StatutInscription;
  dateInscription: string;
  sessionFormationId: string;
  sessionFormationFormationTitre: string;
  apprenantId: string;
  apprenantNom: string;
  apprenantPrenom: string;
  apprenantEmail: string;
}

// Interface pour créer une inscription
export interface CreateInscription {
  statut: StatutInscription;
  dateInscription: string;
  sessionFormationId: string;
  apprenantId: string;
}

// Interface pour mettre à jour une inscription
export interface UpdateInscription {
  statut?: StatutInscription;
}
