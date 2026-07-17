// Enums correspondant aux entités Java du backend
// Les enums sont des types de données qui ne peuvent prendre qu'un ensemble limité de valeurs

export enum StatutDemande {
  EN_ATTENTE = 'EN_ATTENTE',
  VALIDEE = 'VALIDEE',
  REFUSEE = 'REFUSEE'
}

export enum StatutInscription {
  EN_ATTENTE = 'EN_ATTENTE',
  VALIDEE = 'VALIDEE',
  ANNULEE = 'ANNULEE'
}

export enum SessionStatut {
  PLANIFIEE = 'PLANIFIEE',
  EN_COURS = 'EN_COURS',
  TERMINEE = 'TERMINEE',
  ANNULEE = 'ANNULEE'
}

export enum TypeUtilisateur {
  ADMIN = 'ADMIN',
  FORMATEUR = 'FORMATEUR',
  APPRENANT = 'APPRENANT',
  ENTREPRISE = 'ENTREPRISE'
}
