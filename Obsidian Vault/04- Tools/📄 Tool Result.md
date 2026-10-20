# Tool Result

## 1. Purpose

Tool Result defines the standard response returned after a tool executes.

A common result format makes it easier for the Coding Agent and AI Model to interpret tool output.

## 2. Basic Structure

```json
{
  "success": true,
  "tool": "git_diff",
  "output": "diff contents"
}
```

## 3. Failed Result

```json
{
  "success": false,
  "tool": "run_gradle",
  "output": "",
  "error": {
    "code": "COMMAND_FAILED",
    "message": "Gradle task failed"
  }
}
```

## 4. Suggested Fields

```text
success
tool
output
error
metadata
duration
```

Not every tool must populate every field.

## 5. Result Flow

```text
Tool Execution
      |
      v
Tool Result
      |
      v
Coding Agent
      |
      v
Context Management
      |
      v
AI Model
```

## 6. Core Principle

Tool results should contain actual execution information.

The model should not have to guess whether an operation succeeded.
