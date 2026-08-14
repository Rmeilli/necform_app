import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Formation } from '../../models/formation.model';
import { getCategoryIcon, getCategoryColor } from '../../models/category.model';

@Component({
  selector: 'app-formation-card',
  standalone: true,
  templateUrl: './formation-card.html',
  styleUrl: './formation-card.css'
})
export class FormationCard {
  @Input() formation!: Formation;
  @Input() showActions = false;
  @Output() cardClick = new EventEmitter<Formation>();

  getCategoryIcon(): string {
    return getCategoryIcon(this.formation.categorie);
  }

  getCategoryColor(): string {
    return getCategoryColor(this.formation.categorie);
  }

  onClick(): void {
    this.cardClick.emit(this.formation);
  }
}
