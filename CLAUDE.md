# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Quick Reference

- **Project Type**: MCP-JCR Kotlin Server (Spring Boot WebFlux)
- **Development Rules**: See `AGENTS.md` for comprehensive development guidelines
- **MVP Specification**: `/workspace/docs/mvp-spec-2025-07-05.md`

## Claude Code Specific Instructions

### When Starting Work
1. Review `AGENTS.md` for development rules and conventions
2. Check the MVP specification for implementation requirements
3. Note: The devcontainer is currently configured for Node.js but the project requires Kotlin/Java setup

### Essential Commands
```bash
# Quick commands (see AGENTS.md "Build and Development Commands" for full list)
./gradlew build          # Build project
./gradlew test           # Run tests
./gradlew bootRun        # Run locally
./gradlew ktlintCheck    # Check code style
```

### Key Architecture Points
- Follow package structure in `AGENTS.md` under "Package Structure"
- Use Kotlin's `use` blocks for JCR sessions (see `AGENTS.md` "Resource Management")
- Implement readonly mode as per `AGENTS.md` "Readonly Mode Implementation"

### Project Not Yet Initialized
The Kotlin project structure needs to be created. Use Spring Initializr with:
- Language: Kotlin
- Dependencies: `spring-ai-starter-mcp-server-webflux`, `jackrabbit-core`, `jackrabbit-jcr2dav`
- See full setup in MVP specification