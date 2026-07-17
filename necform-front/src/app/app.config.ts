import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core'; // Importe les fonctions de configuration Angular de base
import { provideRouter } from '@angular/router'; // Importe le fournisseur de routeur Angular pour la navigation
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import {
  provideKeycloak,
  includeBearerTokenInterceptor,
  INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
  type IncludeBearerTokenCondition
} from 'keycloak-angular';

import { routes } from './app.routes'; // Importe les routes de l'application définies dans app.routes.ts
import { provideClientHydration } from '@angular/platform-browser'; // Importe l'hydratation côté client pour le SSR

/**
 * Configuration principale de l'application Angular
 * Ce fichier définit tous les providers (services) disponibles dans l'application
 * C'est le point d'entrée pour configurer les fonctionnalités globales comme le routeur, HTTP, et l'authentification
 */
export const appConfig: ApplicationConfig = {
  providers: [
    // Fournit les écouteurs d'erreurs globales du navigateur
    // Cela permet de capturer et gérer les erreurs JavaScript au niveau global
    provideBrowserGlobalErrorListeners(),

    // Fournit le routeur Angular avec les routes définies
    // Le routeur gère la navigation entre les différentes pages de l'application
    provideRouter(routes),

    // Fournit l'hydratation côté client
    // L'hydratation permet de transférer l'état du serveur vers le client pour le SSR (Server-Side Rendering)
    provideClientHydration(),

    // Configure et fournit Keycloak pour l'authentification
    // Keycloak est un serveur d'identité open source qui gère l'authentification et l'autorisation
    provideKeycloak({
      // Configuration de l'adaptateur Keycloak
      config: {
        // URL du serveur Keycloak
        // C'est l'adresse où Keycloak est hébergé (localhost:8081 est le port par défaut)
        url: 'http://localhost:8081',

        // Nom du realm Keycloak
        // Un realm est un espace isolé dans Keycloak qui contient ses propres utilisateurs et rôles
        // 'necform' est le realm créé pour cette application
        realm: 'necform',

        // Identifiant du client Keycloak
        // Un client représente une application qui utilise Keycloak pour l'authentification
        // 'necform-client' est le client Angular existant dans Keycloak
        clientId: 'necform-client'
      },

      // Options d'initialisation de Keycloak
      initOptions: {
        // Comportement au chargement de la page
        // 'login-required' : Force l'utilisateur à se connecter s'il ne l'est pas déjà
        // Cela redirige automatiquement vers la page de connexion Keycloak si nécessaire
        onLoad: 'login-required',

        // Flux d'authentification OAuth2
        // 'standard' : Utilise le flux standard avec code d'autorisation (plus sécurisé)
        // C'est le flux recommandé pour les applications web modernes
        flow: 'standard',

        // Vérification du login via iframe
        // false : Désactive la vérification via iframe pour éviter les problèmes de cross-origin
        // true : Utiliserait une iframe invisible pour vérifier si l'utilisateur est connecté
        checkLoginIframe: false
      }
    }),

    {
      provide: INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
      useValue: [
        {
          urlPattern: /^http:\/\/localhost:8080\//
        } satisfies IncludeBearerTokenCondition
      ]
    },

    provideHttpClient(withInterceptors([includeBearerTokenInterceptor]))
  ]
};
