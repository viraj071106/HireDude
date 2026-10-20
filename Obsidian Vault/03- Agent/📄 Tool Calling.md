# Tool Calling

## Purpose

Tool Calling allows the AI model to request actions from the Coding Agent.

The AI model does not directly execute tools.

The Coding Agent receives the request, validates it, executes the tool, and returns the result to the AI model.

## Basic Flow

AI Model
↓
Tool Request
↓
Coding Agent
↓
Validate Request
↓
Execute Tool
↓
Tool Result
↓
AI Model

## Example

The user asks:

"Find where NavigationBar is used."

The AI may request:

```text
search_files("NavigationBar")
```

The agent runs the search and returns the result, then the AI may request:

```text
read_file("src/ui/AppScaffold.kt")
```

When it has enough information, it replies with a final answer and no tool call.

## Request Format

The agent sends tool definitions to Ollama in the `tools` field of `/api/chat`. Definitions are generated from the [[📄 Tool Registry]].

```json
{
  "model": "qwen2.5-coder:7b",
  "messages": [ ... ],
  "tools": [
    {
      "type": "function",
      "function": {
        "name": "read_file",
        "description": "Read the contents of a project file",
        "parameters": {
          "type": "object",
          "properties": { "path": { "type": "string" } },
          "required": ["path"]
        }
      }
    }
  ],
  "stream": false
}
```

The model's tool request comes back in `message.tool_calls`:

```json
{
  "message": {
    "role": "assistant",
    "tool_calls": [
      { "function": { "name": "read_file", "arguments": { "path": "src/Main.kt" } } }
    ]
  }
}
```

The tool result is sent back as a message with `role: "tool"`.

## Fallback Parser

Small models sometimes write the tool call as plain text instead of using `tool_calls`. Qwen coder models use this text form:

```text
<tool_call>
{"name": "read_file", "arguments": {"path": "src/Main.kt"}}
</tool_call>
```

The agent should:

1. Use `message.tool_calls` when present
2. Otherwise look for `<tool_call>...</tool_call>` in the text and parse the JSON
3. If the JSON is invalid, send back a short error asking for a corrected call (do not execute anything)

## Validation Steps

Before executing, the agent checks:

1. The tool exists in the [[📄 Tool Registry]]
2. The tool is enabled
3. All required arguments are present and have the right types
4. Paths stay inside the project root
5. [[📄 Tool Permissions]] allow it, or the user approves it

If any step fails, the agent returns a structured error ([[📄 Tool Result]]) so the model can correct itself.

## Rules

- One tool call per model reply
- Maximum 15 tool calls per task
- Two identical calls in a row stop the loop and ask the user
- Tool output is truncated before it goes back to the model
- The model never executes anything itself
- Tool arguments come from the user request or earlier tool results; the model must not invent paths or names
- Tools that change dependencies always need user approval

## Relationships

- [[📄 Agent Loop|Agent Loop]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 System Prompt]]
