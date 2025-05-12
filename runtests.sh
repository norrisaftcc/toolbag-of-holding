#!/bin/bash

# Test Runner for Toolbag of Holding
echo "Compiling main source files..."
javac *.java

if [ $? -ne 0 ]; then
    echo "Main source compilation failed. Exiting."
    exit 1
fi

echo "Compiling test files..."
javac -cp .:src/test/java src/test/java/*.java

if [ $? -ne 0 ]; then
    echo "Test compilation failed. Exiting."
    exit 1
fi

echo "Running ScannerUtil tests..."
echo "==========================="
java -cp .:src/test/java ScannerUtilTest

echo ""
echo "Running CharSheetManager tests..."
echo "================================"
java -cp .:src/test/java CharSheetManagerTest

echo ""
echo "All tests completed!"