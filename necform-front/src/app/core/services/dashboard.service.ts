import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DashboardStats } from '../../shared/models/dashboard.model';
import { ApiService } from './api.service';

// Service pour récupérer les statistiques du tableau de bord
@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/dashboard`;
  }

  // Récupérer les statistiques du tableau de bord
  // Observable est un type RxJS qui permet de gérer les opérations asynchrones
  getDashboard(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(this.endpoint);
  }
}
