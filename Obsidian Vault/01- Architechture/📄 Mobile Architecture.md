# Mobile Architecture

## 1. Purpose

The Mobile Application is the user interface for the Local AI Coding Assistant.

It is built using Kotlin Multiplatform and communicates with the local computer through the [[📄 Local API Server]].

The mobile application does not own the Coding Agent, AI model, project files, or development tools.

## 2. High-Level Architecture

```text
User
 |
 v
KMP Mobile App
 |
 +--> UI
 |
 +--> Application State
 |
 +--> API Client
 |
 +--> Session State
 |
 +--> Approval UI
 |
 v
Local API Server
 |
 v
Coding Agent
```

## 3. Main Components

### UI

Responsible for:

- Chat interface
- Project selection
- Task status
- Tool approval
- Tool activity
- Errors
- Settings

### API Client

Responsible for:

- Connecting to the Local API
- Sending requests
- Receiving responses
- Receiving streamed events
- Handling connection errors

### Session State

Maintains the current:

- Session ID
- Task ID
- Conversation state
- Connection state

### Task State

Displays states such as:

```text
IDLE
QUEUED
RUNNING
WAITING_FOR_APPROVAL
COMPLETED
FAILED
CANCELLED
```

## 4. Request Flow

```text
User
 |
 v
UI
 |
 v
ViewModel / State Layer
 |
 v
API Client
 |
 v
Local API Server
 |
 v
Coding Agent
```

## 5. Tool Approval Flow

```text
Coding Agent
      |
      v
Tool Permission Check
      |
      v
Approval Required
      |
      v
Local API
      |
      v
KMP App
      |
      v
User
      |
      v
Approve / Reject
      |
      v
Local API
      |
      v
Coding Agent
```

## 6. Streaming

The mobile application should be able to display incremental events.

```text
API Server
   |
   +--> Agent status
   +--> Model response
   +--> Tool request
   +--> Tool result
   +--> Final response
   |
   v
KMP UI
```

## 7. Offline vs Local-Network

The mobile application may run:

- On the same development computer
- On another device connected to the same local network

When running on another device, it communicates with the development computer through the local network.

## 8. Security

The mobile app should authenticate with the Local API when authentication is enabled.

The app should never receive unrestricted filesystem or shell access.

## 9. Relationships

- [[📄 Local API Server]]
- [[📄 System Architecture|System Architecture]]
- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Agent Loop|Agent Loop]]
- [[📄 Tool Permissions]]
