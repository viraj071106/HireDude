# Task Playbooks

## 1. Purpose

Step-by-step recipes for common tasks. The agent selects the matching playbook and adds only that one to the model context.

These match the test tasks in [[📄Model Selection]], so the same playbooks can be used to evaluate each model.

## 2. Explain Code

1. `search_files` for the file or symbol if the path is unknown
2. `read_file` the file
3. Explain what it does in plain language, focusing on the part asked about
4. Do not modify anything

## 3. Find a Bug

1. Get the exact symptom (error text, wrong behavior)
2. `read_file` the file where it appears
3. `search_files` for related callers and definitions
4. State the most likely cause and the evidence from the code
5. Propose the fix; do not apply it until asked

## 4. Modify a Small Function

1. `read_file` the file
2. Change only the requested function
3. `write_file` the complete file
4. Read the automatic `check_imports` result and fix any problem
5. Build: `run_gradle` (apps) or `run_npm_script` with `build` (web)
6. `git_diff` to confirm only the intended lines changed
7. Report using the format in [[📄 System Prompt]]

## 5. Fix a Build Error

1. `run_gradle` to get the real error
2. Find the **first** error in the output
3. `read_file` the file and line it points to
4. Apply the smallest fix
5. `run_gradle` again
6. Repeat up to 3 times, then stop and report what is still failing

## 6. Add a Feature

1. `project_structure` and `project_info` to understand the layout
2. `search_files` for similar existing features and copy their pattern
3. Make a short plan (3-6 bullets) and show it to the user
4. After approval, change one file at a time
5. Build after each file
6. `run_tests` at the end
7. `git_diff` and summarize

## 7. Understand Multiple Files

1. `project_structure`
2. `read_file` only the files that matter, one at a time
3. Summarize each file in one or two lines before reading the next
4. Give the combined explanation

## 8. Work With Git

1. `git_status`
2. `git_diff`
3. Summarize the changes
4. Only if asked: `git_commit` with a short message (approval required)
5. Only if asked: `git_push` (approval required)

## 9. Web: Add a Page or Component

1. `read_file` `package.json` to see the framework and scripts
2. `project_structure` and `search_files` for a similar existing page or component
3. Copy that pattern: same folder, naming, styling approach, and imports
4. Show a short plan (3-6 bullets) to the user
5. Create the new file with `create_file` after approval
6. Add the route or import in the one place the project uses
7. `check_imports`, then `run_npm_script` with `build` and `lint`
8. Do not add any package; ask if one seems needed

## 10. Web: Fix a Bug

1. Get the exact symptom (console error, wrong output, failing URL)
2. `read_file` the file named in the error
3. `search_files` for the function, component, or route involved
4. State the cause with the file and line as evidence
5. Fix only that cause
6. `run_npm_script` with `build` and `test`; report what was not run

## 11. Web: Add an API Endpoint

1. `search_files` for existing routes and copy their structure
2. Check how input is validated and how errors are returned
3. Add the route with server-side validation and the project's error format
4. Never put secrets in the code; read them from environment variable names already used
5. Build and test; list any manual check the user must do

## 12. Dependency Request (the model thinks it needs a new package)

1. Stop. Do not edit `package.json` or Gradle files
2. Reply with: package name from the user or project files, why it is needed, and an alternative without it
3. Wait for the user's decision
4. Only the user or an approved tool call changes the manifest

## 13. Limits

- Maximum 15 tool calls per task, then stop and report progress
- Maximum 3 build-fix attempts for the same error
- If the same tool call with the same arguments is made twice, stop and ask the user

## 14. Relationships

- [[📄 Agent Loop|Agent Loop]]
- [[📄 System Prompt]]
- [[📄 Project Rules]]
- [[📄 Web Development Rules]]
- [[📄 Verification Gates]]
- [[📄 Dependency Rules]]
- [[📄 Tool Calling|Tool Calling]]
- [[📄 Tool Registry]]
