import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DemandeFormationService } from '../../core/services/demande-formation.service';
import { 
  DemandeFormation, 
  CreateDemandeFormation, 
  UpdateDemandeFormation 
} from '../../shared/models/demande-formation.model';
import { StatutDemande } from '../../shared/models/enums';

// Composant pour gérer les demandes de formation avec CRUD complet
@Component({
  selector: 'app-demandes',
  imports: [CommonModule, FormsModule],
  templateUrl: './demandes.html',
  styleUrl: './demandes.css',
})
export class Demandes implements OnInit {
  // Rendre l'enum accessible dans le template
  StatutDemande = StatutDemande;
  // Liste des demandes de formation
  demandes: DemandeFormation[] = [];
  
  // Variables pour l'état de chargement et les erreurs
  isLoading = false;
  errorMessage = '';
  
  // Variables pour le formulaire de création/modification
  showForm = false;
  isEditing = false;
  selectedDemande: DemandeFormation | null = null;
  
  // Formulaire
  formData: CreateDemandeFormation = {
    dateDemande: '',
    nombreParticipants: 0,
    besoin: '',
    statut: StatutDemande.EN_ATTENTE,
    entrepriseId: '',
    formationId: ''
  };

  constructor(
    private demandeService: DemandeFormationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadDemandes();
  }

  // Charger toutes les demandes
  loadDemandes(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.cdr.detectChanges();
    
    this.demandeService.getAll().subscribe({
      next: (data) => {
        this.demandes = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Erreur lors du chargement des demandes:', error);
        this.errorMessage = 'Impossible de charger les demandes de formation.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  // Afficher le formulaire de création
  showCreateForm(): void {
    this.isEditing = false;
    this.selectedDemande = null;
    this.formData = {
      dateDemande: new Date().toISOString().split('T')[0],
      nombreParticipants: 0,
      besoin: '',
      statut: StatutDemande.EN_ATTENTE,
      entrepriseId: '',
      formationId: ''
    };
    this.showForm = true;
  }

  // Afficher le formulaire de modification
  showEditForm(demande: DemandeFormation): void {
    this.isEditing = true;
    this.selectedDemande = demande;
    this.formData = {
      dateDemande: demande.dateDemande,
      nombreParticipants: demande.nombreParticipants,
      besoin: demande.besoin,
      statut: demande.statut,
      entrepriseId: demande.entrepriseId,
      formationId: demande.formationId
    };
    this.showForm = true;
  }

  // Fermer le formulaire
  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedDemande = null;
  }

  // Soumettre le formulaire (création ou modification)
  submitForm(): void {
    if (this.isEditing && this.selectedDemande) {
      // Modification
      const updateData: UpdateDemandeFormation = {
        nombreParticipants: this.formData.nombreParticipants,
        besoin: this.formData.besoin,
        statut: this.formData.statut
      };
      
      this.demandeService.update(this.selectedDemande.id, updateData).subscribe({
        next: () => {
          this.loadDemandes();
          this.closeForm();
        },
        error: (error) => {
          console.error('Erreur lors de la modification:', error);
          this.errorMessage = 'Impossible de modifier la demande.';
        }
      });
    } else {
      // Création
      this.demandeService.create(this.formData).subscribe({
        next: () => {
          this.loadDemandes();
          this.closeForm();
        },
        error: (error) => {
          console.error('Erreur lors de la création:', error);
          this.errorMessage = 'Impossible de créer la demande.';
        }
      });
    }
  }

  // Supprimer une demande
  deleteDemande(id: string): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette demande ?')) {
      this.demandeService.delete(id).subscribe({
        next: () => {
          this.loadDemandes();
        },
        error: (error) => {
          console.error('Erreur lors de la suppression:', error);
          this.errorMessage = 'Impossible de supprimer la demande.';
        }
      });
    }
  }

  // Obtenir la classe CSS pour le badge de statut
  getStatutClass(statut: StatutDemande): string {
    switch (statut) {
      case StatutDemande.EN_ATTENTE:
        return 'statut-en-attente';
      case StatutDemande.VALIDEE:
        return 'statut-validee';
      case StatutDemande.REFUSEE:
        return 'statut-refusee';
      default:
        return '';
    }
  }

  // Obtenir le libellé du statut
  getStatutLabel(statut: StatutDemande): string {
    switch (statut) {
      case StatutDemande.EN_ATTENTE:
        return 'En attente';
      case StatutDemande.VALIDEE:
        return 'Validée';
      case StatutDemande.REFUSEE:
        return 'Refusée';
      default:
        return statut;
    }
  }
}
