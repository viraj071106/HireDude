# Local AI System

## My Project

Local Coding AI

## Current Work

- Ollama
- Coding Agent
- Tools
- Mobile GUI
- Git

## Hardware

- GPU: RTX 3050 6GB
- RAM: 16GB
- CPU: AMD Ryzen 7 6000H

## Model

Qwen2.5-Coder through Ollama. Start with `7b`, compare with `3b`. Selection is based on testing, see [[📄Model Selection]].

## Roadmap

| Phase | Goal | Done when |
|---|---|---|
| 1 | Run the model and test it | `ollama run` works, first results in [[📄 Model observations]] |
| 2 | Terminal agent, read-only tools | Agent answers questions using `list_files`, `read_file`, `search_files`, `check_dependency` |
| 3 | Write and build tools with approval and gates | Agent edits a file, `check_imports` runs automatically, builds with Gradle or npm, shows the diff, and blocks dependency changes |
| 4 | Git tools | Agent shows status and diff, commits only after approval |
| 5 | Local API and KMP mobile app | Phone can start a task, see events, approve tools |

Build one phase at a time. Do not start Phase 3 before Phase 2 is reliable.

## Notes Map

### Architecture
- [[📄 System Architecture|System Architecture]]
- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄 Mobile Architecture|Mobile Architecture]]
- [[📄 Local API Server]]

### Models
- [[AI Model]]
- [[📄Ollama]]
- [[📄Model Selection]]
- [[📄 Model observations]]

### Agent
- [[📄 Agent Loop|Agent Loop]]
- [[📄 Context Management|Context Management]]
- [[📄 Tool Calling|Tool Calling]]
- [[📄 Memory|Memory]]

### Tools
- [[📄 Tools Overview]]
- [[📄 Tool Registry]]
- [[📄 Tool Permissions]]
- [[📄 Tool Execution]]
- [[📄 Tool Result]]
- [[📄 File Tools]]
- [[📄 Git Tools]]
- [[📄 Build Tools]]
- [[📄 Project Tools]]
- [[📄 Shell Tools]]

### Instructions for the Model
- [[📄 System Prompt]]
- [[📄 Project Rules]]
- [[📄 Web Development Rules]]
- [[📄 Anti-Hallucination Rules]]
- [[📄 Dependency Rules]]
- [[📄 Verification Gates]]
- [[📄 Task Playbooks]]

### Diagrams
- [[System Graph]]

## Open Questions

- Which language will the Coding Agent be written in?
- Which model size gives the best balance of speed and quality on 6GB VRAM?
- Should the agent add a `replace_in_file` tool for large files?
- Which languages will `check_imports` support first (Kotlin, TypeScript/JavaScript)?
- How will the mobile app authenticate with the Local API?
