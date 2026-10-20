# Context Management

## Purpose

Context Management controls what information is given to the AI model for each task.

The entire project should not be sent to the model every time.

Only relevant information should be selected.

## Sources of Context

The agent can collect information from:

- User request
- Project files
- Search results
- Build errors
- Test results
- Git diff
- Project configuration
- Dependency configuration
- Obsidian knowledge
- Previous conversation

## Context Flow

User Request
↓
Context Manager
↓
Find Relevant Information
↓
Remove Unnecessary Information
↓
Build Context
↓
AI Model

## Example

User:

"Fix the navigation error in MarketPlace.kt."

The agent should not send the entire project.

Instead:

User Request
+
MarketPlace.kt
+
Relevant navigation code
+
Relevant error
+
Required project configuration
+
Relevant project rules
↓
AI Model

## Context Priority

The agent should prioritize:

1. Current user request
2. Actual project files
3. Compiler/build errors
4. Test results
5. Current Git state
6. Project configuration
7. Relevant project rules
8. Previous conversation
9. General documentation

## Source of Truth

Actual project files and tool results are more reliable than AI assumptions or outdated documentation.

For example:

If an Obsidian note says:

"Kotlin version = X"

but the actual project configuration says:

"Kotlin version = Y"

the actual project configuration should be used.

## Token Management

The agent should monitor:

- Input tokens
- Output tokens
- Total tokens
- Context size
- Tool output size

Large or irrelevant information should be removed when possible.

## Context Compression

If the conversation becomes too large, the agent can:

- Summarize older conversation
- Remove irrelevant tool results
- Keep important decisions
- Keep current task information
- Keep important errors

## Context Security

Do not unnecessarily send:

- Passwords
- API keys
- Access tokens
- Private credentials
- Unrelated personal information

## Dependency Grounding

For every code task, the agent reads the real dependency list from the project manifests and adds it to the context as `ALLOWED DEPENDENCIES`.

Sources:

- `package.json`
- `build.gradle(.kts)` and `gradle/libs.versions.toml`
- `settings.gradle(.kts)` for module names

This stops the model from guessing which libraries and versions exist. If a library is not on the list, the model must ask before using it ([[📄 Dependency Rules]]).

Also include:

- The first relevant compiler or build error, quoted exactly
- The exact file being changed
- A short list of existing similar files, so the model copies real patterns instead of inventing new ones

See [[📄 Anti-Hallucination Rules]].

## Core Principle

Give the AI model enough information to solve the problem, but do not give it unnecessary information.