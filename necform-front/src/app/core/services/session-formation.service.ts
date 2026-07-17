import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  SessionFormation, 
  CreateSessionFormation 
} from '../../shared/models/session-formation.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class SessionFormationService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/sessions`;
  }

  create(request: CreateSessionFormation): Observable<SessionFormation> {
    return this.http.post<SessionFormation>(this.endpoint, request);
  }

  getAll(): Observable<SessionFormation[]> {
    return this.http.get<SessionFormation[]>(this.endpoint);
  }

  getById(id: string): Observable<SessionFormation> {
    return this.http.get<SessionFormation>(`${this.endpoint}/${id}`);
  }

  update(id: string, request: Partial<SessionFormation>): Observable<SessionFormation> {
    return this.http.put<SessionFormation>(`${this.endpoint}/${id}`, request);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
