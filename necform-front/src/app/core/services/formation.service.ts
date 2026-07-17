import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  Formation, 
  CreateFormation 
} from '../../shared/models/formation.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/formations`;
  }

  create(request: CreateFormation): Observable<Formation> {
    return this.http.post<Formation>(this.endpoint, request);
  }

  getAll(): Observable<Formation[]> {
    return this.http.get<Formation[]>(this.endpoint);
  }

  getById(id: string): Observable<Formation> {
    return this.http.get<Formation>(`${this.endpoint}/${id}`);
  }

  update(id: string, request: Partial<Formation>): Observable<Formation> {
    return this.http.put<Formation>(`${this.endpoint}/${id}`, request);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
