# Angular Frontend Development

## Purpose
High-level skill for Angular frontend development in the Necform project. Handles component development, state management, API integration, and authentication.

## Project Structure
- **Frontend Root**: `C:\Users\User\Downloads\Necform\necform-front`
- **Framework**: Angular 22.0.0 with TypeScript
- **Styling**: Bootstrap 5.3.8
- **HTTP Client**: Axios
- **Authentication**: Keycloak Angular
- **Build Tool**: Angular CLI
- **Testing**: Vitest

## Capabilities

### Component Development
- Create new Angular components with proper structure
- Implement reactive forms with validation
- Add template-driven forms
- Create reusable components
- Implement component communication (Input/Output)
- Add lifecycle hooks

### Service Development
- Create services for API communication
- Implement HTTP requests with Axios
- Add error handling and retry logic
- Create services for state management
- Implement data transformation
- Add loading states and progress indicators

### Authentication & Authorization
- Configure Keycloak Angular integration
- Implement role-based UI rendering
- Handle authentication state
- Protect routes with route guards
- Manage token refresh logic
- Handle logout functionality

### Routing & Navigation
- Configure Angular Router
- Create lazy-loaded modules
- Implement route guards (canActivate, canLoad)
- Add route parameters and query params
- Handle navigation events
- Create breadcrumb navigation

### State Management
- Implement service-based state management
- Use RxJS subjects for reactive state
- Handle observables and subscriptions
- Implement data caching
- Add error state handling

### Styling & UI
- Create responsive layouts with Bootstrap
- Implement custom CSS components
- Add Bootstrap icons
- Create theme support
- Handle dark mode if needed
- Implement animations

### Testing
- Write unit tests for components
- Test services with mocked HTTP calls
- Test pipe functionality
- Test directive behavior
- Create integration tests
- Test authentication flows

## Common Commands

### Development
```bash
# Navigate to frontend directory
cd necform-front

# Install dependencies
npm install

# Start development server
npm start

# Build for production
npm run build

# Watch mode for development
npm run watch
```

### Testing
```bash
# Run unit tests
npm test

# Run tests with coverage
npm test -- --coverage

# Run specific test file
npm test -- sessions.service.spec.ts
```

### Code Generation
```bash
# Generate new component
ng generate component components/my-component

# Generate new service
ng generate service services/my-service

# Generate new module
ng generate module modules/my-module

# Generate new guard
ng generate guard guards/my-guard
```

## Code Conventions

### Component Structure
```typescript
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MyService } from '../../services/my.service';
import { MyModel } from '../../models/my.model';

@Component({
  selector: 'app-my-component',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './my-component.component.html',
  styleUrls: ['./my-component.component.css']
})
export class MyComponent implements OnInit {
  data: MyModel[] = [];
  loading = false;
  error: string | null = null;

  constructor(private myService: MyService) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loading = true;
    this.myService.getData().subscribe({
      next: (data) => {
        this.data = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load data';
        this.loading = false;
      }
    });
  }
}
```

### Service Structure
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MyModel } from '../models/my.model';

@Injectable({
  providedIn: 'root'
})
export class MyService {
  private apiUrl = 'http://localhost:8080/api/entities';

  constructor(private http: HttpClient) {}

  getData(): Observable<MyModel[]> {
    return this.http.get<MyModel[]>(this.apiUrl);
  }

  createData(data: MyModel): Observable<MyModel> {
    return this.http.post<MyModel>(this.apiUrl, data);
  }
}
```

### Route Guard Structure
```typescript
import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
    private keycloak: KeycloakService,
    private router: Router
  ) {}

  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    const requiredRoles = route.data['roles'];
    const isLoggedIn = await this.keycloak.isLoggedIn();
    
    if (!isLoggedIn) {
      this.router.navigate(['/login']);
      return false;
    }

    const userRoles = await this.keycloak.getUserRoles();
    const hasRequiredRole = requiredRoles.some((role: string) => 
      userRoles.includes(role)
    );

    if (!hasRequiredRole) {
      this.router.navigate(['/unauthorized']);
      return false;
    }

    return true;
  }
}
```

## Configuration Files
- **Angular Config**: `angular.json`
- **TypeScript Config**: `tsconfig.json`
- **Package Config**: `package.json`
- **Environment Config**: `src/environments/`

## Dependencies Added
- Angular 22.0.0 (core, common, forms, router, etc.)
- Axios for HTTP requests
- Bootstrap 5.3.8 for styling
- Bootstrap Icons
- Keycloak Angular for authentication
- RxJS for reactive programming
- Express for SSR support

## Project Structure
```
src/
├── app/
│   ├── core/              # Core services, guards, interceptors
│   ├── features/          # Feature modules
│   │   ├── sessions/
│   │   ├── users/
│   │   └── ...
│   ├── shared/            # Shared components, pipes, directives
│   ├── models/            # TypeScript interfaces/models
│   └── app.component.*    # Root component
├── environments/          # Environment configs
└── styles/               # Global styles
```

## Development Workflow
1. **Git Setup**: Create feature branch for the ticket
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/NEC-XXX-ticket-description
   ```
2. Generate component/service using Angular CLI
3. Define TypeScript interfaces for data models
4. Implement service for API communication
5. Create component with template and styles
6. Add form validation if needed
7. Implement error handling
8. Add authentication/authorization if needed
9. Create routing configuration
10. Write unit tests
11. Test with development server
12. **Git Commit**: Commit changes with ticket reference
    ```bash
    git add .
    git commit -m "NEC-XXX: Frontend change description"
    git push origin feature/NEC-XXX-ticket-description
    ```
13. **Pull Request**: Create PR to develop branch and request review

## Best Practices
- Use standalone components (Angular 15+)
- Implement reactive forms for complex forms
- Use RxJS operators for data transformation
- Add loading states for async operations
- Implement proper error handling
- Use lazy loading for feature modules
- Create reusable components
- Follow accessibility guidelines
- Add ARIA attributes for screen readers
- Implement proper cleanup in ngOnDestroy
- Use trackBy for ngFor loops
- Avoid direct DOM manipulation
- Use CSS classes instead of inline styles
- Implement responsive design with Bootstrap

## Authentication Flow
1. User accesses protected route
2. RoleGuard checks authentication
3. If not authenticated, redirect to Keycloak login
4. Keycloak validates credentials
5. User redirected back with token
6. Token stored in Keycloak service
7. HTTP interceptor adds token to requests
8. Route access granted based on roles

## API Integration
- Base URL configured in environment files
- Services use HttpClient or Axios
- Interceptors handle authentication headers
- Error interceptor handles HTTP errors
- Response data transformed to models
- Loading states managed in components

## Important Notes
- Always unsubscribe from observables (use async pipe or takeUntil)
- Use TypeScript strict mode
- Implement proper null checks
- Add loading indicators for async operations
- Handle network errors gracefully
- Use environment-specific configurations
- Test on different screen sizes
- Follow Angular style guide
- Keep components small and focused
- Use input decorators for parent-child communication
- Use output decorators for child-parent communication