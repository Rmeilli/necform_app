package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="documents")

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TypeDocument type;

    @Column(nullable=false)
    private String nomFichier;

    private String cheminFichier;

    private LocalDate dateGeneration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="session_id")
    private SessionFormation sessionFormation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

}