# Tool Permissions

## 1. Purpose

Tool Permissions determine whether a requested operation may execute.

They protect the development environment from unsafe or unintended actions.

## 2. Permission Levels

A simple initial model is:

```text
READ
WRITE
EXECUTE
DEPENDENCY
GIT_WRITE
ADMIN
```

The exact levels can evolve during implementation.

### DEPENDENCY

Any change to what the project depends on. This level is **always ASK_USER**, even if the user has allowed other writes.

It applies to:

- Editing `package.json`, lockfiles, `build.gradle(.kts)`, `settings.gradle(.kts)`, `gradle/libs.versions.toml`, `requirements.txt`, `pyproject.toml`
- Running `npm install`, `npm add`, `yarn add`, `pnpm add`, `pip install`, or any command that installs, removes, or upgrades packages
- Adding or changing Gradle plugins or modules

The check is based on the **path or command**, not on which tool the model used. A `write_file` to `package.json` is a DEPENDENCY action. See [[📄 Dependency Rules]].

## 3. Permission Decision

```text
Tool Request
     |
     v
Permission Check
     |
     +---- ALLOW
     |
     +---- ASK_USER
     |
     +---- DENY
```

## 4. Example

Reading a file:

```text
read_file
   ↓
READ
   ↓
ALLOW
```

Writing a source file:

```text
write_file
   ↓
WRITE
   ↓
Policy
   ↓
ALLOW / ASK_USER
```

Executing an arbitrary shell command:

```text
execute_command
   ↓
EXECUTE
   ↓
Policy
   ↓
ASK_USER / DENY
```

## 5. Important Principle

The AI model must not be allowed to bypass permission checks.

```text
AI Model
   |
   v
Tool Request
   |
   v
Permission System
   |
   v
Execution
```

## 6. Future Policy

Permissions may depend on:

- Tool
- Project
- Path
- Command
- User approval
- Current session
- Security policy

## 7. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Execution]]
- [[📄 Tool Calling|Tool Calling]]
