# MCP-JCR Server Project Board Setup

## GitHub Project Configuration

### Project Name: MCP-JCR Server MVP Phase 1

### Board Type: Kanban

### Columns:
1. **📋 Backlog** - New issues, not ready to work on
2. **🔜 Ready** - Dependencies met, ready to start
3. **🚧 In Progress** - Actively being worked on (limit: 2)
4. **👀 Review** - PR submitted, awaiting review
5. **✅ Done** - Merged and deployed

### Labels:
- `setup` - Project setup and configuration
- `core` - Core functionality (MCP, JCR)
- `tools` - MCP tool implementations
- `ui` - User interface
- `quality` - Testing and code quality
- `ci` - Continuous integration
- `phase-1` - MVP Phase 1
- `high-priority` - Must have for MVP
- `medium-priority` - Should have
- `low-priority` - Nice to have

### Milestones:
1. **MVP Phase 1** - Local read-only JCR access
   - Due date: 4 weeks from start
   - Description: Basic working MCP server with 5 read-only tools

### Automation Rules:
- When PR is opened → Move to "Review"
- When PR is merged → Move to "Done"
- When issue is assigned → Move to "In Progress"

## Implementation Order

### Week 1: Foundation
1. Initial project setup (#1)
2. Spring AI MCP configuration (#2)
3. JCR Service implementation (#3)
4. Code quality setup (#11)

### Week 2: Core Tools
1. Query Tool (#4)
2. Fetch Tool (#5)
3. ListChildren Tool (#6)
4. SearchByFullText Tool (#7)
5. ExportTree Tool (#8)

### Week 3: Integration
1. Tool Registration (#9)
2. Web UI (#10)
3. GitHub Actions (#12)
4. Integration testing

### Week 4: Polish
1. Documentation
2. Bug fixes
3. Performance optimization
4. Release preparation

## Definition of Done
- [ ] Code compiles without warnings
- [ ] Unit tests pass with >80% coverage
- [ ] Integration tests pass
- [ ] Code passes Detekt and Ktlint checks
- [ ] PR reviewed and approved
- [ ] Documentation updated
- [ ] Merged to main branch

## Quick Start Commands

```bash
# Create all issues at once (after setting up gh cli)
gh issue create --title "[Setup] Initial Kotlin Spring Boot Project Structure" --body-file docs/issues/issue-1.md --label "setup,phase-1,high-priority"

# View project board
gh project list
gh project view <project-number>

# Move issue to column
gh project item-edit --project <project-number> --id <item-id> --field Status --value "In Progress"
```