import { Component, OnInit, inject, Output, EventEmitter, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FormationService } from '../../../core/services/formation.service';
import { Formation } from '../../models/formation.model';
import { CATEGORIES, Category } from '../../models/category.model';
import { CategoryCard } from '../category-card/category-card';
import { FormationCard } from '../formation-card/formation-card';

@Component({
  selector: 'app-formation-catalog',
  standalone: true,
  imports: [CommonModule, FormsModule, CategoryCard, FormationCard],
  templateUrl: './formation-catalog.html',
  styleUrl: './formation-catalog.css'
})
export class FormationCatalog implements OnInit {
  private formationService = inject(FormationService);
  private cdr = inject(ChangeDetectorRef);
  
  @Output() formationClick = new EventEmitter<Formation>();
  
  formations: Formation[] = [];
  filteredFormations: Formation[] = [];
  categories: Category[] = [];
  isLoading = false;
  errorMessage = '';
  
  searchTerm = '';
  selectedCategory: Category | null = null;
  showAllCategories = true;
  
  ngOnInit(): void {
    this.categories = JSON.parse(JSON.stringify(CATEGORIES));
    this.loadFormations();
  }
  
  loadFormations(): void {
    this.isLoading = true;
    this.formationService.getAll().subscribe({
      next: (data) => {
        this.formations = data;
        this.updateCategoryCounts();
        this.filterFormations();
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = 'Impossible de charger les formations.';
        this.isLoading = false;
      }
    });
  }
  
  updateCategoryCounts(): void {
    this.categories = this.categories.map(category => ({
      ...category,
      count: this.formations.filter(f => {
        const formationCategory = f.categorie.toLowerCase().trim();
        const categoryName = category.name.toLowerCase().trim();
        return formationCategory === categoryName;
      }).length
    }));
  }
  
  filterFormations(): void {
    this.filteredFormations = this.formations.filter(formation => {
      const matchesSearch = this.searchTerm === '' || 
        formation.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        formation.description.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        formation.categorie.toLowerCase().includes(this.searchTerm.toLowerCase());
      
      const matchesCategory = !this.selectedCategory || 
        formation.categorie.toLowerCase() === this.selectedCategory.name.toLowerCase();
      
      return matchesSearch && matchesCategory;
    });
  }
  
  onSearchChange(): void {
    this.filterFormations();
  }
  
  selectCategory(category: Category): void {
    if (this.selectedCategory === category) {
      this.selectedCategory = null;
    } else {
      this.selectedCategory = category;
    }
    this.filterFormations();
  }
  
  clearFilters(): void {
    this.searchTerm = '';
    this.selectedCategory = null;
    this.filterFormations();
  }
  
  onFormationClick(formation: Formation): void {
    this.formationClick.emit(formation);
  }
}
