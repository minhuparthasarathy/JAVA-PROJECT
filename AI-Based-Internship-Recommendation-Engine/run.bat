@echo off
setlocal enabledelayedexpansion

echo ==========================================
echo AI-Based Internship Recommendation Engine
echo ==========================================
echo.

if exist out rmdir /s /q out
mkdir out

set "FILES="

for /r %%f in (*.java) do (
    set "FILES=!FILES! "%%f""
)

echo Compiling project...
javac -d out %FILES%

if errorlevel 1 (
    echo.
    echo Compilation failed.
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo Starting application...
echo.

java -cp out Main

pause