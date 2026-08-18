// Interface correspondant à DashboardResponse du backend
// Cette interface définit la structure des statistiques du tableau de bord
export interface DashboardStats {
  formations: number;
  sessions: number;
  utilisateurs: number;
  apprenants: number;
  formateurs: number;
  inscriptions: number;
  entreprises: number;
  demandes: number;
}
