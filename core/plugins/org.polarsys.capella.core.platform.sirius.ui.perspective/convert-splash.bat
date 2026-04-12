@echo off
setlocal EnableDelayedExpansion
chcp 65001 > nul

set "SCRIPT_DIR=%~dp0"
set "DEFAULT_SVG=%SCRIPT_DIR%splash.svg"

set "SVG_PATH="
set "BMP_PATH="
set "REFERENCE_BMP="
set "VECTORIZED_SVG_PATH="
set "PNG_EXPORT_PATH="

set "REQUIRED_FONT_FAMILY=Lato"

:parse_args
if "%~1"=="" goto end_args
if "%~1"=="--svg" ( set "SVG_PATH=%~2" & shift & shift & goto parse_args )
if "%~1"=="--bmp" ( set "BMP_PATH=%~2" & shift & shift & goto parse_args )
if "%~1"=="--reference-bmp" ( set "REFERENCE_BMP=%~2" & shift & shift & goto parse_args )
if "%~1"=="--vectorized-svg" ( set "VECTORIZED_SVG_PATH=%~2" & shift & shift & goto parse_args )
if "%~1"=="--png" ( set "PNG_EXPORT_PATH=%~2" & shift & shift & goto parse_args )
if "%~1"=="-h" goto usage
if "%~1"=="--help" goto usage
echo Unknown argument: %1 >&2
goto usage

:usage
echo Usage: convert-splash.bat [options]
echo.
echo Render splash.svg into an Eclipse-compatible splash.bmp.
echo.
echo Options:
echo   --svg ^<path^>              Source SVG path (Default: ./splash.svg)
echo   --bmp ^<path^>              Output BMP path (Default: same directory as SVG)
echo   --reference-bmp ^<path^>    Existing BMP used to derive the target format (Default: same directory as SVG)
echo   --vectorized-svg ^<path^>   (Optional) Path to save the intermediate SVG with text converted to paths
echo   --png ^<path^>              (Optional) Path to save the intermediate rendered PNG
echo   -h, --help                Show this help
echo.
echo The output format is derived from the existing splash.bmp so the generated
echo bitmap stays aligned with the BMP variant currently shipped by the plugin.
echo This script also fails if fontconfig cannot resolve the required Lato
echo styles without substitution.
exit /b 2

:end_args

if "%SVG_PATH%"=="" set "SVG_PATH=%DEFAULT_SVG%"
for %%I in ("%SVG_PATH%") do set "SVG_DIR=%%~dpI"
if "%BMP_PATH%"=="" set "BMP_PATH=%SVG_DIR%splash.bmp"
if "%REFERENCE_BMP%"=="" set "REFERENCE_BMP=%SVG_DIR%splash.bmp"

for %%C in (inkscape magick) do (
  where %%C >nul 2>&1 || (
    echo Missing required command: %%C >&2
    exit /b 2
  )
)

if not exist "%SVG_PATH%" (
  echo SVG not found: %SVG_PATH% >&2
  exit /b 2
)

if not exist "%REFERENCE_BMP%" (
  echo Reference BMP not found: %REFERENCE_BMP% >&2
  exit /b 2
)

for /f "tokens=1-6 delims=|" %%A in ('magick identify -format "%%m|%%w|%%h|%%x|%%y|%%U" "%REFERENCE_BMP%"') do (
  set "REFERENCE_FORMAT=%%A"
  set "TARGET_WIDTH=%%B"
  set "TARGET_HEIGHT=%%C"
  set "TARGET_DENSITY_X=%%D"
  set "TARGET_DENSITY_Y=%%E"
  set "TARGET_UNITS=%%F"
)

if "%REFERENCE_FORMAT%" NEQ "BMP3" (
  echo Unsupported reference format: %REFERENCE_FORMAT% ^(expected BMP3^) >&2
  exit /b 1
)

if "%TARGET_UNITS%" NEQ "PixelsPerCentimeter" (
  echo Unsupported reference density units: %TARGET_UNITS% >&2
  exit /b 1
)

magick identify -verbose "%REFERENCE_BMP%" | findstr /I "Type: TrueColor" >nul
if %ERRORLEVEL% neq 0 (
  echo Reference BMP is not 24-bit: %REFERENCE_BMP% >&2
  exit /b 1
)

set "FONT_FOUND=0"
for %%H in (HKLM HKCU) do (
  reg query "%%H\Software\Microsoft\Windows NT\CurrentVersion\Fonts" /s 2>nul | findstr /I "%REQUIRED_FONT_FAMILY%" >nul
  if !ERRORLEVEL! EQU 0 set "FONT_FOUND=1"
)

if "%FONT_FOUND%"=="0" (
  echo Missing required font %REQUIRED_FONT_FAMILY%; fontconfig would substitute... >&2
  exit /b 1
)

set "TMP_DIR=%TEMP%\splash_tmp_%RANDOM%"
mkdir "%TMP_DIR%"
set "TMP_VECTOR_SVG=%TMP_DIR%\vectorized.svg"
set "TMP_PNG=%TMP_DIR%\splash.png"
set "TMP_VERBOSE=%TMP_DIR%\verbose.txt"

:: Step 1: object-to-path
inkscape "%SVG_PATH%" ^
  --export-text-to-path ^
  --export-plain-svg ^
  --export-filename="%TMP_VECTOR_SVG%" ^
  >nul 2>&1

:: Step 2: if --vectorized-svg, copy the intermediate file
if "%VECTORIZED_SVG_PATH%" NEQ "" (
  if not exist "%VECTORIZED_SVG_PATH%\.." mkdir "%VECTORIZED_SVG_PATH%\.." 2>nul
  copy /y "%TMP_VECTOR_SVG%" "%VECTORIZED_SVG_PATH%" >nul
  echo Exported vectorized SVG to %VECTORIZED_SVG_PATH%
)

:: Step 3: generate .png from the vectorized .svg
inkscape "%TMP_VECTOR_SVG%" ^
  --export-type=png ^
  --export-filename="%TMP_PNG%" ^
  --export-width="%TARGET_WIDTH%" ^
  --export-height="%TARGET_HEIGHT%" ^
  >nul 2>&1

:: Step 4: export the .png output
if "%PNG_EXPORT_PATH%" NEQ "" (
  if not exist "%PNG_EXPORT_PATH%\.." mkdir "%PNG_EXPORT_PATH%\.." 2>nul
  copy /y "%TMP_PNG%" "%PNG_EXPORT_PATH%" >nul
  echo Exported intermediate PNG to %PNG_EXPORT_PATH%
)

if not exist "%BMP_PATH%\.." mkdir "%BMP_PATH%\.." 2>nul

:: Step 5: generate the .bmp output
magick "%TMP_PNG%" ^
  -background white ^
  -alpha remove ^
  -alpha off ^
  -type TrueColor ^
  -depth 8 ^
  -compress none ^
  -units PixelsPerCentimeter ^
  -density "%TARGET_DENSITY_X%x%TARGET_DENSITY_Y%" ^
  "BMP3:%BMP_PATH%"

for /f "tokens=1-6 delims=|" %%A in ('magick identify -format "%%m|%%w|%%h|%%x|%%y|%%U" "%BMP_PATH%"') do (
  set "OUTPUT_FORMAT=%%A"
  set "OUTPUT_WIDTH=%%B"
  set "OUTPUT_HEIGHT=%%C"
  set "OUTPUT_DENSITY_X=%%D"
  set "OUTPUT_DENSITY_Y=%%E"
  set "OUTPUT_UNITS=%%F"
)

magick identify -verbose "%BMP_PATH%" > "%TMP_VERBOSE%"

if "%OUTPUT_FORMAT%" NEQ "BMP3" (
  echo Unexpected output geometry/format: %OUTPUT_FORMAT% %OUTPUT_WIDTH%x%OUTPUT_HEIGHT% >&2
  goto cleanup_err
)
if "%OUTPUT_WIDTH%" NEQ "%TARGET_WIDTH%" (
  echo Unexpected output geometry/format: %OUTPUT_FORMAT% %OUTPUT_WIDTH%x%OUTPUT_HEIGHT% >&2
  goto cleanup_err
)
if "%OUTPUT_HEIGHT%" NEQ "%TARGET_HEIGHT%" (
  echo Unexpected output geometry/format: %OUTPUT_FORMAT% %OUTPUT_WIDTH%x%OUTPUT_HEIGHT% >&2
  goto cleanup_err
)

if "%OUTPUT_UNITS%" NEQ "%TARGET_UNITS%" (
  echo Unexpected output density units: %OUTPUT_UNITS% >&2
  goto cleanup_err
)

findstr /I "Type: TrueColor" "%TMP_VERBOSE%" >nul
if %ERRORLEVEL% neq 0 (
  echo Output BMP is not 24-bit: %BMP_PATH% >&2
  goto cleanup_err
)

findstr /I "Compression: None" "%TMP_VERBOSE%" >nul
if %ERRORLEVEL% neq 0 (
  echo Output BMP is compressed unexpectedly. >&2
  goto cleanup_err
)

findstr /I "Type: TrueColor" "%TMP_VERBOSE%" >nul
if %ERRORLEVEL% neq 0 (
  echo Output BMP is not truecolor unexpectedly. >&2
  goto cleanup_err
)

echo Wrote %BMP_PATH%
echo Reference : %REFERENCE_BMP% (BMP3 format, 24-bit)
echo Output    : %BMP_PATH% (BMP3 format, 24-bit)

rmdir /s /q "%TMP_DIR%"
exit /b 0

:cleanup_err
rmdir /s /q "%TMP_DIR%"
exit /b 1