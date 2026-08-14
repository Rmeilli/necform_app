import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import Keycloak from 'keycloak-js';
import { TypeUtilisateur } from '../../shared/models/enums';

export const authGuard: CanActivateFn = async (route, state) => {
  const keycloak = inject(Keycloak);
  const router = inject(Router);

  if (!keycloak.authenticated) {
    await keycloak.login({
      redirectUri: window.location.origin + state.url
    });
    return false;
  }

  return true;
};

export const roleGuard = (allowedRoles: TypeUtilisateur[]): CanActivateFn => {
  return async (route, state) => {
    const keycloak = inject(Keycloak);
    const router = inject(Router);

    console.log('[roleGuard] Checking access for route:', state.url);
    console.log('[roleGuard] Required roles:', allowedRoles);
    console.log('[roleGuard] Authenticated:', keycloak.authenticated);

    if (!keycloak.authenticated) {
      console.log('[roleGuard] Not authenticated, redirecting to login');
      await keycloak.login({
        redirectUri: window.location.origin + state.url
      });
      return false;
    }

    const tokenParsed = keycloak.tokenParsed;
    console.log('[roleGuard] Token parsed:', tokenParsed);

    if (!tokenParsed) {
      console.log('[roleGuard] No token parsed, redirecting to access denied');
      router.navigate(['/access-denied']);
      return false;
    }

    const realmAccess = tokenParsed['realm_access'] as { roles?: string[] };
    const userRoles = realmAccess?.roles || [];
    console.log('[roleGuard] User roles from token:', userRoles);

    const hasRequiredRole = allowedRoles.some((role) => {
      const roleString = role.toString();
      const hasRole = userRoles.includes(roleString);
      console.log(`[roleGuard] Checking role ${roleString}: ${hasRole}`);
      return hasRole;
    });

    console.log('[roleGuard] Has required role:', hasRequiredRole);

    if (hasRequiredRole) {
      console.log('[roleGuard] Access granted');
      return true;
    }

    console.log('[roleGuard] Access denied, redirecting to access denied');
    router.navigate(['/access-denied']);
    return false;
  };
};

// Role-specific guards
export const adminGuard: CanActivateFn = roleGuard([TypeUtilisateur.ADMIN]);

export const trainerGuard: CanActivateFn = roleGuard([TypeUtilisateur.FORMATEUR]);

export const companyGuard: CanActivateFn = roleGuard([TypeUtilisateur.ENTREPRISE]);

export const learnerGuard: CanActivateFn = roleGuard([TypeUtilisateur.APPRENANT]);
