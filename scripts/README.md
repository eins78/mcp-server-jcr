# Claude Code Hook for Prompt Logging

This directory contains a Claude Code hook that automatically logs all user prompts to `docs/prompts.md`.

## Setup

The hook is configured in `.claude/settings.json` and consists of:

1. **Hook Script**: `log-prompt.js` - Node.js script that parses the Claude Code transcript and extracts the latest user prompt
2. **Hook Configuration**: `.claude/settings.json` - Configures the hook to run on the "Stop" event

## How It Works

1. When you finish a prompt in Claude Code CLI, the "Stop" event is triggered
2. The hook script receives the transcript path via stdin
3. It parses the transcript JSON to find the latest user message
4. It appends the prompt to `docs/prompts.md` with an ISO timestamp

## Output Format

Each prompt is logged in the following format:

```html
<details>
<summary>2024-01-15T10:30:45.123Z</summary>

Your prompt text here

</details>
```

## Files

- `log-prompt.js` - The main hook script
- `../.claude/settings.json` - Claude Code hook configuration

## Testing

To test the hook manually, you can simulate the input:

```bash
echo '{"transcript_path": "/path/to/transcript.jsonl"}' | node log-prompt.js
```

## Requirements

- Node.js (available in the devcontainer)
- Claude Code CLI
- The hook runs in the devcontainer environment at `/workspace` 
