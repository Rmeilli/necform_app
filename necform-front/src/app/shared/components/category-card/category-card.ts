import { Component, Input } from '@angular/core';
import { Category } from '../../models/category.model';

@Component({
  selector: 'app-category-card',
  standalone: true,
  templateUrl: './category-card.html',
  styleUrl: './category-card.css'
})
export class CategoryCard {
  @Input() category!: Category;
  @Input() isSelected = false;

  onClick(): void {
    // Event will be emitted by parent component
  }
}
