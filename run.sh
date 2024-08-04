#!/bin/sh

# Application configurations
MAIN_CLASS="org.example.TxnRoutineApplication"
SRC_DIR="src"
BUILD_DIR="build"
# Create the build directory if it doesn't exist
mkdir -p $BUILD_DIR

echo "Building gradle project..."
chmod +x ./gradlew
./gradlew clean build

#javac -d $BUILD_DIR -cp "$LIB_DIR/*" $SRC_DIR/org/example/TxnRoutineApplication.java

echo "Permission..."
chmod -R +x $BUILD_DIR
echo "running application..."
java -jar $BUILD_DIR/**/*SNAPSHOT.jar
