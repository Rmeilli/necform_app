package org.sid.necform.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sid.necform.entity.TypeUtilisateur;
import org.sid.necform.entity.Utilisateur;
import org.sid.necform.repository.UtilisateurRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Filtre de synchronisation des utilisateurs Keycloak vers PostgreSQL.
 * A chaque requete authentifiee, les informations du token JWT sont extraites
 * et l'utilisateur est cree ou mis a jour dans la base de donnees locale.
 */
public class KeycloakUserSyncFilter extends OncePerRequestFilter {

    private final UtilisateurRepository utilisateurRepository;

    public KeycloakUserSyncFilter(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            syncUserFromJwt(jwt);
        }

        filterChain.doFilter(request, response);
    }

    private void syncUserFromJwt(Jwt jwt) {
        String keycloakUserId = jwt.getSubject(); // claim "sub"

        if (keycloakUserId == null || keycloakUserId.isBlank()) {
            return;
        }

        Optional<Utilisateur> existing = utilisateurRepository.findByKeycloakUserId(keycloakUserId);

        if (existing.isPresent()) {
            updateExistingUser(existing.get(), jwt);
            return;
        }

        createNewUser(jwt, keycloakUserId);
    }

    private void updateExistingUser(Utilisateur user, Jwt jwt) {
        user.setEmail(getClaimAsString(jwt, "email"));
        user.setPrenom(getClaimAsString(jwt, "given_name"));
        user.setNom(getClaimAsString(jwt, "family_name"));

        utilisateurRepository.save(user);
    }

    private void createNewUser(Jwt jwt, String keycloakUserId) {
        TypeUtilisateur type = extractTypeFromJwt(jwt);

        Utilisateur newUser = Utilisateur.builder()
                .keycloakUserId(keycloakUserId)
                .email(getClaimAsString(jwt, "email"))
                .prenom(getClaimAsString(jwt, "given_name"))
                .nom(getClaimAsString(jwt, "family_name"))
                .type(type)
                .build();

        utilisateurRepository.save(newUser);
    }

    private TypeUtilisateur extractTypeFromJwt(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess != null && realmAccess.get("roles") instanceof Collection<?> roles) {
            for (Object role : roles) {
                String roleStr = role.toString().toUpperCase();

                try {
                    return TypeUtilisateur.valueOf(roleStr);
                } catch (IllegalArgumentException ignored) {
                    // Role non reconnu, on continue
                }
            }
        }

        return TypeUtilisateur.APPRENANT;
    }

    private String getClaimAsString(Jwt jwt, String claimName) {
        Object claim = jwt.getClaim(claimName);
        return claim != null ? claim.toString() : "";
    }
}