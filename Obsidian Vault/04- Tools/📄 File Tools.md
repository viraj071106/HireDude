# File Tools

## 1. Purpose

File Tools let the Coding Agent read, search, and change project files in a controlled way.

## 2. Tools

| Tool | Permission | Default policy |
|---|---|---|
| `list_files` | READ | ALLOW |
| `read_file` | READ | ALLOW |
| `search_files` | READ | ALLOW |
| `create_file` | WRITE | ASK_USER |
| `write_file` | WRITE | ASK_USER |
| `delete_file` | WRITE | ASK_USER (always) |

### list_files

```json
{ "path": "src" }
```

### read_file

```json
{ "path": "src/Main.kt" }
```

Large files are truncated with a note telling the model how to read a specific line range.

### search_files

```json
{ "query": "NavigationBar", "path": "src" }
```

Returns file paths with line numbers and a short snippet, not whole files.

### create_file

```json
{ "path": "src/Utils.kt", "content": "full file contents" }
```

Fails if the file already exists.

### write_file

```json
{ "path": "src/Main.kt", "content": "complete new file contents" }
```

Replaces the whole file. The agent should show a diff to the user before or after writing.

### delete_file

```json
{ "path": "src/Old.kt" }
```

Always requires approval.

## 3. Safety Rules

- Resolve every path against the project root and reject anything outside it (including `..` and symlinks)
- Reject ignored or sensitive paths: `.git/`, `.env`, `local.properties`, keystores, `build/`, `node_modules/`
- Reject binary files
- Treat dependency manifests and lockfiles (`package.json`, lockfiles, `build.gradle(.kts)`, `libs.versions.toml`) as DEPENDENCY level, which always needs approval ([[📄 Dependency Rules]])
- After every `create_file` or `write_file`, run `check_imports` automatically and return the result with the write result
- Limit output size (suggested: 20 KB per read)
- Back up the previous file contents before `write_file` so the change can be undone

## 4. Large File Fallback

Small models can drop code when asked to return a very large file. If a file is longer than about 300 lines, the agent should either:

1. Ask the model to change only one function and splice it back into the file in code, or
2. Add a `replace_in_file` tool (exact old text to new text, fails if the old text is not found exactly once)

Complete-file output stays the default for normal-size files.

## 5. Flow

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
Path Validation
   |
   v
Permission Check
   |
   v
File Operation
   |
   v
Tool Result
```

## 6. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 Project Rules]]
