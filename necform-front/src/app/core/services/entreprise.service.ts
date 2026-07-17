import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  Entreprise, 
  CreateEntreprise, 
  UpdateEntreprise 
} from '../../shared/models/entreprise.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/entreprises`;
  }

  create(request: CreateEntreprise): Observable<Entreprise> {
    return this.http.post<Entreprise>(this.endpoint, request);
  }

  getAll(): Observable<Entreprise[]> {
    return this.http.get<Entreprise[]>(this.endpoint);
  }

  getById(id: string): Observable<Entreprise> {
    return this.http.get<Entreprise>(`${this.endpoint}/${id}`);
  }

  update(id: string, request: UpdateEntreprise): Observable<Entreprise> {
    return this.http.put<Entreprise>(`${this.endpoint}/${id}`, request);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
