import { Component, OnInit, ChangeDetectorRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardService } from '../../core/services/dashboard.service';
import { RoleService } from '../../core/services/role.service';
import { DashboardStats } from '../../shared/models/dashboard.model';
import { TypeUtilisateur } from '../../shared/models/enums';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  dashboardData: DashboardStats | null = null;
  isLoading = false;
  errorMessage = '';
  currentRole: TypeUtilisateur | null = null;
  userName = '';

  private dashboardService = inject(DashboardService);
  private roleService = inject(RoleService);
  private cdr = inject(ChangeDetectorRef);

  ngOnInit(): void {
    console.log('Dashboard ngOnInit called');
    this.currentRole = this.roleService.getCurrentRole();
    console.log('Current role:', this.currentRole);
    this.userName = this.roleService.getCurrentUserName();
    console.log('User name:', this.userName);
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    console.log('Loading dashboard data...');
    this.isLoading = true;
    this.errorMessage = '';
    this.cdr.detectChanges();

    this.dashboardService.getDashboard().subscribe({
      next: (data) => {
        console.log('Dashboard data received:', data);
        this.dashboardData = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Erreur lors du chargement du dashboard:', error);
        this.errorMessage = 'Impossible de charger les données du tableau de bord.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  isAdmin(): boolean {
    return this.currentRole === TypeUtilisateur.ADMIN;
  }

  isFormateur(): boolean {
    return this.currentRole === TypeUtilisateur.FORMATEUR;
  }

  isEntreprise(): boolean {
    return this.currentRole === TypeUtilisateur.ENTREPRISE;
  }

  isApprenant(): boolean {
    return this.currentRole === TypeUtilisateur.APPRENANT;
  }

  getWelcomeMessage(): string {
    if (this.userName) {
      return `Bienvenue, ${this.userName}`;
    }
    return 'Bienvenue';
  }

  getRoleLabel(): string {
    switch (this.currentRole) {
      case TypeUtilisateur.ADMIN:
        return 'Administrateur';
      case TypeUtilisateur.FORMATEUR:
        return 'Formateur';
      case TypeUtilisateur.ENTREPRISE:
        return 'Entreprise';
      case TypeUtilisateur.APPRENANT:
        return 'Apprenant';
      default:
        return '';
    }
  }
}
