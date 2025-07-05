[Tools] Fetch Tool Implementation
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