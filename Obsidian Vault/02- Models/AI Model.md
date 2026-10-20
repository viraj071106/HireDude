# AI Model

## 1. Purpose

The AI Model is the reasoning component of the Local AI Coding Assistant.

It receives the user request and relevant context and determines the next action.

The model may generate a final response or request a tool through the Coding Agent.

## 2. Position

```text
Coding Agent
     |
     v
Ollama
     |
     v
AI Model
     |
     +---- Final Response
     |
     +---- Tool Request
```

## 3. Responsibilities

- Understand user requests
- Analyze project context
- Reason about code
- Generate code
- Identify possible causes of errors
- Request tools when information is missing
- Interpret tool results
- Decide whether more work is required
- Produce a final response

## 4. What the Model Does Not Do

The model does not directly:

- Read files
- Write files
- Run Gradle
- Run tests
- Execute shell commands
- Run Git commands
- Modify the operating system

Those operations happen through tools controlled by the Coding Agent.

## 5. Model Input

```text
System Instructions
       +
User Request
       +
Relevant Project Context
       +
Relevant Memory
       +
Available Tool Definitions
       +
Previous Tool Results
       |
       v
AI Model
```

## 6. Tool Requests

Example:

```json
{
  "tool": "read_file",
  "arguments": {
    "path": "src/Main.kt"
  }
}
```

The Coding Agent validates this request before execution.

## 7. Tool Results

Example:

```json
{
  "success": true,
  "tool": "git_diff",
  "output": "diff contents"
}
```

The result becomes new context for the next model call.

## 8. Context

[[📄 Context Management|Context Management]] determines what information is relevant enough to send to the model.

## 9. Model Selection

[[📄Model Selection|Model Selection]] determines which local model should be used for a task.

## 10. Ollama

[[📄Ollama|Ollama]] provides the local model runtime.

## 11. Agent Loop

The model is repeatedly called as part of the [[📄 Agent Loop|Agent Loop]].

```text
Request
  ↓
Context
  ↓
AI Model
  ↓
Tool Request
  ↓
Tool Result
  ↓
AI Model
  ↓
Final Response
```

## 12. Core Principle

The model reasons.

The Coding Agent controls.

The tools execute.

The development environment provides actual results.
