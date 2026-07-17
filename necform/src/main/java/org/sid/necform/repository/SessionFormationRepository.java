package org.sid.necform.repository;

import org.sid.necform.entity.SessionFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionFormationRepository extends JpaRepository<SessionFormation, UUID> {
}