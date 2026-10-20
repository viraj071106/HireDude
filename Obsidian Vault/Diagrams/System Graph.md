# System Graph

Visual map of the Local AI Coding Assistant. Obsidian renders the diagrams below.

## Components

```mermaid
flowchart TD
    User([User]) --> App[KMP Mobile App]
    User --> Term[Terminal]
    App -->|HTTP / LAN| API[Local API Server]
    Term --> API
    API --> Agent[Coding Agent]
    Agent --> Ctx[Context Manager]
    Agent --> Ollama[Ollama]
    Ollama --> Model[Qwen Coder Model]
    Agent --> Tools[Tool System]
    Tools --> Perm{Permission Check}
    Perm -->|Allowed| Exec[Tool Execution]
    Perm -->|Ask| App
    Perm -->|Denied| Agent
    Exec --> Files[Project Files]
    Exec --> Git[Git]
    Exec --> Gradle[Gradle]
    Ctx --> Vault[Obsidian Vault]
    Ctx --> Mem[Project Memory]
```

## Agent Loop

```mermaid
flowchart TD
    A[User request] --> B[Gather context]
    B --> C[Ask model]
    C --> D{Tool call?}
    D -->|Yes| E[Validate and permission check]
    E --> F[Execute tool]
    F --> G[Tool result]
    G --> C
    D -->|No| H[Validate result with build / tests]
    H --> I[Final response]
```

## Notes

- [[📄 System Architecture|System Architecture]]
- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Agent Loop|Agent Loop]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄 Mobile Architecture|Mobile Architecture]]
