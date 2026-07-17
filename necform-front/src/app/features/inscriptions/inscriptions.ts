import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InscriptionService } from '../../core/services/inscription.service';
import { Inscription, CreateInscription, UpdateInscription } from '../../shared/models/inscription.model';
import { StatutInscription } from '../../shared/models/enums';

@Component({
  selector: 'app-inscriptions',
  imports: [CommonModule, FormsModule],
  templateUrl: './inscriptions.html',
  styleUrl: './inscriptions.css',
})
export class Inscriptions implements OnInit {
  StatutInscription = StatutInscription;
  inscriptions: Inscription[] = [];
  isLoading = false;
  errorMessage = '';
  showForm = false;
  isEditing = false;
  selectedInscription: Inscription | null = null;
  formData: CreateInscription = {
    statut: StatutInscription.EN_ATTENTE,
    dateInscription: '',
    sessionFormationId: '',
    apprenantId: ''
  };

  constructor(
    private inscriptionService: InscriptionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadInscriptions();
  }

  loadInscriptions(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.inscriptionService.getAll().subscribe({
      next: (data) => { this.inscriptions = data; this.isLoading = false; this.cdr.detectChanges(); },
      error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de charger.'; this.isLoading = false; this.cdr.detectChanges(); }
    });
  }

  showCreateForm(): void {
    this.isEditing = false;
    this.selectedInscription = null;
    this.formData = {
      statut: StatutInscription.EN_ATTENTE,
      dateInscription: new Date().toISOString().split('T')[0],
      sessionFormationId: '',
      apprenantId: ''
    };
    this.showForm = true;
  }

  showEditForm(inscription: Inscription): void {
    this.isEditing = true;
    this.selectedInscription = inscription;
    this.formData = {
      statut: inscription.statut,
      dateInscription: inscription.dateInscription,
      sessionFormationId: inscription.sessionFormationId,
      apprenantId: inscription.apprenantId
    };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedInscription = null;
  }

  submitForm(): void {
    if (this.isEditing && this.selectedInscription) {
      const updateData: UpdateInscription = { statut: this.formData.statut };
      this.inscriptionService.update(this.selectedInscription.id, updateData).subscribe({
        next: () => { this.loadInscriptions(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de modifier.'; }
      });
    } else {
      this.inscriptionService.create(this.formData).subscribe({
        next: () => { this.loadInscriptions(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de créer.'; }
      });
    }
  }

  deleteInscription(id: string): void {
    if (confirm('Supprimer cette inscription ?')) {
      this.inscriptionService.delete(id).subscribe({
        next: () => this.loadInscriptions(),
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de supprimer.'; }
      });
    }
  }

  getStatutClass(statut: StatutInscription): string {
    switch (statut) {
      case StatutInscription.EN_ATTENTE: return 'statut-en-attente';
      case StatutInscription.VALIDEE: return 'statut-validee';
      case StatutInscription.ANNULEE: return 'statut-annulee';
      default: return '';
    }
  }

  getStatutLabel(statut: StatutInscription): string {
    switch (statut) {
      case StatutInscription.EN_ATTENTE: return 'En attente';
      case StatutInscription.VALIDEE: return 'Validée';
      case StatutInscription.ANNULEE: return 'Annulée';
      default: return statut;
    }
  }
}
