# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

The Toolbag of Holding is a Java-based console application for Dungeons & Dragons players. It provides tools for character creation, gameplay, and combat tracking.

### Key Components

1. **dndToolbag.java**: Main entry point with three primary modes:
   - Play Mode: Access dice rolling, inventory, character sheets, and combat
   - Character Creation: Roll stats, calculate HP, manage inventory, and make notes
   - Combat Mode: Handle attacks, spells, and HP tracking

2. **CharSheetManager.java**: Handles reading/writing character data to a text file with sections for:
   - Character Info
   - Hit Points
   - Assigned Stats
   - Inventory
   - Notes

3. **Utility Classes**:
   - **diceRoller.java**: Handles various dice rolls (d4, d6, d8, d10, d12, d20, d100)
   - **rollStats.java**: Generates and assigns character ability scores
   - **hitPoints.java**: Calculates and tracks character hit points
   - **inventory.java**: Manages character inventory items
   - **charInfo.java**: Records basic character information
   - **charNotes.java**: Stores character notes

## Architecture

The application follows a simple modular design with:
- A main controller class (dndToolbag)
- A data manager (CharSheetManager)
- Specialized tool classes for different D&D mechanics

All character data is stored in a single text file (charSheet.txt) with predefined sections.

## Build and Run Instructions

To compile and run the project:

```bash
# Compile all Java files
javac *.java

# Run the application
java dndToolbag
```

## Development Guidance

When working on this codebase:

1. Each class handles a specific D&D mechanic and should maintain that separation of concerns.

2. The CharSheetManager class provides a centralized way to read/write character data - use it instead of creating additional file operations.

3. All user interface code is console-based with Scanner input and System.out output.

4. Classes are connected through static method calls, not object instantiation.

5. Changes should maintain the text-based UI approach and file-based data storage.

## TODO List

The following improvements are planned for this project:

1. **Unit Testing**:
   - Add JUnit tests for each utility class
   - Create test fixtures for CharSheetManager
   - Implement test cases for dice rolling accuracy
   - Add tests for character stat calculations

2. **Code Improvements**:
   - Complete the characterSheet.java implementation
   - Implement spell tracking functionality in combat mode
   - Address Scanner resource leaks (ensure proper closing)
   - Handle exceptions more gracefully
   - Add character leveling mechanics

3. **User Experience**:
   - Improve input validation
   - Add confirmation prompts for critical actions
   - Implement a help system
   - Add save/load functionality for multiple characters

4. **Documentation**:
   - Add JavaDoc comments to methods
   - Create user documentation
   - Document file format structure