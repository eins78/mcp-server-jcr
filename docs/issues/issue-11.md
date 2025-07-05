[Quality] Code Quality and Testing Setup
**Labels:** `quality`, `phase-1`, `medium-priority`
**Depends on:** #1

#### 🎯 Goal
Set up code quality tools and testing infrastructure.

#### 📋 Tasks
- [ ] Configure Detekt with rules
- [ ] Configure Ktlint
- [ ] Set up Kover for coverage
- [ ] Create test fixtures
- [ ] Add pre-commit hooks

#### 📁 Files to Create
- `config/detekt/detekt.yml`
- `.editorconfig`
- `src/test/kotlin/com/example/mcpjcr/TestFixtures.kt`

#### 📚 References
- [Detekt Documentation](https://detekt.dev/)
- [Ktlint Documentation](https://ktlint.github.io/)
- [Kover Documentation](https://kotlin.github.io/kotlinx-kover/)

#### ✅ Acceptance Criteria
- `./gradlew detekt` runs successfully
- `./gradlew ktlintCheck` passes
- Coverage reports generated
- Pre-commit hooks work