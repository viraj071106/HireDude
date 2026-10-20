# Local API Server

## 1. Purpose

The Local API Server is the communication boundary between the KMP Mobile Application and the Coding Agent running on the development computer.

It receives requests from the mobile application, validates them, passes them to the Coding Agent, and returns or streams the result.

The API server does not perform the model's reasoning and does not directly execute development tools.

## 2. Position in the System

```text
KMP Mobile App
      |
      | HTTP / HTTPS
      v
Local API Server
      |
      v
Coding Agent
      |
      +------> Context Management
      +------> AI Model
      +------> Tool System
```

## 3. Responsibilities

- Accept client requests
- Authenticate requests when required
- Validate request data
- Create and manage task/session identifiers
- Forward requests to the Coding Agent
- Stream agent events when required
- Return final responses
- Report task status
- Handle cancellation requests
- Return structured errors

## 4. What It Does Not Do

The API server should not:

- Reason about the coding task
- Select tools itself
- Execute shell commands directly
- Read project files directly
- Call Gradle directly
- Modify Git directly
- Replace the Agent Loop

Those responsibilities belong to the Coding Agent and Tool System.

## 5. Request Flow

```text
Mobile App
    |
    v
API Request
    |
    v
Validate
    |
    v
Coding Agent
    |
    v
Agent Loop
    |
    v
AI Model / Tools
    |
    v
Agent Result
    |
    v
API Response
    |
    v
Mobile App
```

## 6. Example Request

```json
{
  "sessionId": "session-123",
  "projectId": "project-001",
  "message": "Find why the build is failing."
}
```

## 7. Example Response

```json
{
  "success": true,
  "sessionId": "session-123",
  "taskId": "task-456",
  "status": "COMPLETED",
  "message": "The build failure is caused by..."
}
```

## 8. Streaming

Long-running tasks should support streaming.

```text
Coding Agent
     |
     +---- status event
     +---- model response
     +---- tool request
     +---- tool result
     +---- model response
     +---- final response
     |
     v
Local API Server
     |
     v
KMP Mobile App
```

## 9. Task States

```text
QUEUED
  ↓
RUNNING
  ↓
WAITING_FOR_APPROVAL
  ↓
RUNNING
  ↓
COMPLETED
```

Other terminal states:

```text
FAILED
CANCELLED
```

## 10. Security Boundary

Because the API can indirectly control the development environment, it must not be treated as an unrestricted public API.

Possible protections include:

- Authentication
- Request validation
- Project allow-listing
- Tool permission checks
- Local-network restrictions
- Secure transport when required
- Rate limiting

## 11. Endpoints

| Method | Path | Purpose |
|---|---|---|
| GET | `/projects` | List allow-listed projects |
| POST | `/sessions` | Create a session |
| POST | `/sessions/{sessionId}/messages` | Send a user message, creates a task |
| GET | `/tasks/{taskId}` | Task status and result |
| GET | `/tasks/{taskId}/events` | Stream agent events (SSE) |
| POST | `/tasks/{taskId}/approve` | Approve or reject a pending tool call |
| POST | `/tasks/{taskId}/cancel` | Cancel a running task |

Example approval request event:

```json
{
  "type": "APPROVAL_REQUIRED",
  "taskId": "task-456",
  "approvalId": "appr-1",
  "tool": "git_commit",
  "arguments": { "message": "Fix navigation crash" }
}
```

Example approval response:

```json
{ "approvalId": "appr-1", "approved": true }
```

## 12. Network Defaults

- Bind to `127.0.0.1` by default
- To allow phone access on the LAN, bind to the LAN address and require a token on every request
- Accept requests only for allow-listed projects
- Never expose this API to the internet

## 13. Relationships


- [[📄 Mobile Architecture|Mobile Architecture]]
- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Agent Loop|Agent Loop]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄Ollama|Ollama]]
