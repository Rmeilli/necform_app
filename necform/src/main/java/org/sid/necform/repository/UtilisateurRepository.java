package org.sid.necform.repository;

import org.sid.necform.entity.TypeUtilisateur;
import org.sid.necform.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByKeycloakUserId(String keycloakUserId);
    boolean existsByEmail(String email);
    boolean existsByKeycloakUserId(String keycloakUserId);
    long countByType(TypeUtilisateur type);
}
