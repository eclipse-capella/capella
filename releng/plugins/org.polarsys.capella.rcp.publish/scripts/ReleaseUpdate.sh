#!/bin/sh
# =============================================================================
# Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

# Get folder path contain this script
BASEDIR=$(dirname $0)
# Get script name
SCRIPT_NAME=$(basename $0)

# Get script parameter
# Verify parameter number => should be 6 
if [ "$#" -lt 5 ]
then
>&2 echo "ERREUR ${scriptName} : This script should take 5 parameters to run it."
>&2 echo "Command : ${scriptName} componentName targetManifest buildType buildTypePrefix buildVersion"
	exit 95
fi

export COMPONENT_NAME=$1
export TARGET_MANIFEST_NAME=$2
export TARGET_MANIFEST_FOLDER=$3
export BUILD_TYPE=$4
export BUILD_TYPE_PREFIX=$5
export BUILD_VERSION=$6

######################################################################
# Setup
######################################################################

# Exit on error
set -e


# other parameters are defined in global-parameters.sh script and launched before this script.

######################################################################
# Publish the build
######################################################################

# wget nightly manifest to release
# TARGET_BUILD_URL parameter provide by hudson

# Remove previous download
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 0 ReleaseUpdate.sh Remove previous download : rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME"
	echo ">>> Run cmd 0 ReleaseUpdate.sh Remove previous download : rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME*"
fi
rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME
# remove all download history
rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME.*
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdate.sh start : wget \"$TARGET_BUILD_URL\"artifact/\"$TARGET_MANIFEST_FOLDER/$TARGET_MANIFEST_NAME\""
fi
wget "$TARGET_BUILD_URL"artifact/"$TARGET_MANIFEST_FOLDER/$TARGET_MANIFEST_NAME"
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdate.sh end"
fi
# Get path to copy from manifest file
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseUpdate.sh start : UPDATE_FOLDER_TO_RELEASE=\$\(cat $WORKSPACE/$TARGET_MANIFEST_NAME | grep \"$US_PATH_PATERN\" | sed \"s/$US_PATH_PATERN//g\")"
fi
US_PATH_PATERN="Update nightly path : "
UPDATE_FOLDER_TO_RELEASE=$(cat $WORKSPACE/$TARGET_MANIFEST_NAME | grep "$US_PATH_PATERN" | sed "s/$US_PATH_PATERN//g")
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseUpdate.sh end : UPDATE_FOLDER_TO_RELEASE=$UPDATE_FOLDER_TO_RELEASE"
fi
# if $BUILD_TYPE = stable and $BUILD_VERSION is null or empty
# then set $BUILD_VERSION to nigthly build name with S in place of N
if [ "$BUILD_TYPE" = "stable" ]; then
	if [ "$BUILD_VERSION" = "" ]; then
		# Replace N by S : for instance 1.1.0-N20170529-151714 to 1.1.0-S20170529-151714
		if [ "$LOG" = "true" ]; then
			echo ">>> Run cmd 3 ReleaseUpdate.sh start : BUILD_VERSION=\$\(basename $UPDATE_FOLDER_TO_RELEASE | sed s/-N2/-S2/g\)"
		fi
		BUILD_VERSION=$(basename $UPDATE_FOLDER_TO_RELEASE | sed s/-N2/-S2/g)
		if [ "$LOG" = "true" ]; then
			echo ">>> Run cmd 3 ReleaseUpdate.sh end : BUILD_TYPE=stable and BUILD_VERSION is empty : BUILD_VERSION=$BUILD_VERSION"
		fi
	fi
fi

# Ensure the target folder exists
mkdir -p "$TARGET_DIR_RELEASE"
# The actual publication of the p2 repo produced by the build
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 4 ReleaseUpdate.sh start : cp -dR \"$UPDATE_FOLDER_TO_RELEASE\" \"$TARGET_DIR_RELEASE$BUILD_VERSION\""
fi
if [ -d $UPDATE_FOLDER_TO_RELEASE ]; then
	if [ -d $TARGET_DIR_RELEASE$BUILD_VERSION ]; then
		echo "The nightly build $UPDATE_FOLDER_TO_RELEASE is already release in $TARGET_DIR_RELEASE$BUILD_VERSION."
	else
		cp -dR "$UPDATE_FOLDER_TO_RELEASE" "$TARGET_DIR_RELEASE$BUILD_VERSION"
	fi
else
	echo "The nightly build $UPDATE_FOLDER_TO_RELEASE does not exist longer. It was probably erase previously."
fi
if [ "$LOG" = "true" ]; then
	echo "Run cmd 4 ReleaseUpdate.sh end"
fi


# Copy manifest to published update

# Manifest file
MANIFEST_NAME="ManifestRelease$COMPONENT_NAME.txt"
MANIFEST_FILE="$WORKSPACE/$MANIFEST_NAME"
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 5 ReleaseUpdate.sh start : $MANIFEST_NAME creation"
fi
echo "<<<ORGINE MANIFEST<<<" > $MANIFEST_FILE
cat "$WORKSPACE/$TARGET_MANIFEST_NAME" >>  $MANIFEST_FILE
echo "<<<ORGINE MANIFEST<<<" >> $MANIFEST_FILE
echo "" >> $MANIFEST_FILE
echo "<<< RELEASE MANIFEST<<<"  >> $MANIFEST_FILE
echo "Update release path : $TARGET_DIR_RELEASE" >>  $MANIFEST_FILE
echo "Update release url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$BUILD_VERSION" >>  $MANIFEST_FILE
echo "Update release manifest url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$BUILD_VERSION/$MANIFEST_NAME" >>  $MANIFEST_FILE
echo ">>> RELEASE MANIFEST>>>"  >> $MANIFEST_FILE
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 5 ReleaseUpdate.sh end"
fi


# Copy manifest to published update
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 6 ReleaseUpdate.sh start : cp -dR $MANIFEST_FILE $TARGET_DIR_RELEASE$BUILD_VERSION"
fi
cp -dR $MANIFEST_FILE $TARGET_DIR_RELEASE$BUILD_VERSION
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 6 end ReleaseUpdate.sh"
fi

echo "Release this night build '$UPDATE_FOLDER_TO_RELEASE' to publish folder'$TARGET_DIR_RELEASE$BUILD_VERSION'" 
echo "		Url release : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$BUILD_VERSION"
