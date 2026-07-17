package org.sid.necform.config;

// Importation des classes Spring Security nécessaires pour la configuration
import org.springframework.context.annotation.Bean; // Pour créer des beans gérés par Spring
import org.springframework.context.annotation.Configuration; // Pour marquer cette classe comme configuration
import org.springframework.core.convert.converter.Converter; // Pour convertir les JWT en tokens d'authentification
import org.springframework.security.authentication.AbstractAuthenticationToken; // Classe de base pour les tokens
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Pour activer la sécurité au niveau des méthodes
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Pour configurer la sécurité HTTP
import org.springframework.security.config.http.SessionCreationPolicy; // Pour définir la politique de session
import org.springframework.security.oauth2.jwt.Jwt; // Classe représentant un token JWT
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter; // Convertisseur JWT vers Authentication
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter; // Convertisseur pour les autorités (rôles)
import org.springframework.security.web.SecurityFilterChain; // Chaîne de filtres de sécurité
import org.springframework.web.cors.CorsConfiguration; // Configuration CORS pour autoriser les requêtes cross-origin
import org.springframework.web.cors.CorsConfigurationSource; // Source de configuration CORS
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // Implémentation basée sur les URLs

// Importations Java standard
import java.util.Collection; // Interface pour les collections
import java.util.Map; // Interface pour les maps (dictionnaires)
import java.util.Arrays; // Utilitaires pour les tableaux
import java.util.stream.Collectors; // Utilitaires pour les streams
import java.util.stream.Stream; // Interface pour les streams

// @Configuration : Indique à Spring que cette classe contient des beans de configuration
// @EnableMethodSecurity : Active la sécurité au niveau des méthodes (annotations @PreAuthorize, @Secured, etc.)
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configure la chaîne de filtres de sécurité HTTP
     * C'est le point d'entrée principal pour configurer la sécurité de l'application
     *
     * @param http L'objet HttpSecurity à configurer
     * @return La chaîne de filtres de sécurité configurée
     * @throws Exception En cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive la protection CSRF (Cross-Site Request Forgery)
                // Pour une API REST avec JWT, on n'a pas besoin de CSRF car les tokens sont envoyés dans chaque requête
                .csrf(csrf -> csrf.disable())

                // Active la configuration CORS (Cross-Origin Resource Sharing)
                // Permet au frontend (localhost:4200) de communiquer avec le backend (localhost:8080)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Configure la gestion des sessions
                // STATELESS : Pas de session HTTP côté serveur, l'état est géré par le token JWT
                // C'est essentiel pour une API RESTful
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configure les autorisations des requêtes HTTP
                .authorizeHttpRequests(auth ->

                        auth

                                // Ces endpoints sont accessibles sans authentification
                                // Swagger UI pour la documentation de l'API
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                )
                                .permitAll() // Autorise l'accès sans authentification

                                // Ces endpoints API nécessitent une authentification
                                // Tous les endpoints de l'API sont protégés
                                .requestMatchers(
                                        "/api/**"
                                )
                                .authenticated() // Nécessite une authentification valide

                                // Toute autre requête doit être authentifiée
                                .anyRequest()
                                .authenticated()
                )

                // Configure le serveur de ressources OAuth2 pour valider les tokens JWT
                // C'est ici que Spring Security va vérifier les tokens Keycloak
                .oauth2ResourceServer(oauth2 -> oauth2
                        // Configure le convertisseur de JWT vers Authentication
                        // Ce convertisseur extrait les rôles du token Keycloak
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        // Construit et retourne la chaîne de filtres de sécurité
        return http.build();
    }

    /**
     * Configure la source de configuration CORS (Cross-Origin Resource Sharing)
     * CORS permet au frontend Angular (localhost:4200) de faire des requêtes au backend (localhost:8080)
     * Sans cela, le navigateur bloquerait les requêtes pour des raisons de sécurité
     *
     * @return La source de configuration CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Crée un nouvel objet de configuration CORS
        CorsConfiguration configuration = new CorsConfiguration();

        // Définit les origines autorisées (les URLs du frontend)
        // Seules ces origines pourront faire des requêtes au backend
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));

        // Définit les méthodes HTTP autorisées
        // GET : pour récupérer des données
        // POST : pour créer des données
        // PUT : pour modifier des données
        // DELETE : pour supprimer des données
        // OPTIONS : pour les requêtes préflight (vérification CORS)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Définit les en-têtes HTTP autorisés
        // "*" signifie tous les en-têtes sont autorisés (Authorization, Content-Type, etc.)
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Autorise l'envoi de cookies et d'informations d'authentification
        // Nécessaire pour que le token JWT puisse être envoyé correctement
        configuration.setAllowCredentials(true);

        // Crée une source de configuration CORS basée sur les URLs
        // Cela permet d'appliquer la configuration à tous les endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Enregistre la configuration CORS pour tous les chemins ("/**")
        // Cela signifie que tous les endpoints de l'API seront accessibles avec CORS
        source.registerCorsConfiguration("/**", configuration);

        // Retourne la source de configuration CORS
        return source;
    }

    /**
     * Configure le convertisseur de JWT vers Authentication
     * Ce convertisseur extrait les rôles et les autorités du token Keycloak
     * et les transforme en objets Spring Security compréhensibles
     *
     * Keycloak envoie un JWT avec les informations de l'utilisateur et ses rôles
     * Ce convertisseur transforme ces informations en objets Spring Security
     *
     * @return Le convertisseur JWT configuré
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Crée un convertisseur pour les scopes (portées) du JWT
        // Les scopes sont des permissions basiques définies dans Keycloak
        JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

        // Définit le préfixe pour les autorités
        // "ROLE_" est ajouté devant chaque rôle pour respecter les conventions Spring Security
        // Par exemple : "ADMIN" devient "ROLE_ADMIN"
        scopesConverter.setAuthorityPrefix("ROLE_");

        // Définit le nom du claim dans le JWT qui contient les scopes
        // "scope" est le nom standard pour les scopes dans un JWT OAuth2
        scopesConverter.setAuthoritiesClaimName("scope");

        // Crée un convertisseur personnalisé pour les rôles du realm Keycloak
        // Les rôles du realm sont définis au niveau du realm Keycloak (pas au niveau du client)
        // Ce convertisseur extrait ces rôles du claim "realm_access" du JWT
        Converter<Jwt, Collection<org.springframework.security.core.GrantedAuthority>> keycloakRealmRolesConverter =
                jwt -> {
                    // Extrait le claim "realm_access" du JWT
                    // Ce claim contient les rôles de l'utilisateur dans le realm Keycloak
                    Map<String, Object> realmAccess = jwt.getClaim("realm_access");

                    // Vérifie si le claim existe et contient des rôles
                    // Si non, retourne une liste vide (pas de rôles)
                    if (realmAccess == null || realmAccess.get("roles") == null) {
                        return java.util.List.of();
                    }

                    // Extrait la liste des rôles du claim realm_access
                    // Les rôles sont stockés sous forme de collection de chaînes
                    Collection<String> roles = (Collection<String>) realmAccess.get("roles");

                    // Transforme chaque rôle en GrantedAuthority Spring Security
                    // Chaque rôle est préfixé par "ROLE_" pour respecter les conventions Spring
                    // Par exemple : "ADMIN" devient "ROLE_ADMIN"
                    return roles.stream()
                            .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList());
                };

        // Crée le convertisseur principal JWT vers Authentication
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        // Configure le convertisseur pour combiner les scopes et les rôles du realm
        // Cela permet d'avoir à la fois les scopes (basiques) et les rôles (avancés)
        // Stream.concat fusionne les deux flux d'autorités
        // Collectors.toSet() évite les doublons
        converter.setJwtGrantedAuthoritiesConverter(jwt -> Stream.concat(
                scopesConverter.convert(jwt).stream(),      // Ajoute les scopes
                keycloakRealmRolesConverter.convert(jwt).stream() // Ajoute les rôles du realm
        ).collect(Collectors.toSet()));

        // Retourne le convertisseur configuré
        return converter;
    }
}

