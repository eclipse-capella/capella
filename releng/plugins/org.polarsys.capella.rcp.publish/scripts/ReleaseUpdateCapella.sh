#!/bin/sh
# =============================================================================
# Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
# 
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License 2.0 which is available at
# http://www.eclipse.org/legal/epl-2.0
# 
# SPDX-License-Identifier: EPL-2.0
# 
# Contributors:
#      Obeo - initial implementation retrieved from Sirius
#    Thales - adaptation for Capella
# =============================================================================
# ====================================================================
#
# This script Publish update site from runtime core build to
# http://download.eclipse.org/capella/core/updates
#
# ====================================================================


[ -z "$WORKSPACE" ] && {
     echo "Execution aborted.


The required variable WORKSPACE is not set. They are normally
provided by the Hudson build.
"
    exit 1
}

# Component name
export COMPONENT_NAME="capella"
# Update project name
export UPDATE_PRJ_NAME="org.polarsys.kitalpha.sdk.updatesite"
# Target update project path
export UPDATE_PATH="releng/$UPDATE_PRJ_NAME"


# Achived Manifest Name
TARGET_MANIFEST_NAME="Manifest$COMPONENT_NAME.txt"
# Extract Achived manifest file.
TARGET_MANIFEST_FOLDER="$UPDATE_PATH/target"

# Get folder path contain this script
BASEDIR=$(dirname $0)
# Get script name
SCRIPT_NAME=$(basename $0)

# Extract global and release parameters (ie VERSION)
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdateSdk.sh start : . $BASEDIR/utils/global-parameters.sh $BUILD_TYPE"
fi
# Extract global parameters (ie Publish path)
. $BASEDIR/utils/global-parameters.sh $BUILD_TYPE
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdateSdk.sh start : . $BASEDIR/utils/release-parameters.sh $PRODUCT_NAME $COMPONENT_NAME $BUILD_TYPE"
fi
# Extract global parameters (ie VERSION)
. $BASEDIR/utils/release-parameters.sh $COMPONENT_NAME $BUILD_TYPE
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdateSdk.sh end"
fi

# Run release publish for this component
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseUpdateSdk.sh start : . $BASEDIR/ReleaseUpdate.sh $COMPONENT_NAME $TARGET_MANIFEST_NAME $TARGET_MANIFEST_FOLDER $BUILD_TYPE $BUILD_TYPE_PREFIX $BUILD_VERSION"
fi
. $BASEDIR/ReleaseUpdate.sh $COMPONENT_NAME $TARGET_MANIFEST_NAME $TARGET_MANIFEST_FOLDER $BUILD_TYPE $BUILD_TYPE_PREFIX $BUILD_VERSION
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseUpdateSdk.sh end"
fi
