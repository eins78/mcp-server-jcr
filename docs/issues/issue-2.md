[Core] Spring AI MCP Server Configuration
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