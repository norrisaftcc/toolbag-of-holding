# Toolbag of Holding

A Java-based console application for Dungeons & Dragons players to help with character creation, gameplay, and combat tracking.

## Features

- Character creation with stat rolling and assignment
- Hit point calculation based on class
- Inventory management system
- Dice rolling for various dice types (d4, d6, d8, d10, d12, d20, d100)
- Character information storage with persistent file-based saving
- Multiple character sheet support with backup functionality

## How to Run

### Using the Run Script (Recommended)

Simply run the provided shell script:

```bash
./run.sh
```

This will compile all the Java files and start the application.

### Manual Compilation and Execution

1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the main application:
   ```bash
   java dndToolbag
   ```

## Usage Guide

1. **Main Menu**:
   - Choose "Play" to use an existing character
   - Choose "Character Creation" to create a new character
   - Choose "Exit" to quit the application

2. **Character Creation**:
   - Enter basic character information
   - Roll and assign ability scores
   - Calculate hit points based on class
   - Add items to your inventory
   - Record character notes
   - When finished, you'll be taken to Play mode

3. **Play Mode**:
   - Roll dice for various checks
   - Manage your inventory
   - View your character sheet
   - Track hit points and other resources
   - Enter combat mode for battle encounters

4. **Combat Mode**:
   - Roll attack dice with modifiers
   - Calculate damage based on weapon dice
   - Track hit points
   - Manage spell usage (coming soon)

## Recent Updates

- Implemented centralized `ScannerUtil` class for input handling
- Fixed resource leaks with Scanner objects
- Improved error handling throughout the application
- Added input validation for all user interactions
- Refactored dice rolling code to use a generic approach
- Added JavaDoc documentation to major methods
- Added support for multiple character sheets
- Added automatic file backups before saving

## Project Structure

- `dndToolbag.java` - Main entry point with menu system
- `CharSheetManager.java` - Handles reading/writing character data
- `ScannerUtil.java` - Centralized input handling
- Utility classes for specific D&D mechanics:
  - `diceRoller.java` - Dice rolling functionality
  - `rollStats.java` - Character ability score generation
  - `hitPoints.java` - HP calculation and tracking
  - `inventory.java` - Item management
  - `charInfo.java` - Basic character information
  - `charNotes.java` - Character notes management
  - `rollToAttack.java` - Combat attack rolls and damage

## File Structure

Character data is stored in text files with the following sections:
- Character Info: Name and class details
- Hit Points: Character HP
- Assigned Stats: Ability scores and modifiers
- Inventory: List of items
- Notes: Character notes and details

Character sheets are automatically backed up before each save operation.
