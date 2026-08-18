import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Formation } from '../../models/formation.model';
import { getCategoryIcon, getCategoryColor } from '../../models/category.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formation-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './formation-details.html',
  styleUrl: './formation-details.css'
})
export class FormationDetails {
  @Input() formation!: Formation;
  @Input() showCloseButton = true;
  
  private router = inject(Router);

  getCategoryIcon(): string {
    return getCategoryIcon(this.formation.categorie);
  }

  getCategoryColor(): string {
    return getCategoryColor(this.formation.categorie);
  }

  onClose(): void {
    this.router.navigate(['/formations']);
  }

  onEnroll(): void {
    // TODO: Implement enrollment logic
    console.log('Enroll in formation:', this.formation.id);
  }
}
