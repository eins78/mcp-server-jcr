# > MCP-JCR Server

> **Model Context Protocol server for JCR repository access**  
> Enables AI agents to explore and query content repositories through natural language

[![License: CC0](https://img.shields.io/badge/License-CC0_1.0-lightgrey.svg)](http://creativecommons.org/publicdomain/zero/1.0/)
[![GitHub Issues](https://img.shields.io/github/issues/eins78/mcp-server-jcr)](https://github.com/eins78/mcp-server-jcr/issues)

## What is this?

An MCP server that connects AI agents (like Claude) to JCR (Java Content Repository) systems. Instead of writing complex JCR-SQL2 queries, developers and content architects can explore repository structure and data using natural language.

**Built with:** Kotlin " Spring Boot " WebFlux " Apache Jackrabbit

## =€ Quick Start

Current status: **Phase 1 MVP in development**

=I **[Start here: Issue #14 - MVP Phase 1 Tracking](https://github.com/eins78/mcp-server-jcr/issues/14)**

## =Ú Documentation

- **[MVP Specification](docs/mvp-spec-2025-07-05.md)** - Complete project overview and technical details
- **[Project Management](docs/project-management.md)** - Development approach and continuous value delivery
- **[Development Guidelines](AGENTS.md)** - Kotlin coding standards and best practices
- **[Claude Code Instructions](CLAUDE.md)** - Specific guidance for Claude Code users

## <¯ Current Phase

**MVP Phase 1**: Read-only JCR access with 5 core tools
- Query execution via natural language
- Node fetching and property inspection  
- Tree structure exploration
- Full-text content search
- Tree export for analysis

[’ View all implementation issues](https://github.com/eins78/mcp-server-jcr/issues?q=is%3Aopen+label%3Aphase-1)

## =¡ Use Cases

- **Developers**: Debug JCR issues without writing queries
- **Content Architects**: Understand repository structure through AI
- **CMS Administrators**: Explore content organization patterns

## > Contributing

This project follows a continuous value delivery approach where each issue delivers working, testable functionality. Check the [project management guide](docs/project-management.md) for our development workflow.

## =Ä License

Released under [CC0 1.0 Universal](LICENSE) - Public Domain Dedication