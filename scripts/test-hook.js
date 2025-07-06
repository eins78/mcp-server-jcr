#!/usr/bin/env node

const fs = require('fs');
const path = require('path');
const { spawn } = require('child_process');

// Create a mock transcript file
const mockTranscript = `{"type": "system", "content": "System message"}
{"type": "user", "content": "Hello, this is a test prompt"}
{"type": "assistant", "content": "Assistant response"}
{"type": "user", "content": "This is the latest user message that should be logged"}
{"type": "assistant", "content": "Another assistant response"}`;

const tmpDir = path.join(__dirname, '.tmp');
const transcriptPath = path.join(tmpDir, 'test-transcript.jsonl');

// Ensure temp directory exists
if (!fs.existsSync(tmpDir)) {
  fs.mkdirSync(tmpDir, { recursive: true });
}

// Write mock transcript
fs.writeFileSync(transcriptPath, mockTranscript);

// Create mock hook input
const hookInput = JSON.stringify({
  session_id: "test-session",
  transcript_path: transcriptPath,
  stop_hook_active: false
});

// Run the hook script
const hookProcess = spawn('node', [path.join(__dirname, 'log-prompt.js')], {
  stdio: ['pipe', 'pipe', 'pipe']
});

// Send input to the hook
hookProcess.stdin.write(hookInput);
hookProcess.stdin.end();

// Collect output
let stdout = '';
let stderr = '';

hookProcess.stdout.on('data', (data) => {
  stdout += data.toString();
});

hookProcess.stderr.on('data', (data) => {
  stderr += data.toString();
});

hookProcess.on('close', (code) => {
  console.log(`Hook process exited with code: ${code}`);
  console.log(`Stdout: ${stdout}`);
  
  if (stderr) {
    console.log(`Stderr: ${stderr}`);
  }
  
  // Check if the prompt was logged
  const promptsFile = path.join(process.cwd(), 'docs', 'prompts.md');
  if (fs.existsSync(promptsFile)) {
    console.log('\nPrompts file content:');
    console.log(fs.readFileSync(promptsFile, 'utf8'));
  } else {
    console.log('❌ Prompts file was not created');
  }
  
  // Clean up
  try {
    fs.unlinkSync(transcriptPath);
    fs.rmdirSync(tmpDir);
  } catch (e) {
    // Ignore cleanup errors
  }
}); 
