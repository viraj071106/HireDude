# Web Development Rules

## 1. Purpose

Rules for web projects: HTML, CSS, JavaScript/TypeScript, React, and Node.js backends.

The same global rules apply as in [[📄 Project Rules]]. Where a project's real files differ from this note, the project wins.

## 2. Use What the Project Already Uses

- Detect the framework and tooling from `package.json` and the folder layout before writing anything
- Do not switch frameworks, bundlers, routers, or state libraries
- Do not add UI kits, CSS frameworks, or utility libraries
- Match the existing file naming, folder layout, and code style
- Do not rewrite JavaScript to TypeScript or the reverse unless asked

## 3. HTML

- Use semantic elements (`header`, `nav`, `main`, `section`, `footer`, `button`, `form`)
- Every `img` has `alt`; every form field has a `label`
- One `h1` per page and headings in order
- Do not use a `div` where a `button` or `a` is correct

## 4. CSS

- Reuse existing classes, variables, and design tokens before writing new ones
- Do not invent class names that other files rely on; search first
- Use relative units and flexbox/grid; avoid fixed pixel layouts
- Keep text readable: body at least 16px, enough color contrast
- Support keyboard focus (visible focus styles)

## 5. JavaScript / TypeScript

- Use modern standard syntax (`const`/`let`, `async`/`await`, optional chaining)
- Do not use `var`, `eval`, or `new Function`
- Handle errors explicitly (network failure, non-2xx, bad JSON)
- Do not use browser APIs that the target browsers lack without checking the project's browser support
- Do not invent a global; everything is imported or defined
- In TypeScript, avoid `any`; use proper types or `unknown`

## 6. React

- Function components and hooks only, unless the project uses classes
- Hooks are called at the top level, never in loops or conditions
- Keep state as local as possible
- Lists need stable `key`s (not array index for dynamic lists)
- Effects need correct dependency arrays and cleanup
- Do not use `dangerouslySetInnerHTML` with unsanitized content
- Check the installed React version in `package.json` before using newer APIs

## 7. Node.js Backend

- Use the server approach already in the project (built-in `http`, Express, or another). Do not add a framework
- Validate all input (type, length, allowed values) on the server
- Return consistent JSON errors with correct status codes
- Use parameterized queries, never string-concatenated SQL
- Do not log secrets or personal data
- Set timeouts and body-size limits
- Read configuration from environment variables

## 8. Security

- No secrets in source code or client bundles
- Variables prefixed for the browser (for example `VITE_`, `NEXT_PUBLIC_`) are **public**; never put private keys in them
- With Supabase: only the public `anon` key may appear in client code; never the `service_role` key
- Protect data with server-side rules (for example Row Level Security), not with hidden UI
- Escape or sanitize anything rendered from user input
- Use HTTPS URLs; do not disable certificate verification
- Check CORS settings; do not use `*` for authenticated APIs
- Never read, print, or edit `.env` files; refer to variable names only

## 9. Accessibility and UX

- Keyboard-reachable controls with visible focus
- Sufficient contrast and large tap targets (about 44px)
- Meaningful loading, empty, and error states
- Forms show clear validation messages
- Respect `prefers-reduced-motion`

## 10. Performance

- Do not load large libraries for small tasks
- Lazy-load heavy images and routes when the project already supports it
- Avoid unnecessary re-renders and large inline objects in React
- Do not fetch inside loops

## 11. Build Commands

The agent reads `scripts` in `package.json` and may run only these through `run_npm_script`:

```text
build
test
lint
typecheck
```

Do not run `dev` or `start` through the agent, because they never exit. Do not run install commands without approval ([[📄 Dependency Rules]]).

## 12. Relationships

- [[📄 Project Rules]]
- [[📄 Dependency Rules]]
- [[📄 Anti-Hallucination Rules]]
- [[📄 Verification Gates]]
- [[📄 Task Playbooks]]
- [[📄 Build Tools]]
