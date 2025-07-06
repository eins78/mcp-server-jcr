# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Quick Reference

- **Project Type**: MCP-JCR Kotlin Server (Spring Boot WebFlux)
- **Development Rules**: See `AGENTS.md` for comprehensive development guidelines
- **MVP Specification**: `/workspace/docs/mvp-spec-2025-07-05.md`

## Claude Code Specific Instructions

### When Starting Work
1. Review `AGENTS.md` for development rules and conventions
2. Review `docs/project-management.md` for issue workflow and continuous value delivery approach
3. Check the MVP specification for implementation requirements
4. Look for META tracking issues (e.g., #14) to understand current phase goals
5. Check `AGENTS.md` "Current Project Status" section for setup state

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

### Project Status
- Build configuration complete (build.gradle.kts, settings.gradle.kts)
- Docker setup complete for JCR repository
- Source code implementation pending (Issue #3)