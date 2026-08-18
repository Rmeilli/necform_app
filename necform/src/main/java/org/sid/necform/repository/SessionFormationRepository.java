package org.sid.necform.repository;

import org.sid.necform.entity.SessionFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionFormationRepository extends JpaRepository<SessionFormation, UUID> {
    List<SessionFormation> findByFormateurId(UUID formateurId);
}