# Agent Architecture

## 1. Overview

The Coding Agent is the central controller of the Local AI Coding Assistant.

It connects the user, AI model, project files, development tools, Git, build system, and local API.

The AI model provides reasoning and code generation.

The Coding Agent provides the ability to interact with the real development environment.

```text
                    USER
                     |
                     v
              Mobile / Terminal
                     |
                     v
                Local API
                     |
                     v
                CODING AGENT
                     |
        +------------+------------+
        |            |            |
        v            v            v
     Context       Tools       Ollama
     Manager         |            |
        |       +----+----+       |
        |       |    |    |       v
        |       v    v    v    AI Model
        |     Files Git Gradle
        |       |
        +-------+
            |
            v
       Tool Results
            |
            v
        CODING AGENT
            |
            v
       Final Response
```

## Related Documents

- [[📄 Agent Loop|Agent Loop]]
- [[📄 System Architecture|System Architecture]]
- [[📄 Context Management|Context Management]]
- [[📄 Tool Calling|Tool Calling]]
- [[📄 Memory|Memory]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄Ollama|Ollama]]
- [[📄Model Selection|Model Selection]]