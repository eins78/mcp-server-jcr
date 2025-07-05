[Tools] Tool Registration and Configuration
**Labels:** `tools`, `phase-1`, `medium-priority`
**Depends on:** #4, #5, #6, #7, #8

#### 🎯 Goal
Wire all tools together with Spring configuration.

#### 📋 Tasks
- [ ] Create ToolConfig class
- [ ] Register all tools as beans
- [ ] Configure ToolCallbackProvider
- [ ] Add tool enable/disable flags
- [ ] Verify all tools are discoverable

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/config/ToolConfig.kt`
- `src/test/kotlin/com/example/mcpjcr/config/ToolConfigTest.kt`

#### 📚 References
- [MVP Spec - Tool Registration](/docs/mvp-spec-2025-07-05.md#mvp-tool-registration-read-only)

#### ✅ Acceptance Criteria
- All tools registered and available
- Tools can be enabled/disabled via config
- MCP server lists all available tools
- Integration test verifies tool discovery