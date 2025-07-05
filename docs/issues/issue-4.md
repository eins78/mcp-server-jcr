[Tools] Query Tool Implementation
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