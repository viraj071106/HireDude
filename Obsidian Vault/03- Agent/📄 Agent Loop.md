## Purpose

The Agent Loop defines how the Coding Agent handles a user request from beginning to end.

## Basic Flow

User Request
↓
Understand Request
↓
Gather Context
↓
Ask AI Model
↓
Determine Required Tool
↓
Execute Tool
↓
Receive Tool Result
↓
Send Result Back to AI
↓
Repeat if Necessary
↓
Validate Result
↓
Return Response

## Example

User:

"Fix the navigation error in MarketPlace.kt."

The agent:

1. Understands the request
2. Searches for MarketPlace.kt
3. Reads the relevant code
4. Searches related navigation code
5. Sends relevant context to the AI model
6. Receives a proposed solution
7. Modifies the required file
8. Runs Gradle
9. Checks the result
10. Reviews the Git diff
11. Shows the result to the user

## Important Rule

The agent must not assume that generated code works.

The actual project must be checked using real tools such as:

- Gradle
- Tests
- Git diff
- Compiler output

## Tool Loop

If the AI requires additional information:

AI Model
↓
Tool Request
↓
Agent
↓
Tool Execution
↓
Tool Result
↓
AI Model

The loop continues until the task is complete or the agent needs user input.

## User Approval

The agent should request approval before important operations such as:

- Writing significant changes
- Committing changes
- Pushing to GitHub
- Destructive operations

## Verification Gates

Before returning a response, the agent runs the gates in [[📄 Verification Gates]]:

- Manifest guard (no dependency changes without approval)
- Import check (imports exist, no self-imports, no cycles)
- Lint, build, and type-check
- Tests
- Git diff review

A task is not complete until the required gates pass or the agent reports exactly which could not run. The model must not claim success without a passing tool result.
