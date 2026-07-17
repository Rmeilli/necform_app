// Interface correspondant à EntrepriseResponse du backend
export interface Entreprise {
  id: string;
  raisonSociale: string;
  adresse: string;
  telephone: string;
  email: string;
}

// Interface pour créer une entreprise
export interface CreateEntreprise {
  raisonSociale: string;
  adresse: string;
  telephone: string;
  email: string;
}

// Interface pour mettre à jour une entreprise
export interface UpdateEntreprise {
  raisonSociale?: string;
  adresse?: string;
  telephone?: string;
  email?: string;
}
