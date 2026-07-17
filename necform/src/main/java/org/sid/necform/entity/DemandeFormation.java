package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "demandes_formation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandeFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dateDemande;

    @Column(nullable = false)
    private Integer nombreParticipants;

    @Column(length = 3000)
    private String besoin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDemande statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id", nullable = false)
    private Entreprise entreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;
}