#!/bin/bash

# Script to create revised GitHub issues for MCP-JCR Server Phase 1
# Usage: ./create-github-issues-revised.sh

echo "Creating GitHub issues for MCP-JCR Server..."
echo "This script will create issues in the current repository"
echo ""

# Check if gh is authenticated
if ! gh auth status >/dev/null 2>&1; then
    echo "Error: GitHub CLI not authenticated. Run 'gh auth login' first."
    exit 1
fi

# Get repository info
REPO=$(gh repo view --json nameWithOwner -q .nameWithOwner)
echo "Creating issues in repository: $REPO"
echo ""

# Function to create issue
create_issue() {
    local number=$1
    local title=$2
    local labels=$3
    
    echo "Creating Issue: $title"
    gh issue create \
        --title "$title" \
        --body-file "docs/issues/issue-$number.md" \
        --label "$labels"
}

# Create all issues in order
create_issue 0 "[Setup] Local JCR Repository with Docker Compose" "setup,phase-1,high-priority"
create_issue 1 "[Setup] Minimal Spring Boot Kotlin Project" "setup,phase-1,high-priority"
create_issue 2 "[Core] JCR Connection Service" "core,phase-1,high-priority"
create_issue 3 "[Core] First Working MCP Tool - Query" "core,tools,phase-1,high-priority"
create_issue 4 "[UI] Basic Status Web Page" "ui,phase-1,medium-priority"
create_issue 5 "[Tools] Fetch Tool Implementation" "tools,phase-1,high-priority"
create_issue 6 "[Quality] Code Formatting with Ktlint" "quality,phase-1,medium-priority"
create_issue 7 "[Tools] ListChildren Tool" "tools,phase-1,high-priority"
create_issue 8 "[Testing] Integration Test Infrastructure" "testing,phase-1,high-priority"
create_issue 9 "[Tools] Remaining Read-Only Tools" "tools,phase-1,medium-priority"
create_issue 10 "[UI] Enhanced Web Interface" "ui,phase-1,low-priority"
create_issue 11 "[Release] Docker Image and Distribution" "release,phase-1,medium-priority"

echo ""
echo "All issues created! Check them at: https://github.com/$REPO/issues"
echo ""
echo "Next steps:"
echo "1. Create a project board: gh project create --title 'MCP-JCR MVP Phase 1'"
echo "2. Add issues to the project board"
echo "3. Start with Issue #1 (Local JCR Repository Setup)"