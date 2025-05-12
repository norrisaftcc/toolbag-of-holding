#!/bin/bash

# Toolbag of Holding Runner Script
echo "Compiling Toolbag of Holding..."
javac *.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting Toolbag of Holding..."
    echo "-----------------------------"
    java dndToolbag
else
    echo "Compilation failed. Please fix the errors before running again."
fi