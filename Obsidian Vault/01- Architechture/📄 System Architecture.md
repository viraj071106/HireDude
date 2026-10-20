# System Architecture

## 1. Overview

This project is a local AI coding assistant.

The AI models will run locally on my computer using Ollama. The system will provide a coding assistant that can understand my project, inspect files, use development tools, generate code, and validate changes.

A mobile application built with Kotlin Multiplatform will act as the GUI for interacting with the local AI system.

The main goal is to keep the AI and project data local while providing a convenient mobile interface.

---

## 2. Main Components

The system consists of the following components:

1. Ollama
2. Local AI Models
3. Coding Agent
4. Tool System
5. Local API Server
6. Project Files
7. Git
8. KMP Mobile Application
9. Obsidian Knowledge Base

---

## 3. High-Level Architecture

```text
                    USER
                     |
                     v
              KMP Mobile App
                     |
                  HTTPS/LAN
                     |
                     v
              Local API Server
                     |
                     v
               Coding Agent
              /      |       \
             /       |        \
            v        v         v
        Project    Tools     Ollama
         Files       |         |
                     |         v
                     |      AI Model
                     |
             +-------+-------+
             |       |       |
             v       v       v
           Files    Git    Gradle
```

## Related Documents

- [[📄 Agent Architecture|Agent Architecture]]
- [[📄 Mobile Architecture|Mobile Architecture]]
- [[📄 Tool Architecture|Tool Architecture]]
- [[📄Ollama|Ollama]]
- [[📄Model Selection|Model Selection]]
- [[📄 Context Management|Context Management]]
- [[📄 Tool Calling|Tool Calling]]