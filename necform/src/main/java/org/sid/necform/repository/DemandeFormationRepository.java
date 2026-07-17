package org.sid.necform.repository;

import org.sid.necform.entity.DemandeFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemandeFormationRepository extends JpaRepository<DemandeFormation, UUID> {
}