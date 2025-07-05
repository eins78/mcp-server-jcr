[UI] Basic Web UI for Server Management
**Labels:** `ui`, `phase-1`, `medium-priority`
**Depends on:** #1, #2

#### 🎯 Goal
Create simple web UI for server status and configuration.

#### 📋 Tasks
- [ ] Add Spring MVC dependencies
- [ ] Create index.html template
- [ ] Implement WebUIController
- [ ] Add status REST endpoint
- [ ] Create basic CSS styling

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/controller/WebUIController.kt`
- `src/main/resources/templates/index.html`
- `src/main/resources/static/css/style.css`
- `src/main/resources/static/js/app.js`

#### 📚 References
- [Spring Boot Web Guide](https://spring.io/guides/gs/serving-web-content/)
- [MVP Spec - Web UI](/docs/mvp-spec-2025-07-05.md#1-spring-boot-web-ui)

#### ✅ Acceptance Criteria
- Web UI accessible at http://localhost:8181/
- Shows server status and connection info
- Can configure JCR connection
- Responsive design for desktop