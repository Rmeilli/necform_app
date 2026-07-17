package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "entreprises")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String raisonSociale;

    private String adresse;

    private String telephone;

    private String email;
}