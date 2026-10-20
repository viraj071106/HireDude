# Model Selection

## Goal

Choose the local AI model that will be used by the Coding Agent.

## Requirements

The model should have:

- Strong coding ability
- Good reasoning
- Good instruction following
- Tool-calling support
- Reasonable response speed
- Good performance on my hardware
- Acceptable memory usage

## Hardware

- GPU: RTX 3050 6GB
- RAM: 16GB
- CPU: AMD Ryzen 7 6000H

## Candidates (before testing)

Sizes below are Ollama Q4_K_M downloads. A model also needs extra memory for its context window.

| Model | Tag | Download | Fit on 6GB VRAM | Notes |
|---|---|---|---|---|
| Qwen2.5-Coder 3B | `qwen2.5-coder:3b` | small | Comfortable | Fastest; weaker on multi-file reasoning |
| Qwen2.5-Coder 7B | `qwen2.5-coder:7b` | ~4.7GB | Tight; keep `num_ctx` modest | Best first candidate |
| Qwen2.5-Coder 14B | `qwen2.5-coder:14b` | ~9GB | No; spills into system RAM and slows down | Try only to compare quality |
| Qwen3-Coder 30B | `qwen3-coder:30b` | ~19GB | No | Too large for this hardware |

There is no 2.8B Qwen coder model. The nearest sizes are 3B and 7B.

## Models Tested

| Model | Size | Speed | Coding | Reasoning | VRAM/RAM | Result |
|---|---|---|---|---|---|---|
| | | | | | | |

## Testing Tasks

Test each model with:

1. Explain existing code
2. Find a bug
3. Modify a small function
4. Generate a new feature
5. Understand multiple files
6. Follow project rules
7. Work with tools
8. Fix a build error
9. Ask about a function that does not exist in the library (it should say it does not know)
10. Ask for a feature that tempts it to add a new package (it should ask, not add)
11. Ask it to create a helper that could import itself (it must not)
12. Ask it to use a file path you did not give it (it should search first)
13. Make it write a web component, then check that every import exists

## Selection Criteria

The final model will be selected based on actual testing rather than model size alone.

## How to Score

Rate each task 1-5 and note speed in tokens/second (`ollama run <model> --verbose`).

- 5: correct on the first try
- 3: needed one correction
- 1: wrong or invented code

Also record how often it invented imports, files, or packages (tests 9 to 13), and whether the model produced valid tool calls in the Tool Calling test ([[📄 Tool Calling|Tool Calling]]).

## Notes

Record problems, strengths, speed, and observations for each model.

See also: [[📄Ollama]], [[📄 Model observations]], [[📄 System Prompt]], [[📄 Task Playbooks]]
