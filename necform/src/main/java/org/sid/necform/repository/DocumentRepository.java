package org.sid.necform.repository;

import org.sid.necform.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository
        extends JpaRepository<Document, UUID> {
}