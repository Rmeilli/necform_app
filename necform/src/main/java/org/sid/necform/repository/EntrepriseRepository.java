package org.sid.necform.repository;

import org.sid.necform.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntrepriseRepository extends JpaRepository<Entreprise, UUID> {
}