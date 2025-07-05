# GitHub Issues for MCP-JCR Server Phase 1

## Project Setup

### Issue #1: [Setup] Initial Kotlin Spring Boot Project Structure
**Labels:** `setup`, `phase-1`, `high-priority`

#### 🎯 Goal
Set up the minimal Kotlin Spring Boot project structure with Gradle configuration to get a basic "Hello World" MCP server running.

#### 📋 Tasks
- [ ] Initialize Gradle project with Kotlin DSL
- [ ] Add Spring Boot and core dependencies
- [ ] Create basic application structure
- [ ] Verify project builds and runs

#### 📁 Files to Create
- `build.gradle.kts` - Based on MVP spec configuration
- `settings.gradle.kts`
- `src/main/kotlin/com/example/mcpjcr/McpJcrServerApplication.kt`
- `src/main/resources/application.yml`
- `.gitignore`
- `gradle.properties`

#### 📚 References
- [Spring Initializr](https://start.spring.io/)
- [MVP Spec - Project Setup](/docs/mvp-spec-2025-07-05.md#-project-setup)
- [Kotlin Spring Boot Guide](https://kotlinlang.org/docs/jvm-get-started-spring-boot.html)

#### 🔧 Implementation Details
```kotlin
// build.gradle.kts starter
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    id("org.springframework.boot") version "3.5.3"
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
```

#### ✅ Acceptance Criteria
- Project builds with `./gradlew build`
- Application starts with `./gradlew bootRun`
- Basic health endpoint responds at http://localhost:8080/actuator/health

---

### Issue #2: [Core] Spring AI MCP Server Configuration
**Labels:** `core`, `phase-1`, `high-priority`
**Depends on:** #1

#### 🎯 Goal
Configure Spring AI MCP server with WebFlux for handling MCP protocol requests.

#### 📋 Tasks
- [ ] Add Spring AI MCP dependencies
- [ ] Configure MCP server properties
- [ ] Create basic MCP server configuration class
- [ ] Verify MCP endpoint is accessible

#### 📁 Files to Create/Modify
- `build.gradle.kts` - Add Spring AI dependencies
- `src/main/resources/application.yml` - MCP configuration
- `src/main/kotlin/com/example/mcpjcr/config/McpServerConfig.kt`

#### 📚 References
- [Spring AI MCP Documentation](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html)
- [MVP Spec - Application Configuration](/docs/mvp-spec-2025-07-05.md#application-configuration)
- [MCP Protocol Overview](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html)

#### 🔧 Implementation Details
```yaml
# application.yml
spring:
  ai:
    mcp:
      server:
        name: jcr-server
        version: 1.0.0
        type: ASYNC
        sse-message-endpoint: /mcp/messages
        capabilities:
          tool: true
          resource: false
```

#### ✅ Acceptance Criteria
- MCP server endpoint accessible at `/mcp/messages`
- Server capabilities properly advertised
- Can connect with MCP client for testing

---

### Issue #3: [Core] JCR Service Implementation
**Labels:** `core`, `phase-1`, `high-priority`
**Depends on:** #1

#### 🎯 Goal
Implement JcrService for managing JCR repository connections with session pooling.

#### 📋 Tasks
- [ ] Add Jackrabbit dependencies
- [ ] Create JcrService interface
- [ ] Implement session management with coroutines
- [ ] Add connection configuration
- [ ] Create unit tests with MockK

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/service/JcrService.kt`
- `src/main/kotlin/com/example/mcpjcr/service/impl/JcrServiceImpl.kt`
- `src/main/kotlin/com/example/mcpjcr/config/JcrConfig.kt`
- `src/main/kotlin/com/example/mcpjcr/model/JcrProperties.kt`
- `src/test/kotlin/com/example/mcpjcr/service/JcrServiceTest.kt`

#### 📚 References
- [Apache Jackrabbit Documentation](https://jackrabbit.apache.org/jcr/first-hops.html)
- [MVP Spec - Architecture](/docs/mvp-spec-2025-07-05.md#️-architecture-overview)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)

#### 🔧 Implementation Details
```kotlin
interface JcrService {
    suspend fun <T> executeInSession(block: suspend (Session) -> T): T
    suspend fun isConnected(): Boolean
    fun getRepository(name: String = "default"): Repository
}
```

#### ✅ Acceptance Criteria
- Can connect to local JCR repository
- Session management works with coroutines
- Proper error handling for connection failures
- Unit tests pass with 80% coverage

---

### Issue #4: [Tools] Query Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement the Query tool for executing JCR-SQL2 queries.

#### 📋 Tasks
- [ ] Create McpTool interface
- [ ] Implement QueryTool class
- [ ] Add query parameter validation
- [ ] Implement result pagination
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/McpTool.kt`
- `src/main/kotlin/com/example/mcpjcr/tools/QueryTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/QueryToolTest.kt`

#### 📚 References
- [JCR-SQL2 Query Syntax](https://jackrabbit.apache.org/oak/docs/query/query-engine.html)
- [MVP Spec - Query Tool Example](/docs/mvp-spec-2025-07-05.md#example-read-only-tool-implementation)

#### 🔧 Implementation Details
See MVP spec for full implementation example.

#### ✅ Acceptance Criteria
- Executes JCR-SQL2 queries successfully
- Respects limit parameter
- Handles invalid queries gracefully
- Returns structured JSON response
- Integration tests pass

---

### Issue #5: [Tools] Fetch Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement the Fetch tool for retrieving node data by path.

#### 📋 Tasks
- [ ] Implement FetchTool class
- [ ] Handle node properties and metadata
- [ ] Support multi-valued properties
- [ ] Add path validation
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/FetchTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/FetchToolTest.kt`

#### 📚 References
- [JCR Node API](https://docs.adobe.com/docs/en/spec/jsr170/javadocs/jcr-2.0/javax/jcr/Node.html)
- [MVP Spec - Tools List](/docs/mvp-spec-2025-07-05.md#-mcp-tools)

#### ✅ Acceptance Criteria
- Fetches node by path correctly
- Returns all properties with types
- Handles non-existent paths with 404
- Supports special characters in paths

---

### Issue #6: [Tools] ListChildren Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement the ListChildren tool for listing child nodes.

#### 📋 Tasks
- [ ] Implement ListChildrenTool class
- [ ] Add depth limiting
- [ ] Support node type filtering
- [ ] Handle large child lists
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/ListChildrenTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/ListChildrenToolTest.kt`

#### ✅ Acceptance Criteria
- Lists immediate children correctly
- Respects max depth parameter
- Filters by node type if specified
- Handles nodes without children

---

### Issue #7: [Tools] SearchByFullText Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement full-text search functionality.

#### 📋 Tasks
- [ ] Implement SearchByFullTextTool class
- [ ] Configure full-text indexing
- [ ] Handle search within paths
- [ ] Add result ranking
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/SearchByFullTextTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/SearchByFullTextToolTest.kt`

#### 📚 References
- [JCR Full-Text Search](https://jackrabbit.apache.org/oak/docs/query/fulltext.html)

#### ✅ Acceptance Criteria
- Finds nodes containing search terms
- Searches within specified paths
- Handles special characters
- Returns relevant results

---

### Issue #8: [Tools] ExportTree Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement tree export functionality for analysis.

#### 📋 Tasks
- [ ] Implement ExportTreeTool class
- [ ] Add recursive tree traversal
- [ ] Implement depth limiting
- [ ] Handle large trees efficiently
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/ExportTreeTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/ExportTreeToolTest.kt`

#### ✅ Acceptance Criteria
- Exports complete subtree as JSON
- Respects max depth parameter
- Handles large trees without OOM
- Excludes system properties if configured

---

### Issue #9: [Tools] Tool Registration and Configuration
**Labels:** `tools`, `phase-1`, `medium-priority`
**Depends on:** #4, #5, #6, #7, #8

#### 🎯 Goal
Wire all tools together with Spring configuration.

#### 📋 Tasks
- [ ] Create ToolConfig class
- [ ] Register all tools as beans
- [ ] Configure ToolCallbackProvider
- [ ] Add tool enable/disable flags
- [ ] Verify all tools are discoverable

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/config/ToolConfig.kt`
- `src/test/kotlin/com/example/mcpjcr/config/ToolConfigTest.kt`

#### 📚 References
- [MVP Spec - Tool Registration](/docs/mvp-spec-2025-07-05.md#mvp-tool-registration-read-only)

#### ✅ Acceptance Criteria
- All tools registered and available
- Tools can be enabled/disabled via config
- MCP server lists all available tools
- Integration test verifies tool discovery

---

### Issue #10: [UI] Basic Web UI for Server Management
**Labels:** `ui`, `phase-1`, `medium-priority`
**Depends on:** #1, #2

#### 🎯 Goal
Create simple web UI for server status and configuration.

#### 📋 Tasks
- [ ] Add Spring MVC dependencies
- [ ] Create index.html template
- [ ] Implement WebUIController
- [ ] Add status REST endpoint
- [ ] Create basic CSS styling

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/controller/WebUIController.kt`
- `src/main/resources/templates/index.html`
- `src/main/resources/static/css/style.css`
- `src/main/resources/static/js/app.js`

#### 📚 References
- [Spring Boot Web Guide](https://spring.io/guides/gs/serving-web-content/)
- [MVP Spec - Web UI](/docs/mvp-spec-2025-07-05.md#1-spring-boot-web-ui)

#### ✅ Acceptance Criteria
- Web UI accessible at http://localhost:8181/
- Shows server status and connection info
- Can configure JCR connection
- Responsive design for desktop

---

### Issue #11: [Quality] Code Quality and Testing Setup
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

---

### Issue #12: [CI] GitHub Actions for PR Checks
**Labels:** `ci`, `phase-1`, `low-priority`
**Depends on:** #11

#### 🎯 Goal
Set up automated PR checks with GitHub Actions.

#### 📋 Tasks
- [ ] Create PR check workflow
- [ ] Add test execution
- [ ] Add code quality checks
- [ ] Configure Codecov integration
- [ ] Set up branch protection

#### 📁 Files to Create
- `.github/workflows/pr-check.yml`
- `codecov.yml`

#### 📚 References
- [MVP Spec - GitHub Actions](/docs/mvp-spec-2025-07-05.md#-github-actions-cicd)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)

#### ✅ Acceptance Criteria
- PR checks run automatically
- Tests must pass for merge
- Code quality checks enforced
- Coverage reported to Codecov

---

## Kanban Board Setup

### Columns:
1. **Backlog** - All issues start here
2. **Ready** - Issues with all dependencies met
3. **In Progress** - Currently being worked on
4. **Review** - Code complete, in PR review
5. **Done** - Merged to main

### Initial Board State:
- **Ready**: Issue #1 (Initial Setup)
- **Backlog**: All other issues

### Work Order:
1. Start with Issue #1 (Initial Setup)
2. Then parallel work on #2 (MCP Config) and #3 (JCR Service)
3. Once both complete, work on tools (#4-#8) in parallel
4. Complete with #9 (Tool Registration)
5. UI and quality issues can be worked on in parallel with tools