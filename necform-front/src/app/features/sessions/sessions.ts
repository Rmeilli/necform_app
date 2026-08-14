import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SessionFormationService } from '../../core/services/session-formation.service';
import { FormationService } from '../../core/services/formation.service';
import { UtilisateurService } from '../../core/services/utilisateur.service';
import { SessionFormation, CreateSessionFormation } from '../../shared/models/session-formation.model';
import { Formation } from '../../shared/models/formation.model';
import { Utilisateur } from '../../shared/models/utilisateur.model';
import { SessionStatut, TypeUtilisateur } from '../../shared/models/enums';

@Component({
  selector: 'app-sessions',
  imports: [CommonModule, FormsModule],
  templateUrl: './sessions.html',
  styleUrl: './sessions.css'
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

  // Statistics
  totalSessions = 0;
  sessionsEnCours = 0;
  sessionsAVenir = 0;
  sessionsTerminees = 0;

  // Calendar
  currentMonth: Date = new Date();
  calendarDays: any[] = [];
  selectedDate: Date | null = null;

  // Dropdown data
  formations: Formation[] = [];
  formateurs: Utilisateur[] = [];

  constructor(
    private sessionService: SessionFormationService,
    private formationService: FormationService,
    private utilisateurService: UtilisateurService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadSessions();
    this.loadFormations();
    this.loadFormateurs();
  }

  loadSessions(): void {
    this.isLoading = true;
    this.cdr.detectChanges();
    this.sessionService.getAll().subscribe({
      next: (data) => {
        this.sessions = data;
        this.updateStatistics();
        this.generateCalendar();
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = 'Impossible de charger.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  updateStatistics(): void {
    this.totalSessions = this.sessions.length;
    this.sessionsEnCours = this.sessions.filter(s => s.statut === SessionStatut.EN_COURS).length;
    this.sessionsAVenir = this.sessions.filter(s => s.statut === SessionStatut.PLANIFIEE).length;
    this.sessionsTerminees = this.sessions.filter(s => s.statut === SessionStatut.TERMINEE).length;
  }

  generateCalendar(): void {
    const year = this.currentMonth.getFullYear();
    const month = this.currentMonth.getMonth();
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const startingDay = firstDay.getDay();
    const totalDays = lastDay.getDate();

    this.calendarDays = [];

    // Empty cells for days before the first day of the month
    for (let i = 0; i < startingDay; i++) {
      this.calendarDays.push({ day: null, sessions: [], isToday: false });
    }

    // Days of the month
    const today = new Date();
    for (let day = 1; day <= totalDays; day++) {
      const date = new Date(year, month, day);
      const isToday = date.toDateString() === today.toDateString();
      const daySessions = this.sessions.filter(session => {
        const sessionDate = new Date(session.dateDebut);
        return sessionDate.getDate() === day &&
               sessionDate.getMonth() === month &&
               sessionDate.getFullYear() === year;
      });

      this.calendarDays.push({ day, sessions: daySessions, isToday, date });
    }
  }

  previousMonth(): void {
    this.currentMonth = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() - 1);
    this.generateCalendar();
  }

  nextMonth(): void {
    this.currentMonth = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() + 1);
    this.generateCalendar();
  }

  getMonthName(): string {
    const months = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 
                    'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
    return months[this.currentMonth.getMonth()];
  }

  getEventColor(statut: SessionStatut): string {
    switch (statut) {
      case SessionStatut.EN_COURS:
        return '#22c55e'; // Green
      case SessionStatut.PLANIFIEE:
        return '#3b82f6'; // Blue
      case SessionStatut.TERMINEE:
        return '#6b7280'; // Gray
      case SessionStatut.ANNULEE:
        return '#ef4444'; // Red
      default:
        return '#3b82f6';
    }
  }

  selectDate(date: Date): void {
    this.selectedDate = date;
    this.showCreateForm();
    if (this.formData && date) {
      this.formData.dateDebut = date.toISOString().split('T')[0];
    }
  }

  loadFormations(): void {
    this.formationService.getAll().subscribe({
      next: (data) => {
        this.formations = data;
      },
      error: (error) => {
        console.error('Erreur chargement formations:', error);
      }
    });
  }

  loadFormateurs(): void {
    this.utilisateurService.getAll().subscribe({
      next: (data) => {
        this.formateurs = data.filter(u => u.type === TypeUtilisateur.FORMATEUR);
      },
      error: (error) => {
        console.error('Erreur chargement formateurs:', error);
      }
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
