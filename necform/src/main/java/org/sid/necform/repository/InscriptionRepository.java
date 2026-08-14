package org.sid.necform.repository;

import org.sid.necform.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InscriptionRepository extends JpaRepository<Inscription, UUID> {
    List<Inscription> findByApprenantId(UUID apprenantId);
}