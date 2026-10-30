#!/bin/bash
#*******************************************************************************
# Copyright (c) 2019, 2026 IBM Corporation and others.
#
# This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
#
# SPDX-License-Identifier: EPL-2.0
#
# Contributors:
#     Sravan Kumar Lakkimsetti - initial API and implementation
#     Jonah Graham - adapted for the EPP project (used https://git.eclipse.org/c/platform/eclipse.platform.releng.aggregator.git/tree/cje-production/scripts/common-functions.shsource?id=8866cc6db76d777751acb56456b248708dd80eda#n47 as source)
#*
set -x # echo all commands used for debugging purposes


##
# Notatize a single file passed as an argument. Uses current directory as a temporary directory

DMG_FILE="$1"
DMG="$(basename "${DMG_FILE}")"
cp "${DMG_FILE}"-tonotarize "${DMG}"

# Prior to Mac M1 the primar bundle ID used was the name of the package with platform info stripped.
# However, the ID seems to be allowed to be arbitrary, therefore use the full file name so that
# aarch an x86_64 make the id unique. Except that it appears that _ is not permitted.
# See https://developer.apple.com/forums/thread/120421
PRIMARY_BUNDLE_ID="$(echo ${DMG} | sed  's/_/-/g')"

retryCount=1
while [ ${retryCount} -gt 0 ]; do

  RESPONSE_RAW=$(curl  --write-out "\n%{http_code}" -s -X POST -F file=@${DMG} -F 'options={"primaryBundleId": "'${PRIMARY_BUNDLE_ID}'", "staple": true};type=application/json' https://cbi.eclipse.org/macos/xcrun/notarize)
  RESPONSE=$(head -n1 <<<"${RESPONSE_RAW}")
  STATUS_CODE=$(tail -n1 <<<"${RESPONSE_RAW}")
  UUID="$(echo "${RESPONSE}" | jq -r '.uuid')"
  STATUS="$(echo "${RESPONSE}" | jq -r '.notarizationStatus.status')"

  if [[ ${STATUS_CODE} == '503' || ${STATUS_CODE} == '502' ]]; then
    echo Initial upload failed, Retrying
  else
    while [[ ${STATUS} == 'IN_PROGRESS' || ${STATUS_CODE} == '503' || ${STATUS_CODE} == '502' ]]; do
      sleep 1m
      RESPONSE_RAW=$(curl  --write-out "\n%{http_code}" -s https://cbi.eclipse.org/macos/xcrun/${UUID}/status)
      RESPONSE=$(head -n1 <<<"${RESPONSE_RAW}")
      STATUS_CODE=$(tail -n1 <<<"${RESPONSE_RAW}")
      STATUS=$(echo ${RESPONSE} | jq -r '.notarizationStatus.status')
    done
  fi

  if [[ ${STATUS} != 'COMPLETE' ]]; then
    echo "Notarization failed: ${RESPONSE}"
    retryCount=$(expr $retryCount - 1)
    if [ $retryCount -eq 0 ]; then
      echo "Notarization failed. Exiting"
      exit 1
    else
      echo "Retrying..."
    fi
  else
    break
  fi

done

rm "${DMG}"
curl -JO https://cbi.eclipse.org/macos/xcrun/${UUID}/download
cp -vf "${DMG}" "${DMG_FILE}"

# Generate the checksums in the directory that the files are in
d=$(dirname ${DMG_FILE})
b=$(basename ${DMG_FILE})
cd "$d"
md5sum "${b}" >"${b}".md5
sha1sum "${b}" >"${b}".sha1
sha512sum -b "${b}" >"${b}".sha512
rm "${b}"-tonotarize
rm -f "${b}"-tonotarize.md5
rm -f "${b}"-tonotarize.sha1
rm -f "${b}"-tonotarize.sha512
