#!/usr/bin/env bash
# Copyright (c) 2026 THALES GLOBAL SERVICES.
# SPDX-License-Identifier: EPL-2.0

set -euo pipefail

fail() {
    echo "macOS notarization: $*" >&2
    exit 1
}

[[ $# == 1 && -d $1 ]] || fail "usage: bash notarize.sh <products-directory>"
command -v curl >/dev/null || fail "curl is required"
command -v jq >/dev/null || fail "jq is required"

service_url=${CBI_NOTARIZATION_URL:-https://cbi.eclipse.org/macos/xcrun}
service_url=${service_url%/}
timeout_seconds=${NOTARIZATION_TIMEOUT_SECONDS:-2400}
poll_seconds=${NOTARIZATION_POLL_SECONDS:-30}
[[ $timeout_seconds =~ ^[1-9][0-9]*$ && $poll_seconds =~ ^[1-9][0-9]*$ ]] \
    || fail "timeouts and polling intervals must be positive integers"

# Require one current product for each architecture before submitting anything.
shopt -s nullglob
dmgs=()
for arch in x86_64 aarch64; do
    matches=("$1"/capella-*-macosx-cocoa-"$arch".dmg)
    [[ ${#matches[@]} == 1 && -s ${matches[0]} ]] \
        || fail "expected exactly one nonempty $arch DMG in $1"
    dmgs+=("${matches[0]}")
done

download=
trap 'if [[ -n $download ]]; then rm -f -- "$download"; fi' EXIT

# The deadline includes upload, polling and downloading the stapled artifact.
request() {
    local output=$1
    shift
    local remaining=$((deadline - SECONDS))
    ((remaining > 0)) || fail "timed out waiting for $name"
    curl --fail-with-body --silent --show-error --connect-timeout 30 \
        --max-time "$remaining" --output "$output" --write-out '%{http_code}\n' \
        "$@" > "$output.log" || fail "request failed; see $output.log and Jenkins console"
    [[ $(cat "$output.log") == 2[0-9][0-9] ]] || fail "unexpected HTTP response; see $output.log"
}

for dmg in "${dmgs[@]}"; do
    name=$(basename "$dmg")
    log_dir="$1/notarization/$name"
    mkdir -p "$log_dir"
    deadline=$((SECONDS + timeout_seconds))
    echo "Submitting $name for notarization"
    options=$(jq -n --arg bundle "${name//_/-}" '{primaryBundleId: $bundle, staple: true}')
    response="$log_dir/submit.json"
    request "$response" --form "file=@$dmg" \
        --form "options=$options;type=application/json" "$service_url/notarize"
    uuid=$(jq -er '.uuid | select(type == "string")' "$response")
    [[ $uuid =~ ^[[:xdigit:]]{8}-[[:xdigit:]]{4}-[[:xdigit:]]{4}-[[:xdigit:]]{4}-[[:xdigit:]]{12}$ ]] \
        || fail "invalid submission identifier; see $response"
    status=$(jq -er '.notarizationStatus.status | select(type == "string")' "$response")
    attempt=0
    while [[ $status == IN_PROGRESS ]]; do
        remaining=$((deadline - SECONDS))
        ((remaining > 0)) || fail "timed out waiting for $name; see $response"
        delay=$poll_seconds
        ((delay <= remaining)) || delay=$remaining
        sleep "$delay"
        attempt=$((attempt + 1))
        response="$log_dir/status-$attempt.json"
        request "$response" "$service_url/$uuid/status"
        status=$(jq -er '.notarizationStatus.status | select(type == "string")' "$response")
    done
    [[ $status == COMPLETE ]] || fail "$name returned $status; see $response"

    # Replace the original only after downloading the complete stapled result.
    download="$dmg.notarized.tmp"
    request "$download" "$service_url/$uuid/download"
    mv "$download.log" "$log_dir/download.log"
    [[ -s $download ]] || fail "empty notarized download for $name"
    mv -f -- "$download" "$dmg"
    download=
    echo "Notarized and stapled $name"
done
