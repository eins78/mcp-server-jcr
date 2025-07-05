# Revised GitHub Issues for MCP-JCR Server Phase 1

## Continuous Value Delivery Approach

Each issue delivers a testable, demonstrable feature. The order ensures we always have a working system.

---

## Issue #0: [Setup] Local JCR Repository with Docker Compose
**Labels:** `setup`, `phase-1`, `high-priority`
**Delivers:** Working JCR repository for development

### 🎯 Value Delivered
A running JCR repository that developers can connect to and explore, providing the foundation for all subsequent work.

### 📋 Tasks
- [ ] Create docker-compose.yml with Apache Jackrabbit
- [ ] Add sample content initialization script
- [ ] Create README with setup instructions
- [ ] Verify repository accessible at http://localhost:8080/repository

### 📁 Files to Create
- `docker-compose.yml`
- `docker/jackrabbit/repository.xml`
- `docker/jackrabbit/sample-content.xml`
- `docs/local-jcr-setup.md`

### 🧪 How to Test
```bash
# Start repository
docker-compose up -d

# Verify it's running
curl http://localhost:8080/repository/default

# Should return repository information
```

### ✅ Demo
"Look, we have a JCR repository running with sample content we can query!"

---

## Issue #1: [Setup] Minimal Spring Boot Kotlin Project
**Labels:** `setup`, `phase-1`, `high-priority`
**Delivers:** Running Spring Boot application with health endpoint

### 🎯 Value Delivered
A working Kotlin Spring Boot application that starts and responds to health checks.

### 📋 Tasks
- [ ] Initialize Gradle project with Kotlin DSL
- [ ] Add minimal Spring Boot dependencies
- [ ] Create main application class
- [ ] Add health endpoint
- [ ] Verify application starts

### 📁 Files to Create
- `build.gradle.kts` (minimal version)
- `settings.gradle.kts`
- `src/main/kotlin/com/example/mcpjcr/McpJcrServerApplication.kt`
- `src/main/resources/application.yml` (with just port config)

### 🧪 How to Test
```bash
./gradlew bootRun
# In another terminal:
curl http://localhost:8181/actuator/health
# Returns: {"status":"UP"}
```

### ✅ Demo
"The server is running and healthy!"

---

## Issue #2: [Core] JCR Connection Service
**Labels:** `core`, `phase-1`, `high-priority`
**Depends on:** #0, #1
**Delivers:** Service that connects to JCR and can verify connection

### 🎯 Value Delivered
Ability to connect to JCR repository and verify the connection works.

### 📋 Tasks
- [ ] Add Jackrabbit dependencies
- [ ] Create JcrService interface and implementation
- [ ] Add connection configuration
- [ ] Create connection health indicator
- [ ] Add connection test endpoint

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/service/JcrService.kt`
- `src/main/kotlin/com/example/mcpjcr/service/impl/JcrServiceImpl.kt`
- `src/main/kotlin/com/example/mcpjcr/config/JcrConfig.kt`
- `src/main/kotlin/com/example/mcpjcr/health/JcrHealthIndicator.kt`
- `src/main/kotlin/com/example/mcpjcr/controller/TestController.kt`

### 🧪 How to Test
```bash
# With JCR running and app started:
curl http://localhost:8181/actuator/health
# Returns: {"status":"UP","components":{"jcr":{"status":"UP","details":{"repository":"jackrabbit","workspace":"default"}}}}

curl http://localhost:8181/test/jcr-connection
# Returns: {"connected":true,"nodeCount":5}
```

### ✅ Demo
"The server is connected to JCR and can count nodes!"

---

## Issue #3: [Core] First Working MCP Tool - Query
**Labels:** `core`, `tools`, `phase-1`, `high-priority`
**Depends on:** #1, #2
**Delivers:** First tool accessible via MCP protocol

### 🎯 Value Delivered
A working MCP server with one tool that can execute JCR queries - the first actual AI-usable feature!

### 📋 Tasks
- [ ] Add Spring AI MCP dependencies
- [ ] Configure MCP server
- [ ] Create McpTool interface
- [ ] Implement QueryTool
- [ ] Register tool with MCP
- [ ] Add MCP test client script

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/McpTool.kt`
- `src/main/kotlin/com/example/mcpjcr/tools/QueryTool.kt`
- `src/main/kotlin/com/example/mcpjcr/config/McpConfig.kt`
- `scripts/test-mcp-query.sh`
- Update `build.gradle.kts` with MCP dependencies

### 🧪 How to Test
```bash
# List available tools
curl http://localhost:8181/mcp/tools

# Execute query via MCP
./scripts/test-mcp-query.sh "SELECT * FROM [nt:base] WHERE [jcr:path] = '/'"
# Returns nodes in JSON format
```

### ✅ Demo
"Claude can now query our JCR repository using natural language!"

---

## Issue #4: [UI] Basic Status Web Page
**Labels:** `ui`, `phase-1`, `medium-priority`
**Depends on:** #2
**Delivers:** Visual server status page

### 🎯 Value Delivered
A web page showing server status, JCR connection, and available tools.

### 📋 Tasks
- [ ] Add Spring Web dependencies
- [ ] Create simple HTML template
- [ ] Add status REST endpoint
- [ ] Display connection and tool info
- [ ] Add auto-refresh

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/controller/WebController.kt`
- `src/main/resources/templates/index.html`
- `src/main/resources/static/css/simple.css`

### 🧪 How to Test
```bash
# Open browser to:
http://localhost:8181/

# Should see:
# - Server status: Running
# - JCR connection: Connected
# - Available tools: query
```

### ✅ Demo
"Open your browser and see the server status in real-time!"

---

## Issue #5: [Tools] Fetch Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #3
**Delivers:** Second MCP tool for fetching node data

### 🎯 Value Delivered
Ability to fetch and inspect individual JCR nodes with all their properties.

### 📋 Tasks
- [ ] Implement FetchTool class
- [ ] Add to tool registry
- [ ] Update status page with new tool
- [ ] Add integration test
- [ ] Update test script

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/FetchTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/FetchToolTest.kt`
- Update `scripts/test-mcp-tools.sh`

### 🧪 How to Test
```bash
./scripts/test-mcp-tools.sh fetch --path "/"
# Returns root node properties

# Via Claude:
# "Show me the properties of the root node"
```

### ✅ Demo
"Now we can inspect any node in the repository!"

---

## Issue #6: [Quality] Code Formatting with Ktlint
**Labels:** `quality`, `phase-1`, `medium-priority`
**Depends on:** #1
**Delivers:** Consistent code formatting

### 🎯 Value Delivered
Automated code formatting to maintain consistency across the project.

### 📋 Tasks
- [ ] Add Ktlint plugin to Gradle
- [ ] Create .editorconfig
- [ ] Format existing code
- [ ] Add pre-commit hook
- [ ] Document in README

### 📁 Files to Create
- `.editorconfig`
- `.git/hooks/pre-commit`
- Update `build.gradle.kts`

### 🧪 How to Test
```bash
# Check formatting
./gradlew ktlintCheck

# Auto-format
./gradlew ktlintFormat

# Commit should trigger formatting check
```

### ✅ Demo
"All code is now consistently formatted!"

---

## Issue #7: [Tools] ListChildren Tool
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #3
**Delivers:** Browse JCR tree structure

### 🎯 Value Delivered
Ability to explore repository structure by listing child nodes.

### 🧪 How to Test
```bash
./scripts/test-mcp-tools.sh listChildren --path "/" --maxDepth 2
# Returns tree structure
```

---

## Issue #8: [Testing] Integration Test Infrastructure
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

---

## Issue #9: [Tools] Remaining Read-Only Tools
**Labels:** `tools`, `phase-1`, `medium-priority`
**Depends on:** #8
**Delivers:** Complete set of read-only tools

### 🎯 Value Delivered
Full toolkit for exploring and analyzing JCR content.

### 📋 Tasks
- [ ] Implement SearchByFullText tool
- [ ] Implement ExportTree tool
- [ ] Add integration tests
- [ ] Update documentation

---

## Issue #10: [UI] Enhanced Web Interface
**Labels:** `ui`, `phase-1`, `low-priority`  
**Depends on:** #4, #9
**Delivers:** Interactive tool testing interface

### 🎯 Value Delivered
Web-based tool testing without needing curl or scripts.

### 📋 Tasks
- [ ] Add tool execution forms
- [ ] Display results nicely
- [ ] Add query history
- [ ] Improve styling

---

## Issue #11: [Release] Docker Image and Distribution
**Labels:** `release`, `phase-1`, `medium-priority`
**Depends on:** #9
**Delivers:** Easy deployment option

### 🎯 Value Delivered
One-command deployment of MCP-JCR server.

### 📋 Tasks
- [ ] Create Dockerfile
- [ ] Add to docker-compose
- [ ] Create release script
- [ ] Document deployment

### 🧪 How to Test
```bash
docker-compose up mcp-jcr-server
# Server runs in container
```

---

## Revised Implementation Order for Continuous Value

### Week 1: Foundation with Immediate Value
1. **#0**: JCR Repository Setup (Day 1)
2. **#1**: Minimal Spring Boot (Day 1-2)
3. **#2**: JCR Connection (Day 2-3)
4. **#3**: First MCP Tool (Day 3-4)
5. **#4**: Status Web Page (Day 4-5)

**End of Week 1**: Working MCP server with one tool and web UI!

### Week 2: Expand Functionality
1. **#5**: Fetch Tool (Day 1)
2. **#6**: Code Quality (Day 2)
3. **#7**: ListChildren Tool (Day 3)
4. **#8**: Test Infrastructure (Day 4-5)

**End of Week 2**: Multiple tools with automated testing!

### Week 3: Complete Feature Set
1. **#9**: Remaining Tools (Day 1-3)
2. **#10**: Enhanced UI (Day 4-5)

**End of Week 3**: Full feature set with polished UI!

### Week 4: Production Ready
1. **#11**: Docker Distribution (Day 1-2)
2. Bug fixes and documentation (Day 3-4)
3. Release preparation (Day 5)

## Key Improvements

1. **Every issue delivers value** - No pure setup/configuration tickets
2. **Testable at each step** - Clear testing instructions and demos
3. **Shorter dependency chains** - Most issues depend on only 1-2 others
4. **Earlier user-facing features** - Web UI comes in week 1
5. **Incremental tool delivery** - Don't wait for all tools to test MCP
6. **Clear demo scenarios** - Stakeholders can see progress continuously