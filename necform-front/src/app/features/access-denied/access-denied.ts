import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-access-denied',
  imports: [CommonModule],
  templateUrl: './access-denied.html',
  styleUrl: './access-denied.css'
})
export class AccessDenied {
  constructor(private router: Router) {}

  goHome(): void {
    this.router.navigate(['/']);
  }

  logout(): void {
    // Keycloak logout will be handled by the auth service
    window.location.href = '/';
  }
}
