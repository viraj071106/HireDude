# Memory

## 1. Purpose

Memory lets the Coding Agent keep useful information between messages and between sessions without resending everything to the model.

The model itself remembers nothing. Everything it "remembers" is text the agent puts back into the context.

## 2. Memory Types

| Type | Lifetime | Stored in | Example |
|---|---|---|---|
| Conversation | Current session | Agent memory (RAM) | Recent user and model messages |
| Session summary | Current session | Agent memory | "Fixed navigation bug, build passes" |
| Project memory | Permanent | `.agent/memory.md` in the project | "Uses Ktor client, feature folders" |
| Knowledge | Permanent | This Obsidian vault | Architecture and rules notes |

## 3. Flow

```text
User Request
     |
     v
Context Manager
     |
     +--> Recent conversation
     +--> Session summary
     +--> Relevant project memory
     +--> Relevant vault notes
     |
     v
AI Model
     |
     v
Final Response
     |
     v
Update memory (only if worth keeping)
```

## 4. What to Store

- Decisions the user made ("use Ktor, not Retrofit")
- Project facts that are hard to rediscover
- Recurring errors and the fix that worked
- User preferences about how the agent should behave

## 5. What Never to Store

- Passwords, API keys, tokens, private credentials
- Contents of `.env` or `local.properties`
- Large file contents (store the path instead)
- Raw tool output (store a one-line summary)

## 6. Rules

- Actual project files and tool results always override memory
- Every memory entry has a date; old entries are reviewed before use
- Keep project memory small (target under 2 KB) so it fits in a small context window
- Send only the entries relevant to the current task
- The user can view, edit, and delete memory at any time

## 7. Vault Access

The vault is **read-only** for the agent. The agent may read notes for rules and architecture, but must not edit them. Changes to the vault are made by the user.

## 8. Project Memory Format

```text
# Project Memory

## Decisions
- 2026-10-03: Use Ktor Client for networking

## Facts
- Shared code lives in shared/src/commonMain

## Known Issues
- None
```

## 9. Relationships

- [[📄 Context Management|Context Management]]
- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Agent Loop|Agent Loop]]
- [[📄 System Prompt]]
- [[📄 Project Rules]]
