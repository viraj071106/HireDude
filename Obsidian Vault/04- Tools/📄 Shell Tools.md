# Shell Tools

## 1. Purpose

Shell Tools provide controlled access to command-line operations.

They are higher risk than read-only project tools and should therefore pass through the permission system.

## 2. Primary Tool

### execute_command

Conceptual definition:

```json
{
  "name": "execute_command",
  "description": "Execute an approved command in the project environment",
  "arguments": {
    "command": "string",
    "workingDirectory": "string"
  },
  "permission": "EXECUTE"
}
```

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
   +---- Denied
   |
   +---- Approval
   |
   +---- Allowed
            |
            v
      Shell Execution
            |
            v
        Tool Result
```

## 4. Restrictions

Shell execution should eventually support:

- Allowed command policies
- Working-directory restrictions
- Environment restrictions
- Timeout
- Output limits
- User approval for risky commands

## 5. Command Policy

Default is **deny**. Only commands on an allow-list may run, and risky ones need approval.

Allowed without approval (read-only):

```text
git status, git diff, git log
ls, cat, head, tail, grep, find   (inside the project only)
```

Allowed with approval (EXECUTE):

```text
./gradlew <task>
npm run build | test | lint | typecheck
```

Always denied:

```text
sudo, su, chmod 777, chown
rm -rf, deleting outside the project
curl | sh, wget | sh, or running downloaded scripts
git push --force, git reset --hard, history rewriting
printing or editing .env files, keys, or keystores
```

Always DEPENDENCY level (approval required, see [[📄 Dependency Rules]]):

```text
npm install / add / update, yarn add, pnpm add, pip install
```

Rules:

- Compare the full command against the policy, not just the first word
- Block command chaining (`&&`, `;`, `|`, backticks, `$()`) unless every part is allowed
- Always set a timeout and an output limit
- Run only inside the project working directory

## 6. Terminal Tools

A separate `Terminal Tools` document is not required if it duplicates shell execution.

The canonical tool should be `execute_command` unless a later design introduces a genuinely different terminal abstraction.

## 7. Relationships

- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
