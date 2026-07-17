package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "sessions_formation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    private String lieu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatut statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formateur_id")
    private Utilisateur formateur;
}