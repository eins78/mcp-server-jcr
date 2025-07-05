[Tools] ListChildren Tool
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