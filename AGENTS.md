# AGENTS.md

This file contains development rules and guidelines for AI agents working on this MCP-JCR Kotlin server project.

**Important**: Also review `docs/project-management.md` for issue workflow, tracking issues, and continuous value delivery approach.

## Project Overview

This is an MCP (Model Context Protocol) server implementation for JCR (Java Content Repository) access, built with Kotlin and Spring Boot. The server enables LLM agents to interact with JCR repositories through standardized MCP tools.

## Technology Stack

- **Language**: Kotlin 2.0+
- **Framework**: Spring Boot 3.5+ with WebFlux (Reactive)
- **Build Tool**: Gradle 8.10+ (Kotlin DSL)
- **Key Dependencies**: 
  - `spring-ai-starter-mcp-server-webflux` (MCP protocol - Spring AI 1.0.0-M7+)
  - Apache Jackrabbit 2.23+ (JCR implementation)
  - JCR API 2.0
  - Kotlin Coroutines & Reactor integration
- **Testing**: JUnit 5, MockK, Spring Boot Test, Kover (coverage)
- **Code Quality**: Detekt, Ktlint
- **Development Environment**: VS Code with devcontainer (requires Kotlin/JVM setup)

## Code Style and Conventions

### File Naming
- Use PascalCase for Kotlin class files: `JcrService.kt`, `QueryTool.kt`
- Use lowercase with hyphens for configuration files: `application-dev.yml`
- Test files should end with `Test.kt`: `JcrServiceTest.kt`

### Package Structure
```
com.example.mcpjcr/
├── McpJcrServerApplication.kt    # Main application class
├── config/                       # Spring configuration classes
├── service/                      # Business logic and services
├── tools/                        # MCP tool implementations
├── model/                        # Data classes and DTOs
└── exception/                    # Custom exceptions
```

### Kotlin Conventions
- Use `data class` for DTOs and model objects
- Prefer immutability: use `val` over `var` unless mutability is required
- Use Kotlin's null safety features: avoid `!!` operator
- Leverage extension functions for utility methods
- Use `sealed class` for representing finite state sets
- Apply `@JvmRecord` for interop when appropriate
- Use `coroutineScope` and `suspend` functions for async operations
- Prefer `buildJsonObject` and `buildJsonArray` for JSON construction
- Use Kotlin DSL features in Gradle configuration

### Spring Boot Patterns
- Use constructor injection (no `@Autowired` needed in Kotlin)
- Leverage `@ConfigurationProperties` with data classes
- Use `@ConditionalOnProperty` for feature toggles
- Apply proper transaction boundaries with `@Transactional`

## Architecture Guidelines

### Component Responsibilities

1. **Application Layer** (`McpJcrServerApplication.kt`)
   - Spring Boot application entry point
   - Global configuration scanning

2. **Service Layer** (`service/`)
   - `JcrService`: Manages JCR repository connections and sessions
   - Business logic implementation
   - Transaction management

3. **Tool Layer** (`tools/`)
   - Individual MCP tool implementations
   - Each tool is a Spring `@Component`
   - Tools use `JcrService` for repository access

4. **Configuration Layer** (`config/`)
   - Spring configuration classes
   - Bean definitions
   - Conditional tool registration based on readonly mode

### Resource Management
- Always use Kotlin's `use` function for auto-closeable resources:
  ```kotlin
  session.use { 
    // work with session
  } // automatically closed
  ```
- Never leak JCR sessions or repository connections

### Error Handling
- Use Kotlin's `Result` type for operations that may fail
- Throw specific exceptions with meaningful messages
- Handle JCR exceptions appropriately
- Use Spring's `@ExceptionHandler` for REST endpoints

## Testing Requirements

### Unit Tests
- Test coverage should be > 80%
- Use MockK for mocking dependencies
- Follow the AAA pattern: Arrange, Act, Assert
- Test file location mirrors source: `src/test/kotlin/.../JcrServiceTest.kt`

### Integration Tests
- Use `@SpringBootTest` for full context tests
- Embed Jackrabbit repository for testing
- Test readonly mode enforcement
- Verify MCP protocol compliance

### Test Naming
```kotlin
@Test
fun `should return node data when path exists`() { }

@Test
fun `should throw exception when node not found`() { }
```

## Development Workflow

### Devcontainer Maintenance
- **IMPORTANT**: Before implementing features that require new tools, check if the devcontainer needs updating
- If new tools are needed (e.g., databases, language runtimes, CLI tools), update `.devcontainer/Dockerfile` first
- Remind the user to rebuild the devcontainer when changes are made
- Keep the devcontainer minimal - only add tools specifically needed for this project
- Current tools included:
  - Docker and Docker Compose (for running services)
  - OpenJDK 21 (for Spring Boot)
  - Gradle 8.10 (for build automation)

### One Issue, One Pull Request Rule
- **Goal**: Each GitHub issue should be solved by a single cohesive pull request
- **Issue Sizing**: If an issue feels too large for one PR, split it into sub-issues
- **Cohesive Changes**: A PR should contain all code needed to complete the issue's value delivery
- **Atomic Completion**: The PR should make the issue's demo scenario work end-to-end
- **Quality Gate**: If you need multiple PRs, consider if the issue should be split instead

### Branch Strategy
- Create feature branch from main: `git checkout -b feature/issue-N-short-description`
- Work on single issue per branch
- Create PR when issue is complete and tested
- Merge to main after review

## Build and Development Commands

```bash
# Build project
./gradlew build

# Run tests
./gradlew test

# Run specific test
./gradlew test --tests "com.example.mcpjcr.service.JcrServiceTest"

# Run with hot reload
./gradlew bootRun --continuous

# Generate test coverage report
./gradlew test koverHtmlReport

# Check code style
./gradlew ktlintCheck

# Format code
./gradlew ktlintFormat

# Run static analysis
./gradlew detekt

# Build Docker image
./gradlew bootBuildImage --imageName=mcp-jcr-server:latest

# Clean build
./gradlew clean build

# All quality checks
./gradlew check
```

## Readonly Mode Implementation

The server supports a readonly mode controlled by configuration:

```yaml
mcp:
  readonly: true  # or false
```

### Implementation Requirements
1. Conditional bean registration:
   ```kotlin
   @Bean
   @ConditionalOnProperty(name = ["mcp.readonly"], havingValue = "false")
   fun updateNodeTool(jcrService: JcrService) = UpdateNodeTool(jcrService)
   ```

2. Runtime checks in tools:
   ```kotlin
   if (readonlyMode) {
       throw UnsupportedOperationException("Operation not allowed in readonly mode")
   }
   ```

## Security Considerations

1. **Input Validation**
   - Validate all JCR paths and queries
   - Sanitize user inputs to prevent injection
   - Use parameterized queries where possible

2. **Access Control**
   - Implement proper authentication (consider Basic Auth)
   - Use Spring Security for endpoint protection
   - Validate permissions before JCR operations

3. **Resource Limits**
   - Implement query result limits
   - Add timeouts for long-running operations
   - Monitor and limit resource consumption

## Performance Guidelines

1. **JCR Session Management**
   - Use session pooling for better performance
   - Keep sessions short-lived
   - Avoid session-per-request anti-pattern

2. **Query Optimization**
   - Use JCR-SQL2 efficiently
   - Implement pagination for large results
   - Cache frequently accessed data

3. **Async Operations**
   - Leverage Spring WebFlux's reactive model with Kotlin coroutines
   - Use `suspend` functions and `Flow` for streaming data
   - Implement proper backpressure handling with `buffer` and `conflate`
   - Use `coroutineScope` for structured concurrency
   - Apply `withContext(Dispatchers.IO)` for blocking JCR operations

## Documentation Requirements

1. **KDoc Comments**
   ```kotlin
   /**
    * Executes a JCR-SQL2 query and returns matching node paths.
    *
    * @param query The JCR-SQL2 query string
    * @param limit Maximum number of results (default: 100)
    * @return List of node paths matching the query
    * @throws InvalidQueryException if the query syntax is invalid
    */
   ```

2. **README Updates**
   - Keep README.md updated with setup instructions
   - Document all environment variables
   - Include troubleshooting section

3. **API Documentation**
   - Document all MCP tools with examples
   - Include request/response formats
   - Provide integration examples

## Deployment Checklist

- [ ] All tests passing
- [ ] Code style checks passing
- [ ] Security scan completed
- [ ] Performance testing done
- [ ] Documentation updated
- [ ] Docker image built and tested
- [ ] Environment variables documented
- [ ] Readonly mode tested
- [ ] Integration tests with MCP client passing