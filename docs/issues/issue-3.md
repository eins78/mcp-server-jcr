[Core] First Working MCP Tool - Query
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