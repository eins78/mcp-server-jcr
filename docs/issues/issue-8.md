[Tools] ExportTree Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement tree export functionality for analysis.

#### 📋 Tasks
- [ ] Implement ExportTreeTool class
- [ ] Add recursive tree traversal
- [ ] Implement depth limiting
- [ ] Handle large trees efficiently
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/ExportTreeTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/ExportTreeToolTest.kt`

#### ✅ Acceptance Criteria
- Exports complete subtree as JSON
- Respects max depth parameter
- Handles large trees without OOM
- Excludes system properties if configured