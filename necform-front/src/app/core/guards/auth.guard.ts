import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { createAuthGuard } from 'keycloak-angular';

export const authGuard = createAuthGuard(async (route, state, authData) => {
  const { authenticated, keycloak } = authData;

  if (authenticated) {
    return true;
  }

  await keycloak.login({
    redirectUri: window.location.origin + state.url
  });

  return false;
});

export const roleGuard = (allowedRoles: string[]): CanActivateFn =>
  createAuthGuard(async (route, state, authData) => {
    const { authenticated, keycloak, grantedRoles } = authData;
    const router = inject(Router);

    if (!authenticated) {
      await keycloak.login({
        redirectUri: window.location.origin + state.url
      });
      return false;
    }

    const userRoles = [
      ...grantedRoles.realmRoles,
      ...Object.values(grantedRoles.resourceRoles).flat()
    ];
    const hasRequiredRole = allowedRoles.some((role) => userRoles.includes(role));

    if (hasRequiredRole) {
      return true;
    }

    router.navigate(['/']);
    return false;
  });
