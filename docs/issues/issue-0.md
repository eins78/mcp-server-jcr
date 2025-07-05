[Setup] Local JCR Repository with Docker Compose
**Labels:** `setup`, `phase-1`, `high-priority`
**Delivers:** Working JCR repository for development

### 🎯 Value Delivered
A running JCR repository that developers can connect to and explore, providing the foundation for all subsequent work.

### 📋 Tasks
- [ ] Create docker-compose.yml with Apache Jackrabbit
- [ ] Add sample content initialization script
- [ ] Create README with setup instructions
- [ ] Verify repository accessible at http://localhost:8080/repository

### 📁 Files to Create
- `docker-compose.yml`
- `docker/jackrabbit/repository.xml`
- `docker/jackrabbit/sample-content.xml`
- `docs/local-jcr-setup.md`

### 🧪 How to Test
```bash
# Start repository
docker-compose up -d

# Verify it's running
curl http://localhost:8080/repository/default

# Should return repository information
```

### ✅ Demo
"Look, we have a JCR repository running with sample content we can query!"