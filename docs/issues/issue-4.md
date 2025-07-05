[UI] Basic Status Web Page
**Labels:** `ui`, `phase-1`, `medium-priority`
**Depends on:** #2
**Delivers:** Visual server status page

### 🎯 Value Delivered
A web page showing server status, JCR connection, and available tools.

### 📋 Tasks
- [ ] Add Spring Web dependencies
- [ ] Create simple HTML template
- [ ] Add status REST endpoint
- [ ] Display connection and tool info
- [ ] Add auto-refresh

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/controller/WebController.kt`
- `src/main/resources/templates/index.html`
- `src/main/resources/static/css/simple.css`

### 🧪 How to Test
```bash
# Open browser to:
http://localhost:8181/

# Should see:
# - Server status: Running
# - JCR connection: Connected
# - Available tools: query
```

### ✅ Demo
"Open your browser and see the server status in real-time!"