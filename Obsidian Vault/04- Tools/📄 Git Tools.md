# Git Tools

## 1. Purpose

Git Tools let the Coding Agent inspect and manage the Git repository of the current project.

## 2. Tools

| Tool | Permission | Default policy |
|---|---|---|
| `git_status` | READ | ALLOW |
| `git_diff` | READ | ALLOW |
| `git_log` | READ | ALLOW |
| `git_commit` | GIT_WRITE | ASK_USER |
| `git_push` | GIT_WRITE | ASK_USER (always) |

### git_status

Returns changed, staged, and untracked files.

### git_diff

```json
{ "path": "src/Main.kt", "staged": false }
```

Both arguments are optional. Output is truncated if it is too large.

### git_log

```json
{ "limit": 10 }
```

### git_commit

```json
{ "message": "Fix navigation crash", "paths": ["src/Main.kt"] }
```

Stages the listed paths and commits them.

### git_push

```json
{ "remote": "origin", "branch": "main" }
```

Always requires explicit user approval.

## 3. Rules

- Review `git_diff` before every commit
- Never commit `.env`, `local.properties`, keystores, or build output
- Never force-push; do not provide a force option
- Never rewrite history
- Commit and push only when the user asks
- Pushing uses the user's existing SSH setup; the agent never handles credentials

## 4. Flow

```text
Modify code
    |
    v
Build / Test
    |
    v
git_diff  --> show user
    |
    v
User asks to commit?
    |
    +---- No ------> Stop
    |
    +---- Yes -----> Approval --> git_commit
                                      |
                                      v
                            User asks to push?
                                      |
                                      +---- Yes --> Approval --> git_push
```

## 5. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 Agent Loop|Agent Loop]]
- [[📄 Project Rules]]
