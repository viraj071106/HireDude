# Project Tools

## 1. Purpose

Project Tools provide structured information about the current software project.

They help the Coding Agent understand the project before modifying it.

## 2. Tools

### project_info

Returns general project information.

Example:

```json
{
  "name": "MyApp",
  "path": "/projects/MyApp",
  "language": "Kotlin",
  "buildSystem": "Gradle"
}
```

### dependency_info

Returns project dependencies.

Example:

```json
{
  "dependencies": [
    "kotlinx-coroutines",
    "ktor-client"
  ]
}
```

### project_structure

Returns the relevant project directory structure.

### check_dependency

Checks whether a package, library, or plugin is declared and installed in the project.

```json
{
  "name": "ktor-client-core"
}
```

Example result:

```json
{
  "declared": true,
  "version": "3.x",
  "source": "gradle/libs.versions.toml"
}
```

The model uses this before relying on a library. If `declared` is false, the model must not use it and must ask the user ([[📄 Dependency Rules]]).

### check_imports

Checks the imports of a source file against the real project. Runs automatically after every file write and can also be called directly.

```json
{
  "path": "src/ui/Home.tsx"
}
```

Example result:

```json
{
  "unresolved": ["./components/Fancy"],
  "selfImport": false,
  "cycles": [],
  "undeclaredPackages": ["lodash"]
}
```

It reports:

- Imports that do not resolve to a project file, a declared dependency, or the standard library
- A file importing itself
- New circular imports
- Gradle module cycles and modules depending on themselves

Any non-empty problem list is sent back to the model as an error ([[📄 Verification Gates]]).

## 3. Flow

```text
AI Model
   |
   v
Tool Calling
   |
   v
Project Tool
   |
   v
Project
   |
   v
Tool Result
   |
   v
AI Model
```

## 4. Use Cases

Project tools can help the agent:

- Identify the project type
- Identify modules
- Inspect dependencies
- Understand project structure
- Select appropriate build commands

## 5. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 Context Management|Context Management]]
- [[📄 Dependency Rules]]
- [[📄 Verification Gates]]
