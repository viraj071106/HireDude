# Tool Registry

## 1. Purpose

The Tool Registry is the catalog of tools available to the Coding Agent.

It tells the agent and the execution system which tools exist and how they are defined.

## 2. Responsibilities

The registry should:

- Register tools
- Store tool names
- Store descriptions
- Define arguments
- Define return formats
- Define permission levels
- Enable or disable tools
- Find tools by name
- Reject unavailable tools

## 3. Tool Definition

Each tool should contain:

```text
Name
Description
Arguments
Return Format
Permission Level
Enabled State
```

Example:

```json
{
  "name": "read_file",
  "description": "Read the contents of a project file",
  "arguments": {
    "path": "string"
  },
  "returns": {
    "type": "string"
  },
  "permission": "READ",
  "enabled": true
}
```

## 4. Categories

```text
File Tools
Git Tools
Build Tools
Project Tools
Shell Tools
```

## 5. Example Registry

```text
list_files
read_file
search_files
create_file
write_file
delete_file

git_status
git_diff
git_log
git_commit
git_push

run_gradle
run_npm_script
run_tests

project_info
dependency_info
project_structure
check_dependency
check_imports

execute_command
```

## 6. Lookup

```text
Tool Request
     |
     v
Tool Registry
     |
     +---- Found ------> Validate
     |
     +---- Not Found --> Reject
```

## 7. Registry Is Not Execution

The registry describes tools.

It does not execute them.

```text
Tool Registry
     |
     v
Tool Definition
     |
     v
Tool Execution
```

## 8. Relationships

- [[📄 Tool Calling|Tool Calling]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 Tool Architecture|Tool Architecture]]
