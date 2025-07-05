[Tools] ListChildren Tool Implementation
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