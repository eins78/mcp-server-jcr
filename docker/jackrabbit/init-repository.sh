#!/bin/bash
# Script to initialize JCR repository with sample content

echo "Waiting for Jackrabbit to be ready..."
until curl -f http://localhost:8080/repository/default > /dev/null 2>&1; do
    echo "Waiting for repository to start..."
    sleep 5
done

echo "Repository is ready. Initializing sample content..."

# Uploading sample content using WebDAV
echo "Uploading sample content..."
curl -u admin:admin -T /opt/jackrabbit/sample-content/content.xml http://localhost:8080/repository/default/sample-content.xml

if [ $? -eq 0 ]; then
    echo "✅ Sample content uploaded successfully."
else
    echo "❌ Failed to upload sample content. Please check the repository and try again."
fi

echo ""
echo "Repository is running at: http://localhost:8080"
echo "WebDAV endpoint: http://localhost:8080/repository/default"