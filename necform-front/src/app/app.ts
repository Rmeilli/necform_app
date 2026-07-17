import { Component, signal, inject, OnInit, computed } from '@angular/core'; // Importe inject, OnInit et computed
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router'; // Importe les directives de routage
import Keycloak from 'keycloak-js'; // Importe l'instance Keycloak pour l'authentification
import { KeycloakProfile } from 'keycloak-js'; // Importe l'interface KeycloakProfile pour le profil utilisateur

@Component({
  selector: 'app-root', // Sélecteur CSS du composant racine
  imports: [RouterOutlet, RouterLink, RouterLinkActive], // Importe les directives nécessaires pour le template
  templateUrl: './app.html', // Chemin vers le fichier HTML du template
  styleUrl: './app.css' // Chemin vers le fichier CSS du style
})
export class App implements OnInit { // Implémente OnInit pour le cycle de vie
  // Signal pour stocker le titre de l'application
  // Les signals sont la nouvelle façon de gérer l'état réactif dans Angular
  protected readonly title = signal('necform-front');

  // Signal pour stocker le profil de l'utilisateur connecté
  // null signifie qu'aucun utilisateur n'est connecté
  protected readonly userProfile = signal<KeycloakProfile | null>(null);

  // Signal pour stocker si l'utilisateur est connecté
  // public pour être accessible dans le template HTML
  public readonly isLoggedIn = signal(false);

  // Propriété computed pour l'état de connexion (fonctionne mieux dans les conditions)
  public readonly isLoggedInComputed = computed(() => this.isLoggedIn());

  private readonly keycloak = inject(Keycloak);

  /**
   * Méthode appelée automatiquement par Angular après la création du composant
   * C'est le bon endroit pour initialiser les données et vérifier l'authentification
   */
  async ngOnInit(): Promise<void> {
    await this.loadUserProfile();

    // Écoute les changements d'état de connexion Keycloak
    // Met à jour l'état de connexion quand l'utilisateur se connecte ou se déconnecte
    this.keycloak.onAuthSuccess = async () => {
      this.isLoggedIn.set(true);
      await this.loadUserProfile();
    };

    this.keycloak.onAuthLogout = () => {
      this.isLoggedIn.set(false);
      this.userProfile.set(null);
    };
  }

  /**
   * Charge le profil utilisateur si connecté
   * Cette méthode est appelée quand le composant est initialisé
   */
  async loadUserProfile(): Promise<void> {
    try {
      // Log de débogage pour vérifier l'état d'authentification
      console.log('État d\'authentification Keycloak:', this.keycloak.authenticated);

      // Vérifie si l'utilisateur est déjà connecté via Keycloak
      if (this.keycloak.authenticated) {
        console.log('Utilisateur authentifié, chargement du profil...');

        // Met à jour le signal isLoggedIn pour indiquer que l'utilisateur est connecté
        this.isLoggedIn.set(true);
        console.log('Signal isLoggedIn mis à jour:', this.isLoggedIn());

        // Charge le profil utilisateur depuis Keycloak
        // Le profil contient : nom, email, prénom, etc.
        const profile = await this.keycloak.loadUserProfile();

        // Met à jour le signal userProfile avec les données de l'utilisateur
        this.userProfile.set(profile);
        console.log('Profil utilisateur chargé:', profile);
      } else {
        console.log('Utilisateur non authentifié');
      }
    } catch (error) {
      // En cas d'erreur lors du chargement du profil, log l'erreur dans la console
      console.error('Erreur lors du chargement du profil utilisateur:', error);
    }
  }

  /**
   * Déconnecte l'utilisateur de l'application
   * Cette méthode est appelée quand l'utilisateur clique sur le bouton de déconnexion
   */
  async logout(): Promise<void> {
    try {
      // Appelle la méthode logout() du service Keycloak
      // Cela redirige l'utilisateur vers la page de déconnexion Keycloak
      await this.keycloak.logout();

      // Réinitialise les signaux après la déconnexion
      this.isLoggedIn.set(false); // Indique que l'utilisateur n'est plus connecté
      this.userProfile.set(null); // Efface les informations du profil utilisateur
    } catch (error) {
      // En cas d'erreur lors de la déconnexion, log l'erreur dans la console
      console.error('Erreur lors de la déconnexion:', error);
    }
  }

  /**
   * Obtient le nom complet de l'utilisateur connecté
   * Combine le prénom et le nom pour afficher un nom complet
   *
   * @returns Le nom complet de l'utilisateur ou "Utilisateur" si non connecté
   */
  getUserName(): string {
    // Récupère le profil utilisateur depuis le signal
    const profile = this.userProfile();

    // Si le profil existe, retourne le nom complet (prénom + nom)
    if (profile) {
      // Utilise ?? pour fournir une valeur par défaut si firstName ou lastName sont null
      return `${profile.firstName ?? ''} ${profile.lastName ?? ''}`.trim();
    }

    // Si aucun profil n'est disponible, retourne "Utilisateur"
    return 'Utilisateur';
  }

  /**
   * Obtient l'email de l'utilisateur connecté
   *
   * @returns L'email de l'utilisateur ou une chaîne vide si non connecté
   */
  getUserEmail(): string {
    // Récupère le profil utilisateur depuis le signal
    const profile = this.userProfile();

    // Si le profil existe, retourne l'email
    if (profile && profile.email) {
      return profile.email;
    }

    // Si aucun profil n'est disponible, retourne une chaîne vide
    return '';
  }
}
