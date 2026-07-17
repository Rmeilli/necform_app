package org.sid.necform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="formations")

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String titre;

    private String categorie;

    @Column(length=3000)
    private String description;

    private Integer dureeHeures;

    private BigDecimal prix;

}
