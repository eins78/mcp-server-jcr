[Core] JCR Connection Service
**Labels:** `core`, `phase-1`, `high-priority`
**Depends on:** #0, #1
**Delivers:** Service that connects to JCR and can verify connection

### 🎯 Value Delivered
Ability to connect to JCR repository and verify the connection works.

### 📋 Tasks
- [ ] Add Jackrabbit dependencies
- [ ] Create JcrService interface and implementation
- [ ] Add connection configuration
- [ ] Create connection health indicator
- [ ] Add connection test endpoint

### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/service/JcrService.kt`
- `src/main/kotlin/com/example/mcpjcr/service/impl/JcrServiceImpl.kt`
- `src/main/kotlin/com/example/mcpjcr/config/JcrConfig.kt`
- `src/main/kotlin/com/example/mcpjcr/health/JcrHealthIndicator.kt`
- `src/main/kotlin/com/example/mcpjcr/controller/TestController.kt`

### 🧪 How to Test
```bash
# With JCR running and app started:
curl http://localhost:8181/actuator/health
# Returns: {"status":"UP","components":{"jcr":{"status":"UP","details":{"repository":"jackrabbit","workspace":"default"}}}}

curl http://localhost:8181/test/jcr-connection
# Returns: {"connected":true,"nodeCount":5}
```

### ✅ Demo
"The server is connected to JCR and can count nodes!"