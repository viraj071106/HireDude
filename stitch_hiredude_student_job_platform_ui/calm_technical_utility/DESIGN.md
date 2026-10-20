---
name: Calm Technical Utility
colors:
  surface: '#fbf8ff'
  surface-dim: '#d7d8f0'
  surface-bright: '#fbf8ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f4f2ff'
  surface-container: '#ececff'
  surface-container-high: '#e5e6fe'
  surface-container-highest: '#dfe1f9'
  on-surface: '#181b2b'
  on-surface-variant: '#51424e'
  inverse-surface: '#2c2f41'
  inverse-on-surface: '#f0efff'
  outline: '#84727f'
  outline-variant: '#d6c0d0'
  surface-tint: '#9b2e9b'
  primary: '#6a006d'
  on-primary: '#ffffff'
  primary-container: '#8a1c8c'
  on-primary-container: '#ffa5f7'
  inverse-primary: '#ffaaf7'
  secondary: '#844981'
  on-secondary: '#ffffff'
  secondary-container: '#ffb8f8'
  on-secondary-container: '#7c437a'
  tertiary: '#3a3c00'
  on-tertiary: '#ffffff'
  tertiary-container: '#515400'
  on-tertiary-container: '#c5c96d'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#ffd6f7'
  primary-fixed-dim: '#ffaaf7'
  on-primary-fixed: '#380039'
  on-primary-fixed-variant: '#7e0a81'
  secondary-fixed: '#ffd6f8'
  secondary-fixed-dim: '#f6afef'
  on-secondary-fixed: '#370139'
  on-secondary-fixed-variant: '#693168'
  tertiary-fixed: '#e5e889'
  tertiary-fixed-dim: '#c8cc70'
  on-tertiary-fixed: '#1c1d00'
  on-tertiary-fixed-variant: '#474a00'
  background: '#fbf8ff'
  on-background: '#181b2b'
  surface-variant: '#dfe1f9'
typography:
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
    letterSpacing: -0.015em
  title-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
    letterSpacing: -0.01em
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
    letterSpacing: 0em
  body-md-medium:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0em
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.01em
  tabular-metric:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: -0.01em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 0.5rem
  margin: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 0.75rem
  space-lg: 1rem
  space-xl: 1.5rem
---

## Brand & Style

This design system is engineered for Indian university students, graduates, and engineering talent navigating internships and early career roles. Built with the discipline, restraint, and obsessive detail of early developer utilities (Linear, Vercel), it strips away the gamification, cartoon avatars, aggressive popups, and conversational fluff common in early-career portals.

The aesthetic is **Strict Utilitarian Minimalism**: dense information hierarchy, quiet visual rhythm, deliberate ink contrast, and tactile hairline boundaries. It does not employ decorative drop shadows, gradient text, ambient blur orbs, or floating decorative pills. Every visual choice must serve scanability, rapid comparison of compensation, verified credential validation, and frictionless submission.

The brand voice is precise, respectful, and objective. It communicates parity with professional desktop software adapted to a pocket form-factor.

## Colors

The system uses a strictly controlled, high-clarity light mode architecture with deep plum accents:

- **Primary Accent (`#8A1C8C`)**: Flat magenta-purple used sparingly. Reserved exclusively for primary actions, current navigation indicators, active filter states, and actionable inline links. It must never be applied to large background fields or decorative iconography.
- **Signature Accent (`#4A154B`)**: Deep structural plum. Paired strictly with `#8E2390` in linear gradient treatments (`135deg, #4A154B 0%, #8E2390 100%`) reserved solely for the initial cold splash viewport and elevated tier identification (e.g., verified IIT/NIT alumni talent pool badges or featured application spotlight cards).
- **Surface & Foundation**:
  - Page Background: `#FAF9FB` (soft, anti-glare canvas).
  - Surface Containers: Pure `#FFFFFF` (content cards, input backgrounds, navigation bars).
  - Hairline Boundaries: `#E8E2EA` (uniform 1px borders, table dividers, list delimiters).
- **Text & Content**:
  - Ink Primary: `#1A1D2E` (headings, primary body, tabular metrics).
  - Slate Muted: `#6B7280` (timestamps, secondary meta-data, sub-labels, input placeholders).
- **Functional Semantics (Strictly functional, no soft pastel backgrounds)**:
  - Success: `#16A34A` (stipend confirmed, verified referral, application shortlisted).
  - Warning: `#D97706` (closing within 24 hours, incomplete portfolio).
  - Critical: `#DC2626` (closed role, mandatory field missing, application rejected).

## Typography

Typography relies on a crisp, modern geometric sans-serif tuned for high data density on mobile viewports. Poppins-like architectural clarity is paired with restrained scales to prevent screen overcrowding.

### Rules of Application
- **Scale Discipline**: Never deviate from the 5 strict size increments: `24px` (view titles), `20px` (modal/sheet headers), `16px` (section groupings and inputs), `14px` (primary readouts, company names, descriptions), and `12px` (meta attributes, badges, tags, timestamps).
- **Tabular Figures**: All financial tokens, compensation values (e.g., `₹1,20,000/mo`, `₹28 LPA`), CGPA metrics (`9.42 / 10`), and numerical counters must declare `font-variant-numeric: tabular-nums` to guarantee aligned columns in dense lists.
- **Micro-Copy**: Labels and system tags at 12px utilize medium (`500`) weight with a strict +0.01em tracking to avoid letter collisions on low-DPI displays.
- **Anti-Styling Mandate**: No text stroke gradients, no multi-color headline highlighting, and no cursive or novelty font overrides under any circumstances.

## Layout & Spacing

The layout is built around a standard 390px base mobile canvas, extending vertically with uninterrupted rhythmic spacing.

- **Grid & Margins**: Fixed 16px (`1rem`) outer canvas padding on horizontal edges ensures high usability while maximizing data breadth for mobile screens. Content blocks span the full width within margins unless arranged in strict 2-column metric grids.
- **Rhythm**: Hard adherence to a 4px sub-grid and 8px primary grid. Spacers (`space-xs` = 4px, `space-sm` = 8px, `space-md` = 12px, `space-lg` = 16px, `space-xl` = 24px) govern internal paddings.
- **Target Sizes**: Every interactive trigger—tabs, filter chips, action buttons, table cell links—maintains an invisible bounding box of at least 44px × 44px for physical tap compliance, even when visual badge heights are compact (28px–32px).
- **Stack Density**: Card stacks and list items use 8px gaps between sibling modules. Dividers are single-pixel hairlines spanning full card width with zero horizontal inset.

## Elevation & Depth

Visual hierarchy is constructed purely through **structural demarcation and tonal layering**, eliminating ambient drop shadows entirely.

- **Zero-Shadow Rule**: Standard interface cards, bottom bars, filter chips, and modular panels feature `box-shadow: none`. Depth is achieved solely via a crisp `1px solid #E8E2EA` boundary set against the `#FAF9FB` base canvas.
- **Surface Hierarchy**:
  - Level 0 (Base Canvas): `#FAF9FB`
  - Level 1 (Card & Content Blocks): `#FFFFFF` enclosed in `1px solid #E8E2EA`
  - Level 2 (Modals, Action Sheets, Floating Triggers): `#FFFFFF` enclosed in `1px solid #E8E2EA` with an optional utilitarian 12% black backdrop overlay (`rgba(26, 29, 46, 0.40)`) to focus user attention during sheet expansion.
- **Overlays & Sliders**: Do not use blur effects (backdrop-filter) or tinted chromatic glow. Floating action bars maintain crisp solid white backgrounds with a single top border `#E8E2EA`.

## Shapes

The geometry of the interface is sharp, controlled, and consistent across platforms.

- **Cards & Sheets**: Formed with a fixed `12px` border-radius (`0.75rem`), balancing modern usability with the industrial feel of a productivity dashboard.
- **Inputs & Interactive Controls**: Form inputs, standard buttons, and search rows share an identical `8px` corner radius.
- **Tags, Pills, & Chips**: Use a strict `6px` radius or full geometric pill (`9999px`) for single-word status tags (e.g., `Remote`, `PPO Offered`, `Day 1 Stipend`).
- **Dividers & Lines**: Exactly 1px thick with flat, squared terminations. No dashed or dotted stylistic variants.

## Components

### Buttons
- **Primary**: Solid `#8A1C8C` background, `#FFFFFF` text, 8px radius, height 44px (full-width on mobile action drawers). No bevels, no gradient fills.
- **Secondary / Ghost**: Pure `#FFFFFF` background, `#1A1D2E` text, `1px solid #E8E2EA`. Active state triggers `#FAF9FB` fill.
- **Destructive**: Bordered or text-only `#DC2626` with no red field wash.

### Cards & Job Modules
- Pure `#FFFFFF` surface, 12px radius, `1px solid #E8E2EA`, zero drop shadow.
- Interior layout hierarchy:
  - Top line: Company monogram/logo (32x32px squircle, hairline border) + Company Title (e.g., *Razorpay*, *Zepto*) + Location / Mode.
  - Middle line: Job Title (`16px`, semi-bold `#1A1D2E`).
  - Meta row: Key badges (`Software Development Engineer Intern`, `6 Months`, `On-site Bangalore`).
  - Baseline: Tabular compensation highlighted in dark ink (`₹1,25,000 / month`) paired with an instant apply chevron or trigger.

### Form Inputs & Search
- Surface: `#FFFFFF`, height 44px, radius 8px, `1px solid #E8E2EA`.
- Focused state: Border changes to `#8A1C8C` with a clean 1px inner focus rule (no diffuse halo).
- Placeholder: `#6B7280`, 14px size.
- Trailing clear buttons and search glyphs use standard 1.5px stroke weights in `#6B7280`.

### Status Indicators & Meta Badges
- Strict flat presentation. Avoid heavy solid background colors for tags.
- Format: Flat white background with a 1px border (`#E8E2EA`) paired with a colored status dot (e.g., 6px circle with `#16A34A` for "Active Hiring" or `#D97706` for "Closes Tomorrow").
- Text: 12px, `#1A1D2E` or `#6B7280`.

### Premium IIT/NIT Alumni Badges & Verification Cards
- The only component permitted to use the Signature Gradient (`linear-gradient(135deg, #4A154B 0%, #8E2390 100%)`).
- Text is pure white (`#FFFFFF`) with subtext in 70% opacity white. Border is omitted or set to `rgba(255, 255, 255, 0.15)`.

### Icons & Imagery
- **Icons**: 1.5px consistent stroke line icons exclusively. Never enclosed in tinted rounded squares or saturated circular containers.
- **Data Imagery**: Verified engineering logos (e.g., Swiggy, Cred, Groww, Meesho, Postman) rendered within clean squared containers with 1px border. Emojis and decorative 3D asset illustrations are prohibited across all views.