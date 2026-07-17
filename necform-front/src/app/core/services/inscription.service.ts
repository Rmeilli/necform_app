import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  Inscription, 
  CreateInscription, 
  UpdateInscription 
} from '../../shared/models/inscription.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/inscriptions`;
  }

  create(request: CreateInscription): Observable<Inscription> {
    return this.http.post<Inscription>(this.endpoint, request);
  }

  getAll(): Observable<Inscription[]> {
    return this.http.get<Inscription[]>(this.endpoint);
  }

  getById(id: string): Observable<Inscription> {
    return this.http.get<Inscription>(`${this.endpoint}/${id}`);
  }

  update(id: string, request: UpdateInscription): Observable<Inscription> {
    return this.http.put<Inscription>(`${this.endpoint}/${id}`, request);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
