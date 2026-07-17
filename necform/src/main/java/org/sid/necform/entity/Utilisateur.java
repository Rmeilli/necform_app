package org.sid.necform.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String keycloakUserId;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeUtilisateur type;
}
