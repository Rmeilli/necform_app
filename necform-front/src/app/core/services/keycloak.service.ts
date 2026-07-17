import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';
import { KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakAuthService {
  private userProfile: KeycloakProfile | null = null;
  private readonly keycloak = inject(Keycloak);

  async loadProfile(): Promise<void> {
    if (this.keycloak.authenticated) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  async login(): Promise<void> {
    await this.keycloak.login();
  }

  async logout(): Promise<void> {
    await this.keycloak.logout();
    this.userProfile = null;
  }

  isLoggedIn(): boolean {
    return this.keycloak.authenticated ?? false;
  }

  getUserProfile(): KeycloakProfile | null {
    return this.userProfile;
  }

  async getToken(): Promise<string | null> {
    return this.keycloak.token ?? null;
  }

  hasRole(roleName: string): boolean {
    return (
      this.keycloak.hasRealmRole(roleName) ||
      this.keycloak.hasResourceRole(roleName)
    );
  }

  getUserRoles(): string[] {
    const realmRoles = this.keycloak.realmAccess?.roles ?? [];
    const resourceRoles = Object.values(this.keycloak.resourceAccess ?? {}).flatMap(
      (access) => access.roles ?? []
    );
    return [...new Set([...realmRoles, ...resourceRoles])];
  }

  async updateToken(): Promise<boolean> {
    return this.keycloak.updateToken(5);
  }
}
