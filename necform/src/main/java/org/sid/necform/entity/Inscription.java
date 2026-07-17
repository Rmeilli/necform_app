package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "inscriptions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutInscription statut;

    @Column(nullable = false)
    private LocalDate dateInscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_formation_id", nullable = false)
    private SessionFormation sessionFormation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Utilisateur apprenant;
}