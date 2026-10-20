# Verification Gates

## 1. Purpose

Automatic checks that the **Coding Agent** runs. They do not depend on the model being honest or correct.

A task is not complete until the required gates pass. Failures are sent back to the model as real error text so it can fix them.

## 2. Gate Order

```text
Model writes a file
        |
        v
Gate 1: Path and permission check
        |
        v
Gate 2: Manifest guard
        |
        v
Gate 3: Import check (exists, no self-import, no cycles)
        |
        v
Gate 4: Format / lint
        |
        v
Gate 5: Build / type-check
        |
        v
Gate 6: Tests
        |
        v
Gate 7: Diff review
        |
        v
Final response
```

## 3. Gate Details

### Gate 1: Path and Permission

- Path is inside the project root
- Path is not secret or generated (`.env`, `local.properties`, keystores, `build/`, `node_modules/`, `dist/`)
- Permission policy allows the write (see [[📄 Tool Permissions]])

### Gate 2: Manifest Guard

If the change touches a dependency manifest or lockfile, it is blocked unless the user approved it:

- `package.json`, `package-lock.json`, `pnpm-lock.yaml`, `yarn.lock`
- `build.gradle`, `build.gradle.kts`, `settings.gradle(.kts)`, `gradle/libs.versions.toml`
- `requirements.txt`, `pyproject.toml`

### Gate 3: Import Check

Run `check_imports` on every changed file ([[📄 Project Tools]]). Fail if:

- An import cannot be resolved to a project file, a declared dependency, or the standard library
- The file imports itself
- The change creates a circular import
- A Gradle module depends on itself or creates a module cycle

### Gate 4: Format and Lint

- Web: ESLint and the project formatter, if configured
- Kotlin: ktlint or detekt, if configured
- Do not add a linter that the project does not already use

### Gate 5: Build and Type-Check

- Kotlin: `run_gradle` (for example `assembleDebug`)
- Web: `run_npm_script` with `build`, plus `typecheck` for TypeScript projects
- Report the **first** error exactly as printed

### Gate 6: Tests

- Run existing tests with `run_tests` or the `test` script
- If tests do not exist, say so. Do not claim "tests pass"
- Do not delete or weaken a failing test to make it pass

### Gate 7: Diff Review

Run `git_diff` and check:

- Only the requested files changed
- No unrelated deletions or reformatting
- No secrets, keys, tokens, or debug leftovers
- No new dependencies

## 4. Retry Policy

- Each gate failure goes back to the model with the exact error
- Maximum 3 fix attempts per gate
- After 3 failures, stop and report what failed and what was tried
- Never skip a gate to finish faster

## 5. Final Report Must Show

```text
Changed:  files and one-line summaries
Gates:    import check / build / tests -> pass, fail, or not available
Not run:  anything that could not be verified
Check:    what the user must verify manually
```

Anything not run must be listed under "Not run". The model must not imply it passed.

## 6. Relationships

- [[📄 Agent Loop|Agent Loop]]
- [[📄 Anti-Hallucination Rules]]
- [[📄 Dependency Rules]]
- [[📄 Build Tools]]
- [[📄 Project Tools]]
- [[📄 Tool Permissions]]
- [[📄 Tool Result]]
