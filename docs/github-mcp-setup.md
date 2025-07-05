# GitHub MCP Server Setup for Claude Code

This guide walks you through setting up the official GitHub MCP server for enhanced GitHub integration in Claude Code.

## Prerequisites

- Claude Code CLI installed
- Active GitHub account

## Authentication

The GitHub MCP server supports two authentication methods:

### Option 1: OAuth Authentication (Recommended)
- Automatic authentication flow
- No manual token setup required
- Works with Claude Code's built-in authentication

### Option 2: Personal Access Token (Manual)
If OAuth doesn't work, you can use a manual token:

1. **Create GitHub Personal Access Token**
   - Go to https://github.com/settings/personal-access-tokens/new
   - Grant only the permissions you're comfortable with for AI tools
   - Common permissions: `repo`, `issues`, `pull_requests`

2. **Configure with token** (if needed)
   ```bash
   export GITHUB_TOKEN=your_token_here
   ```

## Adding the MCP Server

Add the official GitHub MCP server to Claude Code:

```bash
claude mcp add --transport http github https://api.githubcopilot.com/mcp/
```

## Verification

Check if the server was added successfully:

```bash
claude mcp list
```

You should see `github` in the list of configured servers.

## Available Capabilities

Once configured, the GitHub MCP server provides these toolsets:

- **Issues**: Create, update, search, assign issues
- **Pull Requests**: Review, merge, comment on PRs
- **Repositories**: Manage repo settings, branches
- **Actions**: Monitor CI/CD workflows
- **Discussions**: Community engagement
- **Code Security**: Vulnerability scanning

## Example Usage

After setup, you can use GitHub tools in Claude Code sessions:

- `/mcp__github__list_repos` - List your repositories
- `/mcp__github__create_issue` - Create new issues
- `/mcp__github__list_prs` - List pull requests
- `@github:issue://123` - Analyze specific GitHub issues

## Management Commands

- **List servers**: `claude mcp list`
- **Get server details**: `claude mcp get github`
- **Remove server**: `claude mcp remove github`

## Troubleshooting

- Ensure your GitHub token has the required permissions
- Check that the token is properly set in your environment
- Use `--mcp-debug` flag when running Claude Code to debug connection issues

## Benefits

- **Context awareness**: AI understands issue relationships
- **Natural language**: "Close all stale issues labeled 'bug'"
- **Cross-repository analysis**: Compare issues across projects
- **Automated workflows**: Smart project board management