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

1. Create the output directory:
   ```bash
   mkdir -p build/classes
   ```

2. Compile all Java files:
   ```bash
   javac -d build/classes src/main/java/*.java
   ```

3. Run the main application:
   ```bash
   java -cp build/classes dndToolbag
   ```

## Running Tests

To run the tests, use the included test script:

```bash
./runtests.sh
```

This will compile and run the test suite.

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
- Reorganized project directory structure
- Created HTML presentation for project showcase (see `docs/presentation.html`)

## Project Structure

```
toolbag-of-holding/
├── build/                  # Compiled output
│   ├── classes/            # Compiled application classes
│   └── test-classes/       # Compiled test classes
├── docs/                   # Documentation
│   ├── CLAUDE.md           # Claude setup and guidance
│   └── ...                 # Other documentation files
├── src/                    # Source code
│   ├── main/
│   │   └── java/           # Application source files
│   │       ├── dndToolbag.java         # Main entry point
│   │       ├── CharSheetManager.java   # Data persistence
│   │       ├── ScannerUtil.java        # Input handling
│   │       └── ...                     # Other classes
│   └── test/
│       └── java/           # Test source files
│           ├── ScannerUtilTest.java
│           └── ...         # Other test classes
├── README.md               # This file
├── run.sh                  # Script to compile and run the app
└── runtests.sh             # Script to run tests
```

## Core Components

- **dndToolbag.java** - Main entry point with menu system
- **CharSheetManager.java** - Handles reading/writing character data
- **ScannerUtil.java** - Centralized input handling
- **Utility Classes**:
  - **diceRoller.java** - Dice rolling functionality
  - **rollStats.java** - Character ability score generation
  - **hitPoints.java** - HP calculation and tracking
  - **inventory.java** - Item management
  - **charInfo.java** - Basic character information
  - **charNotes.java** - Character notes management
  - **rollToAttack.java** - Combat attack rolls and damage

## File Structure

Character data is stored in text files with the following sections:
- **Character Info**: Name and class details
- **Hit Points**: Character HP
- **Assigned Stats**: Ability scores and modifiers
- **Inventory**: List of items
- **Notes**: Character notes and details

Character sheets are automatically backed up before each save operation.

## Project Presentation

An interactive HTML presentation is included to showcase the project features and architecture. To view the presentation:

1. Open the presentation landing page:
   ```
   docs/presentation.html
   ```

2. Navigate through the slides using the sidebar or navigation buttons.

The presentation covers:
- Project introduction and architecture
- Technical deep dive on key components
- Code refactoring and improvements
- Character creation process
- Game mechanics implementation
- Human-AI collaboration process

Additional slides can be created following the template in `docs/presentation/SLIDES_TO_COMPLETE.md`.
