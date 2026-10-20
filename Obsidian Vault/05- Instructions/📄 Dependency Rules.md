# Dependency Rules

## 1. Purpose

Stop the model from adding unnecessary, invented, or dangerous dependencies, and from creating dependency loops.

## 2. Golden Rules

1. **Do not add dependencies.** The model never adds, removes, or upgrades a package, library, plugin, or module on its own
2. If a task seems to need a new dependency, the model must stop and ask the user, naming the package and why
3. Prefer, in this order:
   1. The language or platform standard library
   2. A dependency already in the project
   3. A small amount of new code in the project
   4. A new dependency (only with user approval)
4. Never invent a package name, plugin ID, or version. Package names must come from the user or from the project's own files
5. Never change versions in the version catalog, `build.gradle(.kts)`, `package.json`, or lockfiles unless the user asked for exactly that

## 3. Self-Dependency and Loops

The model must never create:

- A file that imports itself
- A circular import (A imports B, B imports A, or longer cycles)
- A Gradle module that depends on itself or creates a module cycle
- A `package.json` that lists its own package as a dependency
- A shared module that depends on the app module (dependencies point **downward** only)
- A function or class whose only purpose is to wrap itself

Expected direction of dependencies:

```text
App / UI
   |
   v
Features
   |
   v
Domain / Business Logic
   |
   v
Data / Networking
   |
   v
Core / Utilities
```

Lower layers never import upper layers. In KMP, `commonMain` never depends on Android-only or iOS-only code.

## 4. Allowed Dependencies List

The agent builds this list from the real project files and puts it in the model context:

```text
ALLOWED DEPENDENCIES (read from the project, do not add others)
- kotlinx-coroutines 1.x
- ktor-client-core 3.x
- ...
```

The model may only use libraries on this list or the standard library. Source files:

| Ecosystem | Files to read |
|---|---|
| Gradle / Kotlin | `build.gradle(.kts)`, `settings.gradle(.kts)`, `gradle/libs.versions.toml` |
| Node / Web | `package.json`, lockfile |
| Python (if used) | `requirements.txt`, `pyproject.toml` |

## 5. Dependency Change Flow

If a new dependency is truly needed, the user decides:

```text
Model asks: "This needs <package>. Reason: <reason>. Alternatives: <alternatives>"
        |
        v
Agent shows the request to the user (DEPENDENCY permission)
        |
        +---- Rejected --> Model must solve it without the package
        |
        +---- Approved --> User or agent edits the manifest, installs, builds, and checks
```

Before approving, the user should check the package: it exists, is maintained, has a compatible license, and is not a typosquat of a popular package.

## 6. Checks the Agent Runs

After every file write, [[📄 Verification Gates]] checks:

- Every import resolves to a project file, a declared dependency, or the standard library
- No file imports itself
- No new import cycle was introduced
- No manifest or lockfile changed unless approved

## 7. Relationships

- [[📄 System Prompt]]
- [[📄 Anti-Hallucination Rules]]
- [[📄 Verification Gates]]
- [[📄 Tool Permissions]]
- [[📄 Project Tools]]
- [[📄 Context Management|Context Management]]
