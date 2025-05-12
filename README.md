# Toolbag of Holding

A Java-based console application for Dungeons & Dragons players to help with character creation, gameplay, and combat tracking.

## Features

- Character creation with stat rolling and assignment
- Hit point calculation based on class
- Inventory management system
- Dice rolling for various dice types (d4, d6, d8, d10, d12, d20, d100)
- Character information storage with persistent file-based saving

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the main application:
   ```
   java dndToolbag
   ```

## Recent Updates

- Implemented centralized `ScannerUtil` class for input handling
- Fixed resource leaks with Scanner objects
- Improved error handling throughout the application
- Added input validation for all user interactions
- Refactored dice rolling code to use a generic approach
- Added JavaDoc documentation to major methods

## Project Structure

- `dndToolbag.java` - Main entry point with menu system
- `CharSheetManager.java` - Handles reading/writing character data
- Utility classes for specific D&D mechanics:
  - `diceRoller.java` - Dice rolling functionality
  - `rollStats.java` - Character ability score generation
  - `hitPoints.java` - HP calculation and tracking
  - `inventory.java` - Item management
  - `charInfo.java` - Basic character information
  - `charNotes.java` - Character notes management
