// Interface correspondant à DocumentResponse du backend
export interface Document {
  id: string;
  nom: string;
  type: string;
  contenu: string;
  dateUpload: string;
}

// Interface pour créer un document
export interface CreateDocument {
  nom: string;
  type: string;
  contenu: string;
}
