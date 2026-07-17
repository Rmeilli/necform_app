import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardService } from '../../core/services/dashboard.service';
import { DashboardStats } from '../../shared/models/dashboard.model';

// Le composant Dashboard affiche les statistiques de l'application
// OnInit est un lifecycle hook qui s'exécute quand le composant est initialisé
@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  // Variable pour stocker les données du dashboard
  dashboardData: DashboardStats | null = null;

  // Variable pour gérer l'état de chargement
  isLoading = false;

  // Variable pour gérer les erreurs
  errorMessage = '';

  constructor(
    private dashboardService: DashboardService,
    private cdr: ChangeDetectorRef
  ) {}

  // ngOnInit est appelé automatiquement par Angular après la création du composant
  ngOnInit(): void {
    console.log('Dashboard component initialized');
    this.loadDashboardData();
  }

  // Méthode pour charger les données du dashboard depuis l'API
  loadDashboardData(): void {
    console.log('Loading dashboard data...');
    this.isLoading = true;
    this.errorMessage = '';
    this.cdr.detectChanges();

    // subscribe permet de s'abonner à l'Observable pour recevoir les données quand elles arrivent
    this.dashboardService.getDashboard().subscribe({
      // next: callback appelé quand la requête réussit
      next: (data) => {
        console.log('Dashboard data received:', data);
        this.dashboardData = data;
        this.isLoading = false;
        this.cdr.detectChanges();
        console.log('isLoading:', this.isLoading, 'dashboardData:', this.dashboardData);
      },
      // error: callback appelé quand la requête échoue
      error: (error) => {
        console.error('Erreur lors du chargement du dashboard:', error);
        this.errorMessage = 'Impossible de charger les données du tableau de bord.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }
}
