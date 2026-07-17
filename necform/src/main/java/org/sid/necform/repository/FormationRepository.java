package org.sid.necform.repository;



import org.sid.necform.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormationRepository extends JpaRepository<Formation, UUID>{}
