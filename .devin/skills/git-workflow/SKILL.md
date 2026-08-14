# Git Workflow for Necform Project

## Purpose
Standardized Git workflow for all development tickets in the Necform project. Each ticket follows a consistent branch, commit, and pull request process.

## Branch Strategy

### Main Branches
- **main**: Production-ready code, deployed to production
- **develop**: Integration branch for completed features
- **feature/NEC-XXX-ticket-name**: Feature branches for individual tickets

### Branch Creation Workflow
For each development ticket, create a dedicated feature branch:

```bash
# Format: feature/NEC-XXX-ticket-description
git checkout develop
git pull origin develop
git checkout -b feature/NEC-001-apprenant-entity-repository
```

## Development Workflow

### 1. Start New Ticket
```bash
# Ensure develop is up to date
git checkout develop
git pull origin develop

# Create feature branch
git checkout -b feature/NEC-XXX-ticket-description

# Verify branch
git branch
```

### 2. Development Work
- Implement the ticket according to technical strategy
- Follow development steps provided in ticket
- Write tests for new functionality
- Update documentation if needed

### 3. Commit Changes
```bash
# Stage changes
git add .

# Commit with conventional message
git commit -m "NEC-XXX: Brief description of changes"

# Examples:
# NEC-001: Create Apprenant entity with JPA annotations
# NEC-004: Implement apprenant list component with pagination
# NEC-018: Add document generation service with PDF support
```

### 4. Push Feature Branch
```bash
# Push to remote
git push origin feature/NEC-XXX-ticket-description

# Set upstream if first push
git push -u origin feature/NEC-XXX-ticket-description
```

### 5. Create Pull Request
- Go to GitHub/GitLab repository
- Create new pull request
- **Source**: `feature/NEC-XXX-ticket-description`
- **Target**: `develop`
- **Title**: `NEC-XXX: Ticket Title`
- **Description**: Use PR template (see below)

### 6. Pull Request Template
```markdown
## Ticket #NEC-XXX: [Ticket Title]

### Description
[Brief description of what was implemented and why]

### Changes Made
- [List of key changes implemented]
- [Files modified/created]
- [New features added]
- [Bugs fixed]

### Technical Strategy
[Summary of technical approach used]

### Development Steps Completed
- [x] Step 1: [Description]
- [x] Step 2: [Description]
- [x] Step 3: [Description]
- [ ] Step 4: [Description]

### Testing
- [x] Unit tests written and passing
- [x] Integration tests written and passing
- [x] Manual testing completed
- [x] No regression in existing functionality

### Screenshots (if applicable)
[Add screenshots for UI changes or before/after comparisons]

### Checklist
- [ ] Code follows project standards and conventions
- [ ] Tests added/updated for new functionality
- [ ] Documentation updated (README, API docs, etc.)
- [ ] No breaking changes to existing API
- [ ] Code compiles without warnings
- [ ] Linting passes
- [ ] Security review completed (if applicable)

### Related Issues
- Closes #NEC-XXX
- Related to #NEC-YYY

### Additional Notes
[Any additional context for reviewers]
```

### 7. Review Process
- **Product Owner** reviews the pull request
- Request changes if needed
- Developer updates branch based on feedback
- Re-commit and push changes
- Repeat review until approved

### 8. Merge & Cleanup
```bash
# After PR is approved and merged:
# Delete local feature branch
git branch -d feature/NEC-XXX-ticket-description

# Delete remote feature branch
git push origin --delete feature/NEC-XXX-ticket-description

# Update local develop branch
git checkout develop
git pull origin develop
```

### 9. Update Ticket Status
- Mark ticket as "Terminé" in project management
- Move to appropriate column (Done/Completed)
- Add link to merged PR in ticket comments
- Document any lessons learned

## Commit Message Convention

### Format
```
NEC-XXX: Imperative mood description of changes
```

### Examples
```bash
NEC-001: Create Apprenant entity with JPA annotations
NEC-002: Implement Apprenant service layer with business logic
NEC-003: Add Apprenant REST controller with CRUD endpoints
NEC-004: Create apprenant list component with pagination
NEC-018: Implement document generation service with Apache POI
NEC-027: Configure Keycloak authentication with JWT
```

### Good Practices
- Use imperative mood ("Create" not "Created")
- Keep first line under 50 characters
- Reference ticket number in every commit
- Be specific about what was changed
- Group related changes in single commit

## Branch Management

### When to Create a New Branch
- Starting work on a new ticket
- Implementing a feature that requires multiple commits
- Fixing a bug that needs separate testing
- Experimenting with new approaches

### When to Use develop Branch
- Integrating completed features
- Testing feature combinations
- Preparing for release to main
- Hotfixes that need immediate deployment

### Branch Protection Rules (Recommended)
- **main branch**: Require PR approval,禁止直接推送
- **develop branch**: Require PR approval,禁止直接推送
- **feature branches**: No restrictions,允许直接推送

## Conflict Resolution

### When Conflicts Occur
```bash
# Update develop branch
git checkout develop
git pull origin develop

# Go back to feature branch
git checkout feature/NEC-XXX-ticket-description

# Merge develop into feature branch
git merge develop

# Resolve conflicts manually
# Edit conflicted files
# Mark as resolved: git add <file>

# Complete merge
git commit -m "NEC-XXX: Resolve merge conflicts with develop"

# Push updated branch
git push origin feature/NEC-XXX-ticket-description
```

## Release Workflow

### Preparing for Release
```bash
# From develop branch
git checkout develop
git pull origin develop

# Create release branch
git checkout -b release/v1.0.0

# Perform final testing
# Update version numbers
# Update CHANGELOG

# Merge release to main
git checkout main
git merge release/v1.0.0

# Tag release
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin main --tags

# Merge back to develop
git checkout develop
git merge release/v1.0.0
git push origin develop

# Delete release branch
git branch -d release/v1.0.0
```

## Hotfix Workflow

### Emergency Fixes
```bash
# From main branch
git checkout main
git pull origin main

# Create hotfix branch
git checkout -b hotfix/NEC-XXX-critical-bug-fix

# Implement fix
git add .
git commit -m "NEC-XXX: Fix critical bug in production"

# Push and create PR to main
git push origin hotfix/NEC-XXX-critical-bug-fix

# After merge to main, also merge to develop
git checkout develop
git merge hotfix/NEC-XXX-critical-bug-fix
git push origin develop

# Delete hotfix branch
git branch -d hotfix/NEC-XXX-critical-bug-fix
```

## Best Practices

### Do's
- Always create a feature branch for each ticket
- Write descriptive commit messages with ticket numbers
- Keep branches focused on single feature/ticket
- Pull latest develop before starting work
- Test thoroughly before creating PR
- Review your own PR before requesting review
- Delete feature branches after merge
- Update ticket status after merge

### Don'ts
- Don't commit directly to main or develop
- Don't include unrelated changes in single commit
- Don't let feature branches diverge too much from develop
- Don't merge without review (except hotfixes)
- Don't leave stale branches on remote
- Don't force push unless absolutely necessary
- Don't ignore merge conflicts
- Don't skip testing before PR

## Ticket-Specific Workflows

### Backend Tickets (NEC-001 to NEC-025, NEC-027 to NEC-032, NEC-034 to NEC-036, NEC-038)
```bash
git checkout -b feature/NEC-XXX-ticket-name
# Backend development in necform directory
cd necform
# Implement changes...
git add .
git commit -m "NEC-XXX: Backend change description"
git push origin feature/NEC-XXX-ticket-name
# Create PR to develop
```

### Frontend Tickets (NEC-004 to NEC-006, NEC-010, NEC-013, NEC-017, NEC-020, NEC-023, NEC-026, NEC-028, NEC-033, NEC-037, NEC-039)
```bash
git checkout -b feature/NEC-XXX-ticket-name
# Frontend development in necform-front directory
cd necform-front
# Implement changes...
git add .
git commit -m "NEC-XXX: Frontend change description"
git push origin feature/NEC-XXX-ticket-name
# Create PR to develop
```

### Infrastructure Tickets (NEC-029, NEC-030, NEC-034, NEC-035)
```bash
git checkout -b feature/NEC-XXX-ticket-name
# Infrastructure changes in project root
# Implement Docker, deployment, etc.
git add .
git commit -m "NEC-XXX: Infrastructure change description"
git push origin feature/NEC-XXX-ticket-name
# Create PR to develop
```

### Full Stack Tickets (NEC-038)
```bash
git checkout -b feature/NEC-XXX-ticket-name
# Changes in both backend and frontend
cd necform && # backend changes
git add .
git commit -m "NEC-XXX: Backend changes"
cd ../necform-front && # frontend changes
git add .
git commit -m "NEC-XXX: Frontend changes"
git push origin feature/NEC-XXX-ticket-name
# Create PR to develop
```

## Integration with Project Management

### Ticket Status Workflow
1. **Nouveau** → Create feature branch
2. **En cours** → Development in progress
3. **En revue** → PR created, awaiting review
4. **Terminé** → PR merged, branch deleted

### Automated Status Updates (Optional)
Configure GitHub/GitLab integrations to automatically update ticket status based on PR events:
- PR created → "En revue"
- PR merged → "Terminé"
- PR closed without merge → "Nouveau"

## Troubleshooting

### Common Issues

**Push rejected due to non-fast-forward**
```bash
git pull origin feature/NEC-XXX-ticket-name
# Resolve conflicts if any
git push origin feature/NEC-XXX-ticket-name
```

**PR cannot be merged due to conflicts**
```bash
git checkout feature/NEC-XXX-ticket-name
git pull origin develop
# Resolve conflicts
git commit -m "NEC-XXX: Resolve merge conflicts"
git push origin feature/NEC-XXX-ticket-name
```

**Wrong branch name**
```bash
git branch -m wrong-name feature/NEC-XXX-correct-name
git push origin :wrong-name feature/NEC-XXX-correct-name
```

**Accidental commit to wrong branch**
```bash
git reset HEAD~1  # Undo last commit
git checkout feature/NEC-XXX-correct-branch
git commit -m "NEC-XXX: Correct commit"
```

## Quick Reference

### Essential Commands
```bash
# Start new ticket
git checkout develop && git pull && git checkout -b feature/NEC-XXX-name

# Commit changes
git add . && git commit -m "NEC-XXX: Description"

# Push branch
git push -u origin feature/NEC-XXX-name

# Update branch with latest develop
git fetch origin && git rebase origin/develop

# Cleanup after merge
git branch -d feature/NEC-XXX-name && git push origin --delete feature/NEC-XXX-name
```

### Branch Status Check
```bash
# See all branches
git branch -a

# See current branch
git branch --show-current

# See branch status vs develop
git log develop..feature/NEC-XXX-name
```

## Important Notes
- Always pull latest develop before starting work
- Never commit directly to main or develop
- Keep commits atomic and focused
- Write meaningful commit messages
- Test thoroughly before creating PR
- Review your own changes first
- Delete merged branches promptly
- Document complex decisions in commit messages
- Use PR descriptions to provide context
- Follow project-specific conventions