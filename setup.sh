#!/bin/bash
path=$1
cd $path
cd src
javac -d ../build WebMon.java
echo "WebMon.java file compiled..."
cd ../build
echo "Executing..."
java WebMon ../
