[Tools] SearchByFullText Tool Implementation
**Labels:** `tools`, `phase-1`, `high-priority`
**Depends on:** #2, #3

#### 🎯 Goal
Implement full-text search functionality.

#### 📋 Tasks
- [ ] Implement SearchByFullTextTool class
- [ ] Configure full-text indexing
- [ ] Handle search within paths
- [ ] Add result ranking
- [ ] Create integration tests

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/tools/SearchByFullTextTool.kt`
- `src/test/kotlin/com/example/mcpjcr/tools/SearchByFullTextToolTest.kt`

#### 📚 References
- [JCR Full-Text Search](https://jackrabbit.apache.org/oak/docs/query/fulltext.html)

#### ✅ Acceptance Criteria
- Finds nodes containing search terms
- Searches within specified paths
- Handles special characters
- Returns relevant results