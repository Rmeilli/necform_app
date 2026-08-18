import { Routes } from '@angular/router';
import { Dashboard } from './features/dashboard/dashboard';
import { Demandes } from './features/demandes/demandes';
import { Entreprises } from './features/entreprises/entreprises';
import { Formations } from './features/formations/formations';
import { Inscriptions } from './features/inscriptions/inscriptions';
import { Sessions } from './features/sessions/sessions';
import { Utilisateurs } from './features/utilisateurs/utilisateurs';
import { AccessDenied } from './features/access-denied/access-denied';
import { authGuard, adminGuard, trainerGuard, companyGuard, learnerGuard, roleGuard } from './core/guards/auth.guard';
import { TypeUtilisateur } from './shared/models/enums';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },

  // Dashboard - accessible to all authenticated users
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },

  // Admin-only routes
  { path: 'demandes', component: Demandes, canActivate: [adminGuard] },
  { path: 'entreprises', component: Entreprises, canActivate: [adminGuard] },
  { path: 'inscriptions', component: Inscriptions, canActivate: [adminGuard] },
  { path: 'utilisateurs', component: Utilisateurs, canActivate: [adminGuard] },

  // Admin or Formateur routes
  { path: 'sessions', component: Sessions, canActivate: [roleGuard([TypeUtilisateur.ADMIN, TypeUtilisateur.FORMATEUR])] },

  // Admin, Entreprise, or Apprenant routes
  { path: 'formations', component: Formations, canActivate: [authGuard] },

  // Access denied page
  { path: 'access-denied', component: AccessDenied },

  // Wildcard route
  { path: '**', redirectTo: '/dashboard' }
];
