import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  Utilisateur, 
  CreateUtilisateur, 
  UpdateUtilisateur 
} from '../../shared/models/utilisateur.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/utilisateurs`;
  }

  create(request: CreateUtilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.endpoint, request);
  }

  getAll(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.endpoint);
  }

  getById(id: string): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.endpoint}/${id}`);
  }

  update(id: string, request: UpdateUtilisateur): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.endpoint}/${id}`, request);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
