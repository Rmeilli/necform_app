import { TypeUtilisateur } from './enums';

// Interface correspondant à UtilisateurResponse du backend
export interface Utilisateur {
  id: string;
  keycloakUserId: string;
  nom: string;
  prenom: string;
  email: string;
  type: TypeUtilisateur;
}

// Interface pour créer un utilisateur
export interface CreateUtilisateur {
  keycloakUserId: string;
  nom: string;
  prenom: string;
  email: string;
  type: TypeUtilisateur;
}

// Interface pour mettre à jour un utilisateur
export interface UpdateUtilisateur {
  nom?: string;
  prenom?: string;
  email?: string;
  type?: TypeUtilisateur;
}
