# AGENTS.md

This file contains development rules and guidelines for AI agents working on this MCP-JCR Kotlin server project.

## Project Overview

This is an MCP (Model Context Protocol) server implementation for JCR (Java Content Repository) access, built with Kotlin and Spring Boot. The server enables LLM agents to interact with JCR repositories through standardized MCP tools.

## Technology Stack

- **Language**: Kotlin
- **Framework**: Spring Boot 3.x with WebFlux
- **Build Tool**: Gradle (Kotlin DSL)
- **Key Dependencies**: 
  - `spring-ai-starter-mcp-server-webflux` (MCP protocol support)
  - Apache Jackrabbit (JCR implementation)
  - JCR API
- **Testing**: JUnit 5, MockK, Spring Boot Test
- **Development Environment**: VS Code with devcontainer

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

# Generate test report
./gradlew jacocoTestReport

# Check code style
./gradlew ktlintCheck

# Format code
./gradlew ktlintFormat

# Build Docker image
./gradlew bootBuildImage

# Clean build
./gradlew clean build
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
   - Leverage Spring WebFlux's reactive model
   - Use coroutines for async operations
   - Implement proper backpressure handling

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