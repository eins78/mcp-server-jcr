[Setup] Minimal Spring Boot Kotlin Project
**Labels:** `setup`, `phase-1`, `high-priority`
**Delivers:** Running Spring Boot application with health endpoint

### 🎯 Value Delivered
A working Kotlin Spring Boot application that starts and responds to health checks.

### 📋 Tasks
- [ ] Initialize Gradle project with Kotlin DSL
- [ ] Add minimal Spring Boot dependencies
- [ ] Create main application class
- [ ] Add health endpoint
- [ ] Verify application starts

### 📁 Files to Create
- `build.gradle.kts` (minimal version)
- `settings.gradle.kts`
- `src/main/kotlin/com/example/mcpjcr/McpJcrServerApplication.kt`
- `src/main/resources/application.yml` (with just port config)

### 🧪 How to Test
```bash
./gradlew bootRun
# In another terminal:
curl http://localhost:8181/actuator/health
# Returns: {"status":"UP"}
```

### ✅ Demo
"The server is running and healthy!"