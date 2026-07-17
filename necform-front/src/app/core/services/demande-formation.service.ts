import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  DemandeFormation, 
  CreateDemandeFormation, 
  UpdateDemandeFormation 
} from '../../shared/models/demande-formation.model';
import { ApiService } from './api.service';

// Service pour gérer les demandes de formation (CRUD)
@Injectable({
  providedIn: 'root'
})
export class DemandeFormationService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/demandes-formations`;
  }

  // Créer une nouvelle demande de formation
  create(request: CreateDemandeFormation): Observable<DemandeFormation> {
    return this.http.post<DemandeFormation>(this.endpoint, request);
  }

  // Récupérer toutes les demandes de formation
  getAll(): Observable<DemandeFormation[]> {
    return this.http.get<DemandeFormation[]>(this.endpoint);
  }

  // Récupérer une demande de formation par son ID
  getById(id: string): Observable<DemandeFormation> {
    return this.http.get<DemandeFormation>(`${this.endpoint}/${id}`);
  }

  // Mettre à jour une demande de formation
  update(id: string, request: UpdateDemandeFormation): Observable<DemandeFormation> {
    return this.http.put<DemandeFormation>(`${this.endpoint}/${id}`, request);
  }

  // Supprimer une demande de formation
  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
