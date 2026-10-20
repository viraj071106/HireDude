# Tool Architecture

## 1. Purpose

The Tool System gives the Coding Agent controlled access to the development environment.

The AI model proposes tool calls. The Tool System validates, authorizes, executes, and reports the results.

## 2. High-Level Architecture

```text
AI Model
    |
    v
Tool Calling
    |
    v
Tool Registry
    |
    v
Argument Validation
    |
    v
Permission Check
    |
    v
Tool Execution
    |
    v
Tool Result
    |
    v
Coding Agent
    |
    v
AI Model
```

## 3. Tool Layers

```text
04 - Tools
 |
 +-- Tool Registry
 |
 +-- Tool Permissions
 |
 +-- Tool Execution
 |
 +-- Tool Result
 |
 +-- File Tools
 +-- Git Tools
 +-- Build Tools
 +-- Project Tools
 +-- Shell Tools
```

## 4. Tool Registry

The [[📄 Tool Registry]] describes available tools.

It stores:

- Tool name
- Description
- Arguments
- Return format
- Permission level
- Availability

## 5. Permission Layer

[[📄 Tool Permissions]] determines whether a requested operation can execute.

```text
Tool Request
    |
    v
Permission Check
    |
    +---- Allowed ------> Execute
    |
    +---- Approval ------> Ask User
    |
    +---- Denied --------> Reject
```

## 6. Execution Layer

[[📄 Tool Execution]] converts an approved tool request into an actual operation.

Examples:

```text
read_file
git_diff
run_gradle
search_files
project_info
execute_command
```

## 7. Result Layer

[[📄 Tool Result]] converts execution output into a consistent structure that can be returned to the Coding Agent.

Example:

```json
{
  "success": true,
  "tool": "git_diff",
  "output": "diff contents"
}
```

## 8. Tool Categories

### File Tools

Read and modify project files.

### Git Tools

Inspect and modify Git state.

### Build Tools

Build and test projects.

### Project Tools

Inspect project structure and configuration.

### Shell Tools

Execute approved command-line operations.

## 9. Core Principle

The model should never receive unrestricted direct access to the operating system.

```text
AI Model
   |
   v
Controlled Tool Interface
   |
   v
Permission System
   |
   v
Execution Layer
   |
   v
Operating System
```

## 10. Relationships

- [[📄 Tool Calling|Tool Calling]]
- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 File Tools|File Tools]]
- [[📄 Git Tools|Git Tools]]
- [[📄 Build Tools]]
- [[📄 Project Tools]]
- [[📄 Shell Tools]]
