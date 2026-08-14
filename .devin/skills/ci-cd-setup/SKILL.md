# CI/CD Setup for Necform Project

## Purpose
Comprehensive CI/CD configuration for the Necform project to enforce code quality, branch naming conventions, and prevent common development issues.

## CI/CD Workflows

### 1. Branch Name Validation (`branch-name-validation.yml`)
**Trigger**: Pull requests and pushes to feature/hotfix/release branches

**Purpose**: Enforces proper branch naming conventions

**Allowed Branch Patterns**:
- `feature/NEC-XXX-ticket-description` (e.g., `feature/NEC-001-apprenant-entity-repository`)
- `hotfix/NEC-XXX-ticket-description` (e.g., `hotfix/NEC-015-critical-security-fix`)
- `release/vX.X.X` (e.g., `release/v1.0.0`)
- `develop`
- `main`

**Validation Rules**:
- ✅ Valid: `feature/NEC-001-apprenant-entity-repository`
- ❌ Invalid: `feature-001-apprenant` (missing ticket number format)
- ❌ Invalid: `random-branch-name` (no proper prefix)
- ❌ Invalid: `feature/NEC-abc-description` (non-numeric ticket number)

### 2. Commit Message Validation (`commit-message-validation.yml`)
**Trigger**: Pull requests

**Purpose**: Ensures all commit messages follow the established convention

**Required Format**: `NEC-XXX: Description of changes`

**Examples**:
- ✅ Valid: `NEC-001: Create Apprenant entity with JPA annotations`
- ✅ Valid: `NEC-004: Implement apprenant list component`
- ❌ Invalid: `Create Apprenant entity` (missing ticket number)
- ❌ Invalid: `nec-001: fix bug` (wrong case format)
- ❌ Invalid: `NEC-001 Create Apprenant entity` (missing colon)

**Validation Checks**:
- Format: `NEC-XXX: description`
- Ticket number range: NEC-001 to NEC-039
- Descriptive message after colon

### 3. Backend CI (`backend-ci.yml`)
**Trigger**: Pull requests and pushes to develop/main affecting backend files

**Purpose**: Comprehensive code quality checks for Spring Boot backend

**Checks Performed**:
1. **Code Style**: Checkstyle validation
2. **Static Analysis**: SpotBugs for bug detection
3. **Build**: Maven compilation
4. **Unit Tests**: Maven test execution
5. **Coverage**: JaCoCo coverage report generation
6. **Integration Tests**: Maven verify
7. **Security Scan**: OWASP Dependency Check
8. **Package**: Final JAR creation
9. **SonarQube**: Optional code quality analysis

**Quality Gates**:
- All tests must pass
- Coverage report must be generated
- Build must succeed
- Security scan must complete

**Artifacts Generated**:
- `backend-coverage-report`: JaCoCo HTML coverage report
- `security-scan-results`: OWASP dependency check report
- `backend-jar`: Compiled JAR file

### 4. Frontend CI (`frontend-ci.yml`)
**Trigger**: Pull requests and pushes to develop/main affecting frontend files

**Purpose**: Comprehensive code quality checks for Angular frontend

**Checks Performed**:
1. **Code Style**: ESLint validation
2. **Code Formatting**: Prettier check
3. **Type Safety**: TypeScript compilation check
4. **Unit Tests**: Angular test execution
5. **Coverage**: Test coverage report generation
6. **Build**: Angular production build
7. **Security Audit**: npm audit for vulnerabilities
8. **Performance**: Optional Lighthouse CI

**Quality Gates**:
- All linting checks must pass
- TypeScript compilation must succeed
- All tests must pass
- Build must succeed
- No high-severity security vulnerabilities

**Artifacts Generated**:
- `frontend-coverage-report`: Test coverage HTML report
- `frontend-dist`: Compiled Angular application

### 5. Docker Validation (`docker-validation.yml`)
**Trigger**: Pull requests and pushes affecting Docker files

**Purpose**: Validates Docker configuration and best practices

**Checks Performed**:
1. **Dockerfile Linting**: Syntax and best practices
2. **Docker Compose Validation**: Configuration syntax
3. **Best Practices**: Multi-stage builds, version tags, non-root users
4. **Build Test**: Docker image build validation
5. **Security Scan**: Image vulnerability scanning

**Best Practices Checked**:
- ✅ Uses multi-stage builds
- ✅ Uses specific version tags (not `latest`)
- ✅ Defines non-root user
- ✅ Minimal image size
- ✅ No security vulnerabilities

### 6. Pull Request Validation (`pr-validation.yml`)
**Trigger**: Pull requests (opened, updated, reopened)

**Purpose**: Comprehensive PR quality checks

**Checks Performed**:
1. **PR Description**: Minimum length and content quality
2. **PR Title Format**: Must follow `NEC-XXX: description` format
3. **Linked Issues**: References to NEC tickets
4. **Breaking Changes Label**: Warning for breaking changes
5. **Checklist Items**: Recommended PR checklist
6. **Sensitive Files**: Detection of secret/credential files
7. **Large Files**: Warning for files > 5MB
8. **File Naming**: Java (PascalCase) and TypeScript (kebab-case) conventions

**File Naming Conventions**:
- Java files: `PascalCase.java` (e.g., `ApprenantService.java`)
- TypeScript files: `kebab-case.ts` (e.g., `apprenant.service.ts`)
- No spaces in filenames

**Sensitive File Patterns**:
- `*.pem`, `*.key`, `*.secret`
- `*.credentials`, `passwords.txt`
- `secrets.yaml`, `.env.local`
- `*.p12`, `*.jks`

## Workflow Execution Order

### On Pull Request:
1. **Branch Name Validation** → Validates branch naming
2. **Commit Message Validation** → Validates commit messages
3. **PR Validation** → Validates PR quality
4. **Backend CI** (if backend files changed) → Backend quality checks
5. **Frontend CI** (if frontend files changed) → Frontend quality checks
6. **Docker Validation** (if Docker files changed) → Docker validation

### On Push to develop/main:
1. **Branch Name Validation** → Validates branch naming
2. **Backend CI** (if backend files changed) → Backend quality checks
3. **Frontend CI** (if frontend files changed) → Frontend quality checks
4. **Docker Validation** (if Docker files changed) → Docker validation

## Required GitHub Secrets

### Optional Secrets:
- `SONAR_TOKEN`: For SonarQube code quality analysis
- Other service-specific tokens as needed

## Workflow Files Location
All CI/CD workflows are located in `.github/workflows/`:
- `branch-name-validation.yml`
- `commit-message-validation.yml`
- `backend-ci.yml`
- `frontend-ci.yml`
- `docker-validation.yml`
- `pr-validation.yml`

## Integration with Git Workflow

### Standard Development Flow with CI/CD:
1. **Create Feature Branch** (遵循 `git-workflow` skill)
   ```bash
   git checkout -b feature/NEC-001-apprenant-entity-repository
   ```

2. **Implement Changes** (使用 specialized skills)
   - Backend changes: Use `springboot-backend` skill
   - Frontend changes: Use `angular-frontend` skill

3. **Commit with Proper Format** (CI will validate)
   ```bash
   git commit -m "NEC-001: Create Apprenant entity and repository"
   ```

4. **Push and Create PR** (CI will automatically run)
   ```bash
   git push origin feature/NEC-001-apprenant-entity-repository
   # Create PR to develop branch
   ```

5. **CI Validation** (Automatic)
   - Branch name validation
   - Commit message validation
   - PR quality validation
   - Backend/Frontend CI checks
   - Docker validation (if applicable)

6. **Address CI Failures** (If any)
   - Fix branch naming issues
   - Update commit messages
   - Improve PR description
   - Fix code quality issues
   - Add missing tests

7. **Merge after CI Passes** (Manual)
   - All CI checks must pass
   - Review approved
   - Merge to develop

## Common CI Failures and Solutions

### Branch Name Validation Failure
**Error**: `❌ Branch name 'feature-001' is invalid!`

**Solution**:
```bash
# Rename branch to correct format
git branch -m feature-001 feature/NEC-001-correct-description
git push origin :feature-001 feature/NEC-001-correct-description
```

### Commit Message Validation Failure
**Error**: `❌ Invalid commit message format`

**Solution**:
```bash
# Amend commit message
git commit --amend -m "NEC-001: Correct commit message format"
git push origin feature/NEC-001-name --force
```

### Backend CI Failure
**Error**: Tests failed or build failed

**Solution**:
- Run tests locally: `cd necform && mvn test`
- Fix failing tests
- Check code style issues
- Rebuild and push changes

### Frontend CI Failure
**Error**: Linting failed or tests failed

**Solution**:
- Run linting locally: `cd necform-front && npx eslint .`
- Fix linting issues
- Run tests locally: `npm test`
- Fix failing tests
- Rebuild and push changes

### PR Validation Failure
**Error**: PR description too short or format issues

**Solution**:
- Update PR description with proper details
- Ensure PR title follows `NEC-XXX: description` format
- Add checklist items
- Reference related tickets

## Best Practices

### Development:
- Always follow branch naming conventions
- Use proper commit message format
- Write comprehensive PR descriptions
- Run tests locally before pushing
- Address CI failures promptly

### CI/CD Maintenance:
- Monitor CI execution times
- Update workflows as project evolves
- Add new quality checks as needed
- Review and optimize workflow performance
- Keep dependencies updated

### Security:
- Regularly review security scan results
- Address high-severity vulnerabilities immediately
- Never commit secrets or credentials
- Use GitHub secrets for sensitive data
- Review dependency updates for security issues

## Monitoring and Alerts

### CI Status:
- Check GitHub Actions tab for workflow status
- All checks must pass before merge
- Failed checks will block merge (if branch protection enabled)

### Notifications:
- Configure GitHub notifications for CI failures
- Set up Slack/Discord integrations for team alerts
- Monitor security scan results regularly

## Workflow Customization

### Adding New Checks:
1. Edit relevant workflow file in `.github/workflows/`
2. Add new step in appropriate job
3. Test workflow changes in feature branch
4. Merge to develop after validation

### Modifying Thresholds:
- Adjust coverage thresholds in `backend-ci.yml`
- Update quality gate criteria
- Modify file size limits in `pr-validation.yml`

### Adding New Workflows:
1. Create new `.yml` file in `.github/workflows/`
2. Define triggers and jobs
3. Test thoroughly before merging

## Integration with Project Skills

### Combined Workflow:
1. **Start**: Use `git-workflow` skill for branch creation
2. **Implement**: Use specialized skills (`springboot-backend`, `angular-frontend`)
3. **Validate**: CI/CD workflows automatically validate
4. **Fix**: Address CI failures using skill guidance
5. **Complete**: Use `git-workflow` for PR completion

### Skill References:
- `git-workflow`: Branch management and commit conventions
- `springboot-backend`: Backend development standards
- `angular-frontend`: Frontend development standards
- `docker-orchestration`: Docker configuration standards
- `necform-tickets`: Ticket-specific requirements

## Quick Reference

### CI/CD Commands:
```bash
# Check CI status locally (before push)
cd necform && mvn test
cd necform-front && npm test

# Validate Docker configuration
docker-compose -f compose.yaml config

# Check branch naming
# Must be: feature/NEC-XXX-description

# Check commit format
# Must be: NEC-XXX: description
```

### CI Status Check:
- GitHub Actions tab in repository
- PR check status section
- Workflow run logs for details

### Common Fixes:
```bash
# Fix branch name
git branch -m old-name feature/NEC-XXX-correct-name

# Fix commit message
git commit --amend -m "NEC-XXX: correct message"

# Run tests locally
cd necform && mvn test
cd necform-front && npm test
```

## Important Notes
- All CI checks must pass before merging
- Branch protection rules should enforce CI checks
- Regular security audits are essential
- Monitor CI performance and optimize as needed
- Keep workflow files updated with project changes
- Document any custom workflow modifications
- Train team members on CI/CD processes
- Use CI feedback to improve code quality

## Next Steps
1. **Enable Branch Protection**: Configure branch protection rules in GitHub settings
2. **Add Required Secrets**: Configure SonarQube token if using SonarQube
3. **Test Workflows**: Create test PR to validate all workflows
4. **Monitor Results**: Review initial CI runs and adjust as needed
5. **Team Training**: Ensure team understands CI/CD process
6. **Iterate**: Continuously improve CI/CD based on project needs