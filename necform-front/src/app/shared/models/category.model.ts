export interface Category {
  id: string;
  name: string;
  icon: string;
  color: string;
  count: number;
}

export const CATEGORIES: Category[] = [
  {
    id: 'ia-automation',
    name: 'Intelligence artificielle & Automatisations',
    icon: '🤖',
    color: '#6366f1',
    count: 0
  },
  {
    id: 'cybersecurite',
    name: 'Cybersécurité',
    icon: '🔒',
    color: '#ef4444',
    count: 0
  },
  {
    id: 'devops',
    name: 'DevOps & Automatisation',
    icon: '⚙️',
    color: '#f59e0b',
    count: 0
  },
  {
    id: 'cloud',
    name: 'Cloud Computing',
    icon: '☁️',
    color: '#3b82f6',
    count: 0
  },
  {
    id: 'data-bi',
    name: 'Data, reporting et BI',
    icon: '📊',
    color: '#10b981',
    count: 0
  },
  {
    id: 'dev-numerique',
    name: 'Métiers du numérique & développement',
    icon: '💻',
    color: '#8b5cf6',
    count: 0
  },
  {
    id: 'web',
    name: 'Création de sites web',
    icon: '🌐',
    color: '#ec4899',
    count: 0
  },
  {
    id: 'bureautique',
    name: 'Bureautique & outils numériques',
    icon: '📝',
    color: '#14b8a6',
    count: 0
  },
  {
    id: 'logiciels-metiers',
    name: 'Logiciels métiers & logistique',
    icon: '📦',
    color: '#f97316',
    count: 0
  }
];

export function getCategoryIcon(categoryName: string): string {
  const category = CATEGORIES.find(cat => 
    cat.name.toLowerCase() === categoryName.toLowerCase() ||
    categoryName.toLowerCase().includes(cat.id)
  );
  return category?.icon || '📚';
}

export function getCategoryColor(categoryName: string): string {
  const category = CATEGORIES.find(cat => 
    cat.name.toLowerCase() === categoryName.toLowerCase() ||
    categoryName.toLowerCase().includes(cat.id)
  );
  return category?.color || '#6b7280';
}
