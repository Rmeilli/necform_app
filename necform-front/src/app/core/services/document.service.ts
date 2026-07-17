import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { 
  Document, 
  CreateDocument 
} from '../../shared/models/document.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  private readonly endpoint: string;

  constructor(
    private http: HttpClient,
    private apiService: ApiService
  ) {
    this.endpoint = `${this.apiService.getApiUrl()}/documents`;
  }

  create(request: CreateDocument): Observable<Document> {
    return this.http.post<Document>(this.endpoint, request);
  }

  getAll(): Observable<Document[]> {
    return this.http.get<Document[]>(this.endpoint);
  }

  getById(id: string): Observable<Document> {
    return this.http.get<Document>(`${this.endpoint}/${id}`);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
