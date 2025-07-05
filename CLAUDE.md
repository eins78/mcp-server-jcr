# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an MCP (Model Context Protocol) server implementation for JCR (Java Content Repository) access, built with Kotlin and Spring Boot. The server enables LLM agents to interact with JCR repositories through standardized MCP tools.

## Technology Stack

- **Language**: Kotlin
- **Framework**: Spring Boot 3.x with WebFlux
- **Build Tool**: Gradle
- **Key Dependencies**: 
  - `spring-ai-starter-mcp-server-webflux` (MCP protocol support)
  - Apache Jackrabbit (JCR implementation)
  - JCR API
- **Development Environment**: VS Code with devcontainer (currently configured for Node.js but needs Kotlin setup)

## Build and Development Commands

Since the project is not yet initialized, here are the expected commands once set up:

```bash
# Initialize project (if not done)
spring init --dependencies=webflux --language=kotlin --build=gradle mcp-jcr-server

# Build
./gradlew build

# Run locally
./gradlew bootRun

# Run tests
./gradlew test

# Package as JAR
./gradlew bootJar

# Docker build
docker build -t mcp-jcr-server .
```

## Architecture

The server follows a Spring Boot WebFlux architecture with MCP protocol support:

```
MCP Client → MCP Server (WebFlux) → JcrService → JCR Repository
                 ↓
           Tool Registry (conditional based on readonly mode)
```

### Key Components

1. **JcrService**: Manages JCR connections and sessions
2. **Tool Registry**: Conditionally registers tools based on readonly mode
3. **MCP Tools**: Individual tool implementations for JCR operations
4. **Configuration**: Spring Boot application.yml with MCP settings

### Readonly Mode

The server supports a readonly mode controlled by `mcp.readonly` configuration:
- When `true`: Only query/read tools are registered
- When `false`: All tools including write operations are available

## Available MCP Tools

### Read-Only Tools (always available)
- `query` - Execute JCR-SQL2 queries
- `fetch` - Get node data by path
- `listChildren` - List child nodes
- `searchByFullText` - Full-text search
- `exportTree` - Export subtree as JSON

### Write Tools (disabled in readonly mode)
- `updateNode` - Update node properties
- `deleteNode` - Delete nodes
- `createNode` - Create nodes
- `runWorkflow` - Trigger JCR workflows

## Project Structure

```
/workspace/
├── src/main/kotlin/          # Kotlin source files (to be created)
│   └── com/example/mcpjcr/
│       ├── MvcJcrServerApplication.kt
│       ├── service/JcrService.kt
│       ├── tools/            # MCP tool implementations
│       └── config/           # Spring configuration
├── src/main/resources/
│   └── application.yml       # Spring Boot configuration
├── build.gradle.kts          # Gradle build configuration
├── Dockerfile                # Docker build configuration
└── docs/mvp-spec-2025-07-05.md  # MVP specification
```

## Implementation Notes

1. The devcontainer needs to be updated to support Kotlin/Java development instead of Node.js
2. Follow the MVP specification in `/workspace/docs/mvp-spec-2025-07-05.md` for implementation details
3. Use Spring Boot's conditional bean registration for readonly mode support
4. Ensure proper resource cleanup with Kotlin's `use` blocks for JCR sessions
5. Integration tests should verify readonly mode enforcement

## Testing Strategy

- Unit tests for individual tools using MockK
- Integration tests with embedded Jackrabbit repository
- End-to-end tests using `McpClient` to verify protocol compliance
- Readonly mode enforcement tests