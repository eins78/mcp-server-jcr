#!/bin/bash
# Script to initialize JCR repository with sample content

echo "Waiting for Jackrabbit to be ready..."
until curl -f http://localhost:8080/repository/default > /dev/null 2>&1; do
    echo "Waiting for repository to start..."
    sleep 5
done

echo "Repository is ready. Initializing sample content..."

# Note: In a production setup, you would use WebDAV or the JCR API to import content
# For this MVP, we're documenting the manual process

echo "Sample content structure is available at: docker/jackrabbit/sample-content/content.xml"
echo "To import content, you can use the Jackrabbit web interface or JCR tools."
echo ""
echo "Repository is running at: http://localhost:8080"
echo "WebDAV endpoint: http://localhost:8080/repository/default"
echo ""
echo "Default credentials:"
echo "  Username: admin"
echo "  Password: admin"