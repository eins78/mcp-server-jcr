#!/bin/bash

# Script to create GitHub issues for MCP-JCR Server Phase 1
# Usage: ./create-github-issues.sh <owner> <repo>

OWNER=${1:-"your-github-username"}
REPO=${2:-"mcp-jcr-server"}

echo "Creating GitHub issues for $OWNER/$REPO..."
echo "Make sure you're authenticated with: gh auth login"
echo ""

# Function to create issue
create_issue() {
    local number=$1
    local title=$2
    local labels=$3
    
    echo "Creating Issue #$number: $title"
    gh issue create \
        --repo "$OWNER/$REPO" \
        --title "$title" \
        --body-file "docs/issues/issue-$number.md" \
        --label "$labels" \
        --milestone "MVP Phase 1"
}

# Create all issues
create_issue 1 "[Setup] Initial Kotlin Spring Boot Project Structure" "setup,phase-1,high-priority"
create_issue 2 "[Core] Spring AI MCP Server Configuration" "core,phase-1,high-priority"
create_issue 3 "[Core] JCR Service Implementation" "core,phase-1,high-priority"
create_issue 4 "[Tools] Query Tool Implementation" "tools,phase-1,high-priority"
create_issue 5 "[Tools] Fetch Tool Implementation" "tools,phase-1,high-priority"
create_issue 6 "[Tools] ListChildren Tool Implementation" "tools,phase-1,high-priority"
create_issue 7 "[Tools] SearchByFullText Tool Implementation" "tools,phase-1,high-priority"
create_issue 8 "[Tools] ExportTree Tool Implementation" "tools,phase-1,high-priority"
create_issue 9 "[Tools] Tool Registration and Configuration" "tools,phase-1,medium-priority"
create_issue 10 "[UI] Basic Web UI for Server Management" "ui,phase-1,medium-priority"
create_issue 11 "[Quality] Code Quality and Testing Setup" "quality,phase-1,medium-priority"
create_issue 12 "[CI] GitHub Actions for PR Checks" "ci,phase-1,low-priority"

echo ""
echo "All issues created! Check them at: https://github.com/$OWNER/$REPO/issues"