#!/bin/bash
# Test script to verify JCR repository setup

echo "Testing JCR Repository Setup..."
echo "=============================="
echo ""

# Check if docker-compose.yml exists
if [ ! -f "docker-compose.yml" ]; then
    echo "❌ docker-compose.yml not found!"
    exit 1
fi
echo "✅ docker-compose.yml found"

# Check if repository.xml exists
if [ ! -f "docker/jackrabbit/repository.xml" ]; then
    echo "❌ docker/jackrabbit/repository.xml not found!"
    exit 1
fi
echo "✅ repository.xml found"

# Check if sample content exists
if [ ! -f "docker/jackrabbit/sample-content/content.xml" ]; then
    echo "❌ sample-content/content.xml not found!"
    exit 1
fi
echo "✅ sample-content.xml found"

echo ""
echo "All configuration files are in place!"
echo ""
echo "To start the JCR repository:"
echo "  docker-compose up -d"
echo ""
echo "To verify it's running:"
echo "  curl http://localhost:8080/repository/default"
echo ""
echo "For detailed setup instructions, see: docs/local-jcr-setup.md"