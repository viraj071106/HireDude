## Purpose

Ollama will be used to run AI models locally on my computer.

## Role in the System

Ollama is the model runtime.

It does not control my project, Git, files, or Gradle.

The Coding Agent communicates with Ollama and uses the model's output to decide what to do.

## Architecture

User
↓
Mobile App / Terminal
↓
Local API
↓
Coding Agent
↓
Ollama
↓
AI Model

## Responsibilities

- Load local AI models
- Run inference locally
- Receive prompts from the Coding Agent
- Generate responses
- Generate code
- Analyze code
- Return model output

## Important Rule

The AI model is not the source of truth.

The actual project files, Git state, and build/test results are the source of truth.

## Model Requirements

The model should be evaluated for:

- Coding ability
- Reasoning ability
- Context handling
- Tool calling
- Speed
- RAM usage
- VRAM usage
- Response quality

## Models to Test

Start with these two and compare them using [[📄Model Selection]]:

- `qwen2.5-coder:7b`
- `qwen2.5-coder:3b`

Record results in [[📄 Model observations]].

## Notes

Record model experiments, problems, performance, and observations here.

## Setup

```bash
ollama pull qwen2.5-coder:7b
ollama run qwen2.5-coder:7b
ollama list          # installed models
ollama ps            # loaded models and whether they run on GPU or CPU
```

Note: there is no `qwen2.5-coder` size called "2.8b". The sizes are 0.5b, 1.5b, 3b, 7b, 14b, and 32b.

## Local API

Ollama listens on `http://localhost:11434`. The Coding Agent uses `/api/chat`.

```bash
curl http://localhost:11434/api/chat -d '{
  "model": "qwen2.5-coder:7b",
  "messages": [{ "role": "user", "content": "Explain Kotlin sealed classes in two lines." }],
  "stream": false
}'
```

Keep Ollama bound to localhost. The mobile app talks to the [[📄 Local API Server]], never directly to Ollama.

## Recommended Starting Settings

Set these on every request (or in a Modelfile) and adjust after testing.

```text
num_ctx      8192    # context window; raise only if VRAM allows
temperature  0.2     # low, for stable code and tool calls
top_p        0.9
keep_alive   10m     # keep the model loaded between requests
```

Modelfile example:

```text
FROM qwen2.5-coder:7b
PARAMETER num_ctx 8192
PARAMETER temperature 0.2
PARAMETER top_p 0.9
```

```bash
ollama create local-coder -f Modelfile
```

The system prompt is sent by the agent on each request (see [[📄 System Prompt]]), so it does not need to be inside the Modelfile.

## Hardware Notes

- GPU: RTX 3050 6GB, RAM: 16GB (see [[📄Model Selection]])
- A larger `num_ctx` uses more VRAM. If `ollama ps` shows part of the model on CPU, lower `num_ctx` or use a smaller model
- Close other GPU-heavy apps while testing

## Tool Calling

Qwen coder models are listed in Ollama with tool support, but small models can still produce malformed tool calls. The agent includes a fallback parser (see [[📄 Tool Calling|Tool Calling]]).

## Relationships

- [[📄 System Prompt]]
- [[📄 Tool Calling|Tool Calling]]
- [[📄 Local API Server]]
