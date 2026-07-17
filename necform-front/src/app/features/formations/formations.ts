import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FormationService } from '../../core/services/formation.service';
import { Formation, CreateFormation } from '../../shared/models/formation.model';

@Component({
  selector: 'app-formations',
  imports: [CommonModule, FormsModule],
  templateUrl: './formations.html',
  styleUrl: './formations.css',
})
export class Formations implements OnInit {
  formations: Formation[] = [];
  isLoading = false;
  errorMessage = '';
  showForm = false;
  isEditing = false;
  selectedFormation: Formation | null = null;
  formData: CreateFormation = {
    titre: '',
    categorie: '',
    description: '',
    dureeHeures: 0,
    prix: 0
  };

  constructor(
    private formationService: FormationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadFormations();
  }

  loadFormations(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.formationService.getAll().subscribe({
      next: (data) => { this.formations = data; this.isLoading = false; this.cdr.detectChanges(); },
      error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de charger.'; this.isLoading = false; this.cdr.detectChanges(); }
    });
  }

  showCreateForm(): void {
    this.isEditing = false;
    this.selectedFormation = null;
    this.formData = { titre: '', categorie: '', description: '', dureeHeures: 0, prix: 0 };
    this.showForm = true;
  }

  showEditForm(formation: Formation): void {
    this.isEditing = true;
    this.selectedFormation = formation;
    this.formData = { ...formation };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedFormation = null;
  }

  submitForm(): void {
    if (this.isEditing && this.selectedFormation) {
      this.formationService.update(this.selectedFormation.id, this.formData).subscribe({
        next: () => { this.loadFormations(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de modifier.'; }
      });
    } else {
      this.formationService.create(this.formData).subscribe({
        next: () => { this.loadFormations(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de créer.'; }
      });
    }
  }

  deleteFormation(id: string): void {
    if (confirm('Supprimer cette formation ?')) {
      this.formationService.delete(id).subscribe({
        next: () => this.loadFormations(),
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de supprimer.'; }
      });
    }
  }
}
