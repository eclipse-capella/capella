#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DEFAULT_SVG="${SCRIPT_DIR}/splash.svg"

SVG_PATH=""
BMP_PATH=""
REFERENCE_BMP=""
VECTORIZED_SVG_PATH=""
PNG_EXPORT_PATH=""

REQUIRED_FONT_FAMILY="Lato"
REQUIRED_FONT_STYLES=("Regular" "Light")

usage() {
  cat <<'EOF'
Usage: convert-splash.sh [options]

Render splash.svg into an Eclipse-compatible splash.bmp.

Options:
  --svg <path>              Source SVG path (Default: ./splash.svg)
  --bmp <path>              Output BMP path (Default: same directory as SVG)
  --reference-bmp <path>    Existing BMP used to derive the target format (Default: same directory as SVG)
  --vectorized-svg <path>   (Optional) Path to save the intermediate SVG with text converted to paths
  --png <path>              (Optional) Path to save the intermediate rendered PNG
  -h, --help                Show this help

The output format is derived from the existing splash.bmp so the generated
bitmap stays aligned with the BMP variant currently shipped by the plugin.
The script also fails if fontconfig cannot resolve the required Lato
styles without substitution.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --svg)
      SVG_PATH="$2"
      shift 2
      ;;
    --bmp)
      BMP_PATH="$2"
      shift 2
      ;;
    --reference-bmp)
      REFERENCE_BMP="$2"
      shift 2
      ;;
    --vectorized-svg)
      VECTORIZED_SVG_PATH="$2"
      shift 2
      ;;
    --png)
      PNG_EXPORT_PATH="$2"
      shift 2
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown argument: $1" >&2
      usage >&2
      exit 2
      ;;
  esac
done

SVG_PATH="${SVG_PATH:-$DEFAULT_SVG}"
SVG_DIR="$(dirname "${SVG_PATH}")"
BMP_PATH="${BMP_PATH:-$SVG_DIR/splash.bmp}"
REFERENCE_BMP="${REFERENCE_BMP:-$SVG_DIR/splash.bmp}"

for cmd in inkscape magick identify file mktemp fc-match; do
  command -v "${cmd}" >/dev/null 2>&1 || {
    echo "Missing required command: ${cmd}" >&2
    exit 2
  }
done

[[ -f "${SVG_PATH}" ]] || {
  echo "SVG not found: ${SVG_PATH}" >&2
  exit 2
}

[[ -f "${REFERENCE_BMP}" ]] || {
  echo "Reference BMP not found: ${REFERENCE_BMP}" >&2
  exit 2
}

IFS='|' read -r REFERENCE_FORMAT TARGET_WIDTH TARGET_HEIGHT TARGET_DENSITY_X TARGET_DENSITY_Y TARGET_UNITS < <(
  identify -format '%m|%w|%h|%x|%y|%U\n' "${REFERENCE_BMP}"
)
REFERENCE_FILE_DESCRIPTION="$(file "${REFERENCE_BMP}")"

if [[ "${REFERENCE_FORMAT}" != "BMP3" ]]; then
  echo "Unsupported reference format: ${REFERENCE_FORMAT} (expected BMP3)" >&2
  exit 1
fi

if [[ "${TARGET_UNITS}" != "PixelsPerCentimeter" ]]; then
  echo "Unsupported reference density units: ${TARGET_UNITS}" >&2
  exit 1
fi

if [[ ! "${REFERENCE_FILE_DESCRIPTION}" =~ Windows\ 3\.x\ format ]]; then
  echo "Reference BMP is not a Windows 3.x BMP: ${REFERENCE_FILE_DESCRIPTION}" >&2
  exit 1
fi

if [[ ! "${REFERENCE_FILE_DESCRIPTION}" =~ x\ 24, ]]; then
  echo "Reference BMP is not 24-bit: ${REFERENCE_FILE_DESCRIPTION}" >&2
  exit 1
fi

check_required_font() {
  local family="$1"
  local style="$2"
  local resolved_family
  local resolved_style
  local resolved_file

  IFS='|' read -r resolved_family resolved_style resolved_file < <(
    fc-match -f '%{family[0]}|%{style[0]}|%{file}\n' "${family}:style=${style}"
  )

  if [[ -z "${resolved_family}" || -z "${resolved_file}" ]]; then
    echo "Unable to resolve required font ${family} (${style})." >&2
    exit 1
  fi

  if [[ "${resolved_family}" != "${family}" ]]; then
    echo "Missing required font ${family} (${style}); fontconfig would substitute ${resolved_family} (${resolved_style}) from ${resolved_file}." >&2
    exit 1
  fi
}

for style in "${REQUIRED_FONT_STYLES[@]}"; do
  check_required_font "${REQUIRED_FONT_FAMILY}" "${style}"
done

TMP_DIR="$(mktemp -d)"
cleanup() {
  rm -rf "${TMP_DIR}"
}
trap cleanup EXIT

TMP_VECTOR_SVG="${TMP_DIR}/vectorized.svg"
TMP_PNG="${TMP_DIR}/splash.png"

# Step 1: object-to-path
inkscape "${SVG_PATH}" \
  --export-text-to-path \
  --export-plain-svg \
  --export-filename="${TMP_VECTOR_SVG}" \
  >/dev/null

# Step 2: if --vectorized-svg, copy the intermediate file
if [[ -n "${VECTORIZED_SVG_PATH}" ]]; then
  mkdir -p "$(dirname "${VECTORIZED_SVG_PATH}")"
  cp "${TMP_VECTOR_SVG}" "${VECTORIZED_SVG_PATH}"
  echo "Exported vectorized SVG to ${VECTORIZED_SVG_PATH}"
fi

# Step 3: generate .png from the vectorized .svg
inkscape "${TMP_VECTOR_SVG}" \
  --export-type=png \
  --export-filename="${TMP_PNG}" \
  --export-width="${TARGET_WIDTH}" \
  --export-height="${TARGET_HEIGHT}" \
  >/dev/null

# Step 4: export the .png output
if [[ -n "${PNG_EXPORT_PATH}" ]]; then
  mkdir -p "$(dirname "${PNG_EXPORT_PATH}")"
  cp "${TMP_PNG}" "${PNG_EXPORT_PATH}"
  echo "Exported intermediate PNG to ${PNG_EXPORT_PATH}"
fi

mkdir -p "$(dirname "${BMP_PATH}")"

# Step 5: generate the .bmp output
magick "${TMP_PNG}" \
  -background white \
  -alpha remove \
  -alpha off \
  -type TrueColor \
  -depth 8 \
  -compress none \
  -units PixelsPerCentimeter \
  -density "${TARGET_DENSITY_X}x${TARGET_DENSITY_Y}" \
  "BMP3:${BMP_PATH}"

OUTPUT_FILE_DESCRIPTION="$(file "${BMP_PATH}")"
IFS='|' read -r OUTPUT_FORMAT OUTPUT_WIDTH OUTPUT_HEIGHT OUTPUT_DENSITY_X OUTPUT_DENSITY_Y OUTPUT_UNITS < <(
  identify -format '%m|%w|%h|%x|%y|%U\n' "${BMP_PATH}"
)
OUTPUT_VERBOSE="$(magick identify -verbose "${BMP_PATH}")"

if [[ "${OUTPUT_FORMAT}" != "BMP3" || "${OUTPUT_WIDTH}" != "${TARGET_WIDTH}" || "${OUTPUT_HEIGHT}" != "${TARGET_HEIGHT}" ]]; then
  echo "Unexpected output geometry/format: ${OUTPUT_FORMAT} ${OUTPUT_WIDTH}x${OUTPUT_HEIGHT}" >&2
  exit 1
fi

if [[ "${OUTPUT_UNITS}" != "${TARGET_UNITS}" ]]; then
  echo "Unexpected output density units: ${OUTPUT_UNITS}" >&2
  exit 1
fi

if [[ ! "${OUTPUT_FILE_DESCRIPTION}" =~ Windows\ 3\.x\ format ]]; then
  echo "Output BMP is not a Windows 3.x BMP: ${OUTPUT_FILE_DESCRIPTION}" >&2
  exit 1
fi

if [[ ! "${OUTPUT_FILE_DESCRIPTION}" =~ x\ 24, ]]; then
  echo "Output BMP is not 24-bit: ${OUTPUT_FILE_DESCRIPTION}" >&2
  exit 1
fi

if [[ "${OUTPUT_VERBOSE}" != *"Compression: None"* ]]; then
  echo "Output BMP is compressed unexpectedly." >&2
  exit 1
fi

if [[ "${OUTPUT_VERBOSE}" != *"Type: TrueColor"* ]]; then
  echo "Output BMP is not truecolor unexpectedly." >&2
  exit 1
fi

echo "Wrote ${BMP_PATH}"
echo "Reference : ${REFERENCE_FILE_DESCRIPTION}"
echo "Output    : ${OUTPUT_FILE_DESCRIPTION}"
