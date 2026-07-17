import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EntrepriseService } from '../../core/services/entreprise.service';
import { Entreprise, CreateEntreprise, UpdateEntreprise } from '../../shared/models/entreprise.model';

@Component({
  selector: 'app-entreprises',
  imports: [CommonModule, FormsModule],
  templateUrl: './entreprises.html',
  styleUrl: './entreprises.css',
})
export class Entreprises implements OnInit {
  entreprises: Entreprise[] = [];
  isLoading = false;
  errorMessage = '';
  showForm = false;
  isEditing = false;
  selectedEntreprise: Entreprise | null = null;
  formData: CreateEntreprise = {
    raisonSociale: '',
    adresse: '',
    telephone: '',
    email: ''
  };

  constructor(
    private entrepriseService: EntrepriseService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadEntreprises();
  }

  loadEntreprises(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.entrepriseService.getAll().subscribe({
      next: (data) => {
        this.entreprises = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = 'Impossible de charger les entreprises.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  showCreateForm(): void {
    this.isEditing = false;
    this.selectedEntreprise = null;
    this.formData = { raisonSociale: '', adresse: '', telephone: '', email: '' };
    this.showForm = true;
  }

  showEditForm(entreprise: Entreprise): void {
    this.isEditing = true;
    this.selectedEntreprise = entreprise;
    this.formData = {
      raisonSociale: entreprise.raisonSociale,
      adresse: entreprise.adresse,
      telephone: entreprise.telephone,
      email: entreprise.email
    };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedEntreprise = null;
  }

  submitForm(): void {
    if (this.isEditing && this.selectedEntreprise) {
      const updateData: UpdateEntreprise = {
        raisonSociale: this.formData.raisonSociale,
        adresse: this.formData.adresse,
        telephone: this.formData.telephone,
        email: this.formData.email
      };
      this.entrepriseService.update(this.selectedEntreprise.id, updateData).subscribe({
        next: () => { this.loadEntreprises(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de modifier.'; }
      });
    } else {
      this.entrepriseService.create(this.formData).subscribe({
        next: () => { this.loadEntreprises(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de créer.'; }
      });
    }
  }

  deleteEntreprise(id: string): void {
    if (confirm('Supprimer cette entreprise ?')) {
      this.entrepriseService.delete(id).subscribe({
        next: () => this.loadEntreprises(),
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de supprimer.'; }
      });
    }
  }
}
