import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SessionFormationService } from '../../core/services/session-formation.service';
import { SessionFormation, CreateSessionFormation } from '../../shared/models/session-formation.model';
import { SessionStatut } from '../../shared/models/enums';

@Component({
  selector: 'app-sessions',
  imports: [CommonModule, FormsModule],
  templateUrl: './sessions.html',
  styleUrl: './sessions.css',
})
export class Sessions implements OnInit {
  SessionStatut = SessionStatut;
  sessions: SessionFormation[] = [];
  isLoading = false;
  errorMessage = '';
  showForm = false;
  isEditing = false;
  selectedSession: SessionFormation | null = null;
  formData: CreateSessionFormation = {
    dateDebut: '',
    dateFin: '',
    lieu: '',
    statut: SessionStatut.PLANIFIEE,
    formationId: '',
    formateurId: ''
  };

  constructor(
    private sessionService: SessionFormationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadSessions();
  }

  loadSessions(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.sessionService.getAll().subscribe({
      next: (data) => { this.sessions = data; this.isLoading = false; this.cdr.detectChanges(); },
      error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de charger.'; this.isLoading = false; this.cdr.detectChanges(); }
    });
  }

  showCreateForm(): void {
    this.isEditing = false;
    this.selectedSession = null;
    this.formData = {
      dateDebut: '',
      dateFin: '',
      lieu: '',
      statut: SessionStatut.PLANIFIEE,
      formationId: '',
      formateurId: ''
    };
    this.showForm = true;
  }

  showEditForm(session: SessionFormation): void {
    this.isEditing = true;
    this.selectedSession = session;
    this.formData = { ...session };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.isEditing = false;
    this.selectedSession = null;
  }

  submitForm(): void {
    if (this.isEditing && this.selectedSession) {
      this.sessionService.update(this.selectedSession.id, this.formData).subscribe({
        next: () => { this.loadSessions(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de modifier.'; }
      });
    } else {
      this.sessionService.create(this.formData).subscribe({
        next: () => { this.loadSessions(); this.closeForm(); },
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de créer.'; }
      });
    }
  }

  deleteSession(id: string): void {
    if (confirm('Supprimer cette session ?')) {
      this.sessionService.delete(id).subscribe({
        next: () => this.loadSessions(),
        error: (error) => { console.error('Erreur:', error); this.errorMessage = 'Impossible de supprimer.'; }
      });
    }
  }

  getStatutClass(statut: SessionStatut): string {
    switch (statut) {
      case SessionStatut.PLANIFIEE: return 'statut-planifiee';
      case SessionStatut.EN_COURS: return 'statut-en-cours';
      case SessionStatut.TERMINEE: return 'statut-terminee';
      case SessionStatut.ANNULEE: return 'statut-annulee';
      default: return '';
    }
  }

  getStatutLabel(statut: SessionStatut): string {
    switch (statut) {
      case SessionStatut.PLANIFIEE: return 'Planifiée';
      case SessionStatut.EN_COURS: return 'En cours';
      case SessionStatut.TERMINEE: return 'Terminée';
      case SessionStatut.ANNULEE: return 'Annulée';
      default: return statut;
    }
  }
}
