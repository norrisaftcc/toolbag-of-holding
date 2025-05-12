#!/bin/bash

# Toolbag of Holding Runner Script

# Set Java path if needed
export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"

echo "Compiling Toolbag of Holding..."

# Create output directory if it doesn't exist
mkdir -p build/classes

# Compile all Java files from src/main/java to build/classes
javac -d build/classes src/main/java/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting Toolbag of Holding..."
    echo "-----------------------------"

    # Run the application from the build directory
    java -cp build/classes dndToolbag
else
    echo "Compilation failed. Please fix the errors before running again."
fi