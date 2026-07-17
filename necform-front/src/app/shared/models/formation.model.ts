// Interface correspondant à FormationResponse du backend
export interface Formation {
  id: string;
  titre: string;
  categorie: string;
  description: string;
  dureeHeures: number;
  prix: number; // BigDecimal converti en number
}

// Interface pour créer une formation
export interface CreateFormation {
  titre: string;
  categorie: string;
  description: string;
  dureeHeures: number;
  prix: number;
}
