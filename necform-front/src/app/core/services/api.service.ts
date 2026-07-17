import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Ce service de base contient l'URL de l'API backend
// Injectable signifie que ce service peut être injecté dans d'autres composants
@Injectable({
  providedIn: 'root' // Disponible dans toute l'application
})
export class ApiService {
  // URL de base de l'API backend
  private readonly apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Getter pour accéder à l'URL de base
  getApiUrl(): string {
    return this.apiUrl;
  }
}
