import { Routes } from '@angular/router'; // Importe le type Routes pour définir les routes de l'application
import { Dashboard } from './features/dashboard/dashboard'; // Importe le composant Dashboard
import { Demandes } from './features/demandes/demandes'; // Importe le composant Demandes
import { Entreprises } from './features/entreprises/entreprises'; // Importe le composant Entreprises
import { Formations } from './features/formations/formations'; // Importe le composant Formations
import { Inscriptions } from './features/inscriptions/inscriptions'; // Importe le composant Inscriptions
import { Sessions } from './features/sessions/sessions'; // Importe le composant Sessions
import { Utilisateurs } from './features/utilisateurs/utilisateurs'; // Importe le composant Utilisateurs
import { authGuard } from './core/guards/auth.guard'; // Importe le guard d'authentification
import { roleGuard } from './core/guards/auth.guard'; // Importe le guard de vérification de rôle

/**
 * Configuration des routes de l'application
 * Chaque route associe un chemin URL à un composant
 * Les guards sont utilisés pour protéger les routes et vérifier les permissions
 */
export const routes: Routes = [
  // Route racine : redirige vers le tableau de bord par défaut
  // pathMatch: 'full' signifie que cette route ne correspond que si le chemin est exactement vide
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },

  // Route du tableau de bord
  // canActivate: [authGuard] protège cette route avec le guard d'authentification
  // L'utilisateur doit être connecté pour accéder à cette page
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },

  // Route des demandes
  // Protégée par authGuard : nécessite une connexion
  { path: 'demandes', component: Demandes, canActivate: [authGuard] },

  // Route des entreprises
  // Protégée par authGuard : nécessite une connexion
  { path: 'entreprises', component: Entreprises, canActivate: [authGuard] },

  // Route des formations
  // Protégée par authGuard : nécessite une connexion
  { path: 'formations', component: Formations, canActivate: [authGuard] },

  // Route des inscriptions
  // Protégée par authGuard : nécessite une connexion
  { path: 'inscriptions', component: Inscriptions, canActivate: [authGuard] },

  // Route des sessions
  // Protégée par authGuard : nécessite une connexion
  { path: 'sessions', component: Sessions, canActivate: [authGuard] },

  // Route des utilisateurs
  // Protégée par roleGuard(['ADMIN']) : nécessite une connexion ET le rôle ADMIN
  // Seuls les utilisateurs avec le rôle ADMIN peuvent accéder à cette page
  { path: 'utilisateurs', component: Utilisateurs, canActivate: [roleGuard(['ADMIN'])] },

  // Route par défaut (wildcard)
  // Correspond à toutes les URLs qui ne correspondent pas aux routes précédentes
  // Redirige vers le tableau de bord pour éviter les erreurs 404
  { path: '**', redirectTo: '/dashboard' }
];
