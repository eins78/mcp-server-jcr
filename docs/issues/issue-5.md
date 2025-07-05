[Tools] Fetch Tool Implementation
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