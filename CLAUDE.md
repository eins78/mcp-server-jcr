# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Quick Reference

- **Project Type**: MCP-JCR Kotlin Server (Spring Boot WebFlux)
- **Development Rules**: See `AGENTS.md` for comprehensive development guidelines
- **MVP Specification**: `/workspace/docs/mvp-spec-2025-07-05.md`

## Claude Code Specific Instructions

### When Starting Work
1. Check `AGENTS.md` for development rules and conventions
2. Review the MVP specification for implementation requirements
3. Note: The devcontainer is currently configured for Node.js but the project requires Kotlin/Java setup

### Important Commands
```bash
# After Kotlin/Gradle setup:
./gradlew build          # Build project
./gradlew test           # Run tests
./gradlew bootRun        # Run locally
./gradlew ktlintCheck    # Check code style
```

### Project Not Yet Initialized
The Kotlin project structure needs to be created. Use Spring Initializr or manually set up the Gradle project with the dependencies listed in the MVP specification.