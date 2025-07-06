#!/usr/bin/env node

const fs = require('fs');
const path = require('path');

// Read JSON input from stdin
let inputData = '';
process.stdin.setEncoding('utf8');

process.stdin.on('data', (chunk) => {
  inputData += chunk;
});

process.stdin.on('end', () => {
  try {
    const hookData = JSON.parse(inputData);
    const transcriptPath = hookData.transcript_path;
    
    if (!transcriptPath) {
      console.error('No transcript path provided');
      process.exit(1);
    }

    // Read the transcript file
    const transcriptContent = fs.readFileSync(transcriptPath, 'utf8');
    const transcriptLines = transcriptContent.trim().split('\n');
    
    // Find the latest user message
    let latestUserMessage = null;
    
    for (let i = transcriptLines.length - 1; i >= 0; i--) {
      try {
        const line = JSON.parse(transcriptLines[i]);
        if (line.type === 'user') {
          latestUserMessage = line.content;
          break;
        }
      } catch (e) {
        // Skip invalid JSON lines
        continue;
      }
    }
    
    if (!latestUserMessage) {
      console.log('No user message found in transcript');
      process.exit(0);
    }
    
    // Create timestamp with milliseconds and timezone
    const timestamp = new Date().toISOString();
    
    // Format the log entry
    const logEntry = `\n<details>\n<summary>${timestamp}</summary>\n\n${latestUserMessage}\n\n</details>\n`;
    
    // Ensure docs directory exists
    const docsDir = path.join(process.cwd(), 'docs');
    if (!fs.existsSync(docsDir)) {
      fs.mkdirSync(docsDir, { recursive: true });
    }
    
    // Append to docs/prompts.md
    const promptsFile = path.join(docsDir, 'prompts.md');
    
    // Create the file with header if it doesn't exist
    if (!fs.existsSync(promptsFile)) {
      fs.writeFileSync(promptsFile, '# Claude Code Prompts\n\nThis file contains a log of all prompts submitted to Claude Code.\n');
    }
    
    // Append the log entry
    fs.appendFileSync(promptsFile, logEntry);
    
    console.log('Prompt logged successfully');
    
  } catch (error) {
    console.error('Error processing hook:', error.message);
    process.exit(1);
  }
}); 
