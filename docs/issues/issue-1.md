[Setup] Initial Kotlin Spring Boot Project Structure
**Labels:** `setup`, `phase-1`, `high-priority`

#### 🎯 Goal
Set up the minimal Kotlin Spring Boot project structure with Gradle configuration to get a basic "Hello World" MCP server running.

#### 📋 Tasks
- [ ] Initialize Gradle project with Kotlin DSL
- [ ] Add Spring Boot and core dependencies
- [ ] Create basic application structure
- [ ] Verify project builds and runs

#### 📁 Files to Create
- `build.gradle.kts` - Based on MVP spec configuration
- `settings.gradle.kts`
- `src/main/kotlin/com/example/mcpjcr/McpJcrServerApplication.kt`
- `src/main/resources/application.yml`
- `.gitignore`
- `gradle.properties`

#### 📚 References
- [Spring Initializr](https://start.spring.io/)
- [MVP Spec - Project Setup](/docs/mvp-spec-2025-07-05.md#-project-setup)
- [Kotlin Spring Boot Guide](https://kotlinlang.org/docs/jvm-get-started-spring-boot.html)

#### 🔧 Implementation Details
```kotlin
// build.gradle.kts starter
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    id("org.springframework.boot") version "3.5.3"
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
```

#### ✅ Acceptance Criteria
- Project builds with `./gradlew build`
- Application starts with `./gradlew bootRun`
- Basic health endpoint responds at http://localhost:8080/actuator/health