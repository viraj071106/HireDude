# Project Rules

## 1. Purpose

Rules the Coding Agent injects into the model context when they apply to the task.

**Source of truth:** if a rule here conflicts with the real project files or build configuration, the project wins. See [[📄 Context Management|Context Management]].

Edit this note freely. The agent should load only the sections relevant to the current task.

## 2. Global Rules

- Preserve existing behavior exactly unless the user asks for a specific change
- Return complete files when writing, not partial diffs
- Follow the naming, formatting, and structure already used in the project
- Do not add new libraries or change versions unless asked
- Do not remove code the task did not mention
- Never hard-code secrets, keys, tokens, or passwords
- Prefer small, reviewable changes over large rewrites
- After any change: build, then test, then review `git_diff`

## 3. Kotlin

- Follow the official Kotlin style
- Avoid `!!`; use safe calls, `?:`, or explicit checks
- Prefer `val` over `var`
- Use data classes for plain data
- Use sealed classes/interfaces for state and results
- No blocking calls on the main thread
- Keep functions short and single-purpose

## 4. Jetpack Compose / Compose Multiplatform

- Composables take state and callbacks as parameters (state hoisting)
- Keep business logic in the ViewModel, not in composables
- Use `remember` and `rememberSaveable` correctly; do not create state in a loop
- Give lists stable `key`s
- Pass a `Modifier` parameter and apply it to the root element
- Keep previews free of real network or database calls

## 5. Kotlin Multiplatform

- Put shared logic in `commonMain`
- Use `expect` / `actual` only when platform code is truly needed
- Do not use Android-only APIs in `commonMain`
- Keep shared models, repositories, and ViewModels platform-independent

## 6. Networking and Serialization

- Use Ktor Client for HTTP
- Use `kotlinx.serialization` with `@Serializable` models
- Handle errors explicitly (network failure, timeout, non-2xx, bad JSON)
- Use coroutines and `Flow`; do not block threads
- Base URLs and public keys come from configuration, not from source code

## 7. Gradle and Build

- Always run the build after code changes
- Fix the first compiler error first; later errors are often caused by it
- Do not edit version catalogs or Gradle files unless the task requires it
- Report the exact failing task and the first relevant error line

## 8. Git

- Check `git_status` and `git_diff` before committing
- Commit messages are short and in the imperative mood ("Fix navigation crash")
- Never commit `.env`, `local.properties`, keystores, or build output
- Never force-push
- Commit and push only when the user asks, and only after approval

## 9. Web Projects

Follow [[📄 Web Development Rules]] for HTML, CSS, JavaScript/TypeScript, React, and Node.js. Key points:

- Use the framework and tools already in the project
- No secrets in client code; browser-exposed env vars are public
- Validate input on the server
- Build with `run_npm_script`, never run long-lived dev servers

## 10. Facts, Imports, and Dependencies

Follow [[📄 Anti-Hallucination Rules]] and [[📄 Dependency Rules]]. Key points:

- Project facts come from tools, not memory
- Do not add or change dependencies without user approval
- No self-imports, circular imports, or module cycles
- Dependencies point from higher layers to lower layers only

## 11. Per-Project Rules (fill in)

Copy this block for each project:

```text
Project name:
Path:
Modules:
Min / target SDK:
Kotlin / Compose versions: (read from the project, do not trust this note)
Architecture:
Special rules:
Files the agent must never edit:
```

## 12. Relationships

- [[📄 System Prompt]]
- [[📄 Web Development Rules]]
- [[📄 Anti-Hallucination Rules]]
- [[📄 Dependency Rules]]
- [[📄 Verification Gates]]
- [[📄 Task Playbooks]]
- [[📄 Context Management|Context Management]]
- [[📄 Git Tools]]
- [[📄 Build Tools]]
