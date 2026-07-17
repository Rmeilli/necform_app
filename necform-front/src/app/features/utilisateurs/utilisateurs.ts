import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UtilisateurService } from '../../core/services/utilisateur.service';
import { Utilisateur, CreateUtilisateur, UpdateUtilisateur } from '../../shared/models/utilisateur.model';
import { TypeUtilisateur } from '../../shared/models/enums';

@Component({
  selector: 'app-utilisateurs',
  imports: [CommonModule, FormsModule],
  templateUrl: './utilisateurs.html',
  styleUrl: './utilisateurs.css',
})
export class Utilisateurs implements OnInit {
  TypeUtilisateur = TypeUtilisateur;
  utilisateurs: Utilisateur[] = [];
  isLoading = false;
  errorMessage = '';
  showForm = false;
  isEditing = false;
  selectedUtilisateur: Utilisateur | null = null;
  formData: CreateUtilisateur = {
    keycloakUserId: '',
    nom: '',
    prenom: '',
    email: '',
    type: TypeUtilisateur.APPRENANT
  };

  constructor(
    private utilisateurService: UtilisateurService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadUtilisateurs();
  }

  loadUtilisateurs(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.utilisateurService.getAll().subscribe({
      next: (data) => { this.utilisateurs = data; this.isLoading = false; this.cdr.detectChanges(); },
      error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de charger.'; this.isLoading = false; this.cdr.detectChanges(); }
    });
  }

  showCreateForm(): void {
    this.isEditing = false;
    this.selectedUtilisateur = null;
    this.formData = {
      keycloakUserId: '',
      nom: '',
      prenom: '',
      email: '',
      type: TypeUtilisateur.APPRENANT
    };
    this.showForm = true;
  }

  showEditForm(utilisateur: Utilisateur): void {
    this.isEditing = true;
    this.selectedUtilisateur = utilisateur;
    this.formData = {
      keycloakUserId: utilisateur.keycloakUserId,
      nom: utilisateur.nom,
      prenom: utilisateur.prenom,
      email: utilisateur.email,
      type: utilisateur.type
    };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedUtilisateur = null;
  }

  submitForm(): void {
    if (this.isEditing && this.selectedUtilisateur) {
      const updateData: UpdateUtilisateur = {
        nom: this.formData.nom,
        prenom: this.formData.prenom,
        email: this.formData.email,
        type: this.formData.type
      };
      this.utilisateurService.update(this.selectedUtilisateur.id, updateData).subscribe({
        next: () => { this.loadUtilisateurs(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de modifier.'; }
      });
    } else {
      this.utilisateurService.create(this.formData).subscribe({
        next: () => { this.loadUtilisateurs(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de créer.'; }
      });
    }
  }

  deleteUtilisateur(id: string): void {
    if (confirm('Supprimer cet utilisateur ?')) {
      this.utilisateurService.delete(id).subscribe({
        next: () => this.loadUtilisateurs(),
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de supprimer.'; }
      });
    }
  }

  getTypeLabel(type: TypeUtilisateur): string {
    switch (type) {
      case TypeUtilisateur.ADMIN: return 'Admin';
      case TypeUtilisateur.FORMATEUR: return 'Formateur';
      case TypeUtilisateur.APPRENANT: return 'Apprenant';
      case TypeUtilisateur.ENTREPRISE: return 'Entreprise';
      default: return type;
    }
  }
}
