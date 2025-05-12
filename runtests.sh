#!/bin/bash

# Test Runner for Toolbag of Holding

# Set Java path if needed
export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"

echo "Preparing build directories..."
mkdir -p build/classes build/test-classes

echo "Compiling main source files..."
javac -d build/classes src/main/java/*.java

if [ $? -ne 0 ]; then
    echo "Main source compilation failed. Exiting."
    exit 1
fi

echo "Compiling test files..."
javac -cp build/classes:src/test/java -d build/test-classes src/test/java/*.java

if [ $? -ne 0 ]; then
    echo "Test compilation failed. Exiting."
    exit 1
fi

echo "Running ScannerUtil tests..."
echo "==========================="
java -cp build/classes:build/test-classes ScannerUtilTest

echo ""
echo "Running CharSheetManager tests..."
echo "================================"
java -cp build/classes:build/test-classes CharSheetManagerTest

echo ""
echo "All tests completed!"