[Testing] Integration Test Infrastructure
**Labels:** `testing`, `phase-1`, `high-priority`
**Depends on:** #3
**Delivers:** Automated testing for all tools

### 🎯 Value Delivered
Confidence that tools work correctly with real JCR repository.

### 📋 Tasks
- [ ] Set up embedded Jackrabbit for tests
- [ ] Create test data fixtures
- [ ] Add integration tests for existing tools
- [ ] Create GitHub Actions workflow

### 📁 Files to Create
- `src/test/kotlin/com/example/mcpjcr/IntegrationTestBase.kt`
- `src/test/resources/test-repository.xml`
- `.github/workflows/test.yml`

### 🧪 How to Test
```bash
./gradlew test --tests "*IntegrationTest"
# All tests pass
```