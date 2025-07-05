# Docker Configuration

This directory contains Docker-related configuration files for the MCP-JCR Server.

## Structure

```
docker/
└── jackrabbit/
    ├── repository.xml       # Jackrabbit repository configuration
    ├── sample-content/      # Sample content for testing
    │   └── content.xml     # JCR content structure
    └── init-repository.sh  # Initialization script
```

## Files

### repository.xml
Configures the Apache Jackrabbit repository with:
- Derby database for persistence
- Lucene search indexing with highlighting
- File-based data store for binaries
- Default security configuration

### sample-content/content.xml
Contains sample JCR content structure including:
- Documents (policies, reports)
- Products (electronics, books)
- Blog posts

This provides realistic test data for developing and testing MCP tools.

### init-repository.sh
Helper script that:
- Waits for repository to be ready
- Provides connection information
- Documents how to import sample content

## Usage

See `/docs/local-jcr-setup.md` for complete setup instructions.