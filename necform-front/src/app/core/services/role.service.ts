import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';
import { TypeUtilisateur } from '../../shared/models/enums';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private keycloak = inject(Keycloak);

  /**
   * Get the current user's role from Keycloak JWT
   */
  getCurrentRole(): TypeUtilisateur | null {
    if (!this.keycloak.authenticated) {
      return null;
    }

    const tokenParsed = this.keycloak.tokenParsed;
    if (!tokenParsed) {
      return null;
    }

    const realmAccess = tokenParsed['realm_access'] as { roles?: string[] };
    const roles = realmAccess?.roles || [];
    
    // Check for each role in priority order
    if (roles.includes('ADMIN')) {
      return TypeUtilisateur.ADMIN;
    }
    if (roles.includes('FORMATEUR')) {
      return TypeUtilisateur.FORMATEUR;
    }
    if (roles.includes('ENTREPRISE')) {
      return TypeUtilisateur.ENTREPRISE;
    }
    if (roles.includes('APPRENANT')) {
      return TypeUtilisateur.APPRENANT;
    }

    return null;
  }

  /**
   * Check if current user has a specific role
   */
  hasRole(role: TypeUtilisateur): boolean {
    return this.getCurrentRole() === role;
  }

  /**
   * Check if current user is ADMIN
   */
  isAdmin(): boolean {
    return this.hasRole(TypeUtilisateur.ADMIN);
  }

  /**
   * Check if current user is FORMATEUR
   */
  isFormateur(): boolean {
    return this.hasRole(TypeUtilisateur.FORMATEUR);
  }

  /**
   * Check if current user is ENTREPRISE
   */
  isEntreprise(): boolean {
    return this.hasRole(TypeUtilisateur.ENTREPRISE);
  }

  /**
   * Check if current user is APPRENANT
   */
  isApprenant(): boolean {
    return this.hasRole(TypeUtilisateur.APPRENANT);
  }

  /**
   * Get current user ID from Keycloak
   */
  getCurrentUserId(): string {
    return this.keycloak.subject || '';
  }

  /**
   * Get current user's email
   */
  getCurrentUserEmail(): string {
    const userProfile = this.keycloak.tokenParsed;
    return userProfile?.['email'] || '';
  }

  /**
   * Get current user's name
   */
  getCurrentUserName(): string {
    const userProfile = this.keycloak.tokenParsed;
    const firstName = userProfile?.['given_name'] || '';
    const lastName = userProfile?.['family_name'] || '';
    return `${firstName} ${lastName}`.trim();
  }
}
