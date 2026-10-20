# Tools Overview

## Purpose

Tools give the Coding Agent the ability to interact with the real development environment.

The AI model decides when a tool may be useful.

The Coding Agent validates and executes the tool.

## Tool Categories

### File Tools

Used to work with project files.

Examples:

- List files
- Read file
- Search files
- Create file
- Write file
- Delete file

### Git Tools

Used to inspect and manage the Git repository.

Examples:

- Git status
- Git diff
- Git log
- Git commit
- Git push

### Build Tools

Used to build and test the project.

Examples:

- Run Gradle
- Run tests
- Check build output

### Project Tools

Used to understand the project.

Examples:

- Project information
- Dependency information
- Project structure

## Tool Flow

User
↓
Coding Agent
↓
AI Model
↓
Tool Request
↓
Tool Registry
↓
Tool Execution
↓
Tool Result
↓
AI Model

## Important Rule

The AI model does not directly control the computer.

The Coding Agent executes tools on its behalf.

## Security

Tools should have restricted permissions.

Read operations should generally be safer than write or execute operations.

Sensitive operations such as Git push should require user approval.

## Relationships

- [[📄 Tool Architecture|Tool Architecture]]
- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 File Tools]]
- [[📄 Git Tools]]
- [[📄 Build Tools]]
- [[📄 Project Tools]]
- [[📄 Shell Tools]]
