@echo off
mkdir build
if exist sources.txt del /f sources.txt
dir /s /B *.java > sources.txt
javac -d ./build/ @sources.txt
echo Main-Class: main.program.KaijieSolution > manifest.txt
jar cvfm KaijieSolution.jar manifest.txt -C build .
