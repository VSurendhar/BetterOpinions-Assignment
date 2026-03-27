# Android Application — Implementation Overview

This document outlines the technical decisions, development approach, and architectural choices made during the implementation of this Android application.

---

## Architecture & Design Decision

The application was built using the **MVI (Model–View–Intent)** architecture. Prior to development, a thorough review of the Figma design was conducted to identify all required screens, user flows, and component relationships.

MVI was selected for its simplicity and predictability at this project's sdcale. For larger, more complex applications, **Clean Architecture with MVVM** would be the preferred approach — however, introducing that level of abstraction here would have added unnecessary overhead without meaningful benefit.

---

## Resource Preparation

Before development began, all design assets were extracted and prepared:

- SVG assets exported from Figma and converted to XML vector drawables
- PNG assets imported and organised within the project structure

---

## Development Planning

Each screen was planned in advance by defining its inputs, outputs, UI components, and data flow. This produced a clear development roadmap before any code was written.

Development followed an **incremental approach** — starting with a foundational skeleton and progressively refining each component to match the Figma design with precision.

The following tools were used to support development:

| Tool | Purpose |
|------|---------|
| **ChatGPT** | Architecture discussions and flow planning |
| **Claude** | Code generation assistance |
| **Anti-gravity tools** | Agentic development support |

---

## Version Control

Commits were structured to reflect discrete, logical stages of development. The git history provides a clear and traceable record of the project's progression from initial setup through to final implementation.

---

## Project Structure

| Property | Detail |
|----------|--------|
| **Architecture** | MVI |
| **Module Structure** | Monolithic |

A single-module architecture is appropriate for the current scale of the application. Should the project grow in complexity or team size, it is well-positioned for migration to a **multi-module architecture** without significant restructuring.

---

## Resource Handling

The application currently uses **locally bundled assets** rather than remote API resources. This approach slightly increases the APK size, but was appropriate for the scope of this assignment.

Upon backend integration, the application size is estimated to reduce to **under 5 MB**.

---

## UI Scope

The following decisions were made intentionally, based on the defined scope of the assignment:

- **Orientation:** Portrait mode only
- **Theme:** Light mode only
- **Excluded:** Dark mode and dynamic theming were not part of the assignment requirements and were therefore omitted

---

## Release Build Configuration

The release build was generated with the following optimisations:

- Non-debuggable configuration
- Resource shrinking enabled
- Code optimisation and compression applied
- Signed APK produced

---

## Additional Feature — Catalog Search

A **search feature** was implemented on the home screen as an enhancement beyond the original requirements.

**Key details:**

- Enables users to search catalogs by name
- Designed to handle large datasets efficiently
- Implements a **300ms debounce** to prevent redundant computations on every keystroke

This feature improves the overall usability of the application while maintaining consistent performance during active input.

---

## Device Compatibility & Display Testing

The application was tested across multiple device configurations to validate UI stability and visual consistency.

Testing was conducted on:

- **A high-performance mobile device** — to verify smooth rendering and expected behaviour under standard conditions
- **A device with large display size settings** — where the system font size and display size were configured beyond the default Android values (i.e., accessibility-level scaling)

During large display size testing, several UI layout issues were identified. These were subsequently diagnosed and resolved, ensuring the application renders correctly under **non-default system display and font size configurations**. The application is now compatible with devices where users have increased the display size or font size through Android system settings.

---

## Summary

| Area | Detail |
|------|--------|
| Architecture | MVI |
| Module Structure | Monolithic |
| Orientation Support | Portrait only |
| Theme | Light mode only |
| Asset Source | Locally bundled |
| Release Build | Signed, shrunk, optimised |
| Additional Feature | Debounced catalog search |
| Display Compatibility | Verified on large display/font size settings |