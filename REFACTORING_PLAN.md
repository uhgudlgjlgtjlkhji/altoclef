# AltoClef Refactoring Plan

This document tracks ongoing and planned refactoring efforts for the AltoClef mod codebase. The goal is to improve code maintainability, readability, and architecture while preserving all existing functionality.

## Completed Refactoring

### ✅ AltoClef.java (Core Hub Class)
**Status:** Completed
**Changes:**
- Extracted monolithic `onInitializeLoad()` into focused initialization methods
  - `initializeBaritoneSettings()`
  - `initializeCoreManagers()`
  - `initializeTaskChains()`
  - `initializeTrackers()`
  - `initializeUI()`
  - `initializeUtilities()`
  - `loadUserSettings()`
  - `setupEventHandlers()`
- Improved game loop methods with clear separation of concerns
  - `onClientTick()` now delegates to helper methods
  - Added `handleInputShortcuts()`, `updateTrackers()`, `executeTasks()`, `updateAuxiliarySystems()`
- Added comprehensive JavaDoc documentation throughout
- Fixed TODO comment regarding `_storageTracker.setDirty()`
- Organized code into clear sections with dividers
- Improved naming consistency and readability

## Identified Refactoring Opportunities

### 🔲 TaskCatalogue.java (65KB - Massive Resource Registry)
**Current Issues:**
- Single massive static initialization block
- Mixed concerns (mining, crafting, smelting, mobs, furniture, tools)
- Hard to navigate and maintain
**Proposed Solution:**
- Split into logical sub-modules:
  - `ResourceRegistry.java` - Core resource definitions
  - `MiningTasks.java` - All mining registrations
  - `CraftingTasks.java` - All crafting/recipe registrations
  - `MobTasks.java` - All mob drop registrations
  - `SmeltingTasks.java` - All smelting registrations
  - `WoodTasks.java` - Wood-type specific task generators
- Create a factory pattern for task generation
- Add type-safe resource identifiers

### 🔲 Settings.java (20KB - Configuration)
**Current Issues:**
- Many configuration fields without clear grouping
- Mixed validation and storage logic
**Proposed Solution:**
- Split into configuration categories:
  - `GeneralSettings.java`
  - `MiningSettings.java`
  - `CombatSettings.java`
  - `AutomationSettings.java`
- Implement builder pattern for settings creation
- Add validation methods to each category

### 🔲 Util/Helpers Directory
**Current Issues:**
- `ItemHelper.java` (31KB) - Too many responsibilities
- `StorageHelper.java` (26KB) - Complex storage logic
- `WorldHelper.java` (16KB) - Multiple game world concerns
**Proposed Solution:**
- Split `ItemHelper.java` into:
  - `ItemRegistry.java` - Item constants and mappings
  - `ItemValidator.java` - Item availability checks
  - `ItemUtils.java` - Utility methods
- Split `StorageHelper.java` into:
  - `ContainerManager.java` - Container operations
  - `InventoryTracker.java` - Inventory state tracking
- Create dedicated helper packages by domain

### 🔲 Chains Directory
**Current Issues:**
- Task chains tightly coupled to AltoClef
- Repetitive pattern across chains
**Proposed Solution:**
- Create abstract `TaskChain<TContext>` base class
- Extract common chain operations to `ChainManager.java`
- Implement dependency injection for chain dependencies
- Add unit testable interfaces

### 🔲 Tasks Directory
**Current Issues:**
- Inconsistent task implementations
- Duplicated logic across similar tasks
**Proposed Solution:**
- Create `AbstractTask` base class with common functionality
- Implement strategy pattern for task behaviors
- Extract common mining/crafting logic to `TaskStrategies.java`
- Add task lifecycle management

### 🔲 Trackers Directory
**Current Issues:**
- Tracker pattern inconsistent
- Mixed update logic
**Proposed Solution:**
- Standardize `ITracker` interface
- Create `TrackerRegistry` for unified management
- Implement event-driven updates instead of polling

## Architecture Goals

1. **Single Responsibility Principle:** Each class should have one clear purpose
2. **Dependency Inversion:** High-level modules shouldn't depend on low-level details
3. **Open/Closed Principle:** Extend behavior without modifying existing code
4. **Interface Segregation:** Small, focused interfaces
5. **Separation of Concerns:** Clear boundaries between UI, logic, and data

## Testing Strategy

- Implement unit tests for utility methods
- Create integration tests for task chains
- Add mock interfaces for Minecraft dependencies
- Target 70% code coverage for core logic

## Migration Path

1. Phase 1: Complete AltoClef.java refactoring (✅ Done)
2. Phase 2: Split TaskCatalogue.java into logical modules
3. Phase 3: Refactor Settings.java into categories
4. Phase 4: Split util/helpers into domain-specific packages
5. Phase 5: Standardize chains and tasks architecture
6. Phase 6: Add comprehensive test suite
7. Phase 7: Implement advanced architectural patterns

## Contributing Guidelines

- Each refactoring should maintain backward compatibility
- Add JavaDoc to all public methods
- Follow existing naming conventions
- Include test coverage for new/refactored code
- Document breaking changes in commit messages

---

*Last Updated: 2026-05-14*
*Status: In Progress*
