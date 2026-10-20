# Build Tools

## 1. Purpose

Build Tools allow the Coding Agent to build and validate the project.

## 2. Tools

### run_gradle

Runs a Gradle task.

Example:

```json
{
  "task": "assembleDebug"
}
```

### run_tests

Runs the project's test suite or a specified test task.

Example:

```json
{
  "task": "test"
}
```

### run_npm_script

Runs one script from the project's `package.json`. Only these script names are allowed:

```text
build
test
lint
typecheck
```

Example:

```json
{
  "script": "build"
}
```

Rules:

- Never run `dev`, `start`, or any script that does not exit
- The script must exist in `package.json`; the agent checks first
- Install commands are not allowed here (they are DEPENDENCY actions)
- Apply a timeout and limit the output size

## 3. Flow

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
Permission Check
   |
   v
Build Tool
   |
   v
Gradle
   |
   v
Tool Result
   |
   v
AI Model
```

## 4. Verification

Build tools are especially useful after code changes.

```text
Modify Code
    |
    v
Build
    |
    +---- Failed --> Analyze Error --> Fix
    |
    +---- Passed --> Tests
                       |
                       +---- Failed --> Analyze
                       |
                       +---- Passed --> Complete
```

## 5. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 Agent Loop|Agent Loop]]
