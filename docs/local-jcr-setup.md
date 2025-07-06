# Local JCR Repository Setup

This guide explains how to run a local Apache Jackrabbit JCR repository for development.

## Prerequisites

- Docker and Docker Compose installed
- Port 8080 available on your machine

## Quick Start

1. **Test the setup:**
   ```bash
   ./scripts/test-jcr-setup.sh
   ```
   This ensures all configuration files are in place.

2. **Start the repository:**
   ```bash
   docker-compose up -d
   ```

3. **Verify it's running:**
   ```bash
   curl http://localhost:8080/repository/default
   ```

   You should see XML output with repository information.

4. **Access the web interface:**
   - Open http://localhost:8080 in your browser
   - Default credentials: `admin` / `admin`

## Repository Structure

The repository comes with sample content organized as follows:

```
/
├── documents/
│   ├── policies/
│   │   ├── privacy-policy
│   │   └── terms-of-service
│   └── reports/
│       ├── annual-report-2024
│       └── quarterly-report-q4
├── products/
│   ├── electronics/
│   │   ├── laptop-x1
│   │   └── smartphone-z2
│   └── books/
│       ├── programming-kotlin
│       └── jcr-essentials
└── blog/
    └── posts/
        ├── welcome-post
        └── kotlin-tips
```

## Accessing the Repository

### WebDAV Access
The repository is accessible via WebDAV at:
```
http://localhost:8080/repository/default
```

You can mount this as a network drive or use WebDAV clients to browse content.

### JCR API Access
For programmatic access, connect to:
- URL: `http://localhost:8080/server`
- Workspace: `default`
- Credentials: `admin` / `admin`

## Managing Content

### Using the Web Interface
1. Navigate to http://localhost:8080
2. Login with admin credentials
3. Use the JCR Explorer to browse and manage content

### Using curl (WebDAV)
```bash
# List root content
curl -u admin:admin http://localhost:8080/repository/default/

# Get a specific node
curl -u admin:admin http://localhost:8080/repository/default/documents/policies/privacy-policy
```

## Docker Compose Configuration

The `docker-compose.yml` includes:
- Apache Jackrabbit 2.23 (latest stable)
- Persistent volume for repository data
- Health check configuration
- Memory limits (512MB)

## Stopping the Repository

```bash
docker-compose down
```

To also remove the persistent data:
```bash
docker-compose down -v
```

## Troubleshooting

### Port 8080 already in use
Change the port mapping in `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Use port 8081 instead
```

### Repository not starting
Check the logs:
```bash
docker-compose logs jackrabbit
```

### Out of memory errors
Increase the memory limit in `docker-compose.yml`:
```yaml
environment:
  - JAVA_OPTS=-Xmx1g  # Increase to 1GB
```

## Next Steps

With the repository running, you can:
1. Connect the Spring Boot application (Issue #3)
2. Implement the JCR service layer (Issue #4)
3. Start building MCP tools to query content