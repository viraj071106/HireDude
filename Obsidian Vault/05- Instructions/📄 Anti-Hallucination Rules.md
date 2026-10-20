# Anti-Hallucination Rules

## 1. Purpose

Stop the local model from inventing things: files, functions, packages, props, API fields, versions, or test results.

**Important:** prompt rules reduce hallucination but cannot remove it. A 3B or 7B model will still make things up sometimes. The real protection is the set of checks the **agent code** runs after every change. See [[📄 Verification Gates]].

```text
Prompt rules  -> make mistakes less likely
Agent checks  -> catch the mistakes that still happen
Real tools    -> build, lint, tests give the final answer
```

## 2. What Counts as Hallucination

- A file path that does not exist
- A function, class, hook, prop, or CSS class that is not defined anywhere
- A package or import that is not installed or declared
- A library method that does not exist in the installed version
- An API endpoint or database column that does not exist
- A claim like "the build passes" without running the build
- Invented version numbers or configuration keys

## 3. Rules for the Model

1. Facts about the project come only from tool results, never from memory
2. Before editing a file, read it in this session
3. Before using a library API, do one of these:
   - find an existing use of it in the project, or
   - read its type definitions or source in the installed package
4. Before importing something, confirm it exists (project file, declared dependency, or standard library)
5. Never invent file paths. Use `list_files` or `search_files` to find them
6. Never invent tool arguments. Values come from the user request or earlier tool results
7. If the answer is not in the context, say **"I don't know"** and name the tool that would find it
8. Never state that something works, passes, or is fixed unless a tool result shows it
9. Quote error messages exactly as they appear
10. When two sources disagree, trust this order: tool result > project file > Obsidian note > model memory

## 4. Approved Ways to Say You Are Unsure

The agent should accept and encourage these answers:

- "I don't know. I can check with `search_files`."
- "I can't verify that API. Let me read the type definitions."
- "I have not run the build, so I can't confirm this works."

An honest "I don't know" is always better than a confident wrong answer.

## 5. Grounding the Model

The agent reduces guessing by giving the model real data up front:

- The actual dependency list (see [[📄 Dependency Rules]])
- The project structure from `project_structure`
- The exact file contents being changed
- The first relevant build error, not the whole log

See [[📄 Context Management|Context Management]].

## 6. Settings That Help

- Low temperature (0.1 to 0.2) for code and tool calls (see [[📄Ollama]])
- Short context with only relevant files, since small models get worse with large irrelevant context
- One task and one tool call at a time
- Ask the model to cite the file and line for any claim about code

## 7. Known Weak Spots of Small Models

Watch for these in testing ([[📄Model Selection]]):

- Outdated library APIs from older training data
- Mixing APIs from different library versions
- Making up Gradle plugin IDs and npm package names
- Writing plausible but unused helper functions
- Saying "done" before running anything

## 8. Relationships

- [[📄 System Prompt]]
- [[📄 Verification Gates]]
- [[📄 Dependency Rules]]
- [[📄 Context Management|Context Management]]
- [[📄 Tool Calling|Tool Calling]]
