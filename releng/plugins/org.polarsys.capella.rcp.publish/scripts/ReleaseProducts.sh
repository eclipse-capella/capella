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

COMPONENT_NAME="products"
# Product project name
PRODUCT_PRJ_NAME="org.polarsys.kitalpha.releng.product"

# Get folder path contain this script
BASEDIR=$(dirname $0)

# Extract global parameter (ie VERSION)
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseProducts.sh start : . $BASEDIR/utils/global-parameters.sh $BUILD_TYPE"
	echo ">>> Run cmd 1 ReleaseProducts.sh start : . $BASEDIR/utils/release-parameters.sh $COMPONENT_NAME $BUILD_TYPE"
fi
# Extract global parameter (ie Publish path)
. $BASEDIR/utils/global-parameters.sh $BUILD_TYPE
# Extract global parameter (ie VERSION)
. $BASEDIR/utils/release-parameters.sh $PRODUCT_NAME $COMPONENT_NAME $BUILD_TYPE
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 1 ReleaseUpdateRuntime.sh end"
fi

# Manifest file
TARGET_MANIFEST_NAME="Manifest$COMPONENT_NAME.txt"
TARGET_MANIFEST_FOLDER="releng/$PRODUCT_PRJ_NAME/target"

######################################################################
# Setup
######################################################################

# Exit on error
set -e


# other parameters are defined in global-parameters.sh script and launched before this script.

######################################################################
# Publish the build
######################################################################

# wget nigthly manifest to release
# TARGET_BUILD_URL parameter provide by hudson

# Remove previous download
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseProducts.sh Remove previous download : rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME"
	echo ">>> Run cmd 2 ReleaseProducts.sh Remove previous download : rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME*"
fi
rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME
# remove all download history
rm -rf $WORKSPACE/$TARGET_MANIFEST_NAME.*

if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseProducts.sh start : wget \"$TARGET_BUILD_URL\"artifact/\"$TARGET_MANIFEST_FOLDER/$TARGET_MANIFEST_NAME\""
fi
wget "$TARGET_BUILD_URL"artifact/"$TARGET_MANIFEST_FOLDER/$TARGET_MANIFEST_NAME"
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 2 ReleaseProducts.sh end."
fi

# Get path to copy from manifest file
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 3 ReleaseProducts.sh start : PRODUCT_FOLDER_TO_RELEASE=\$\(cat $WORKSPACE/$TARGET_MANIFEST_NAME | grep \"$US_PATH_PATERN\" | sed \"s/$US_PATH_PATERN//g\"\)"
fi
US_PATH_PATERN="Products nightly path : "
PRODUCT_FOLDER_TO_RELEASE=$(cat $WORKSPACE/$TARGET_MANIFEST_NAME | grep "$US_PATH_PATERN" | sed "s/$US_PATH_PATERN//g")
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 3 ReleaseProducts.sh end : PRODUCT_FOLDER_TO_RELEASE=$PRODUCT_FOLDER_TO_RELEASE"
fi
# if $BUILD_TYPE = stable and $BUILD_VERSION is null or empty
# then set $BUILD_VERSION to nigthly build name with S in place of N
if [ "$BUILD_TYPE" = "stable" ]; then
	if [ "$BUILD_VERSION" = "" ]; then
		# Replace N by S : for instance 1.1.0-N20170529-151714 to 1.1.0-S20170529-151714
		if [ "$LOG" = "true" ]; then
			echo ">>> Run cmd 4 ReleaseProducts.sh start : BUILD_VERSION=\$\(basename $PRODUCT_FOLDER_TO_RELEASE | sed s/-N2/-S2/g\)"
		fi
		BUILD_VERSION=$(basename $PRODUCT_FOLDER_TO_RELEASE | sed s/-N2/-S2/g)
		if [ "$LOG" = "true" ]; then
			echo ">>> Run cmd 4 ReleaseProducts.sh end : BUILD_TYPE=stable and BUILD_VERSION is empty : BUILD_VERSION=$BUILD_VERSION"
		fi
	fi
fi


# Ensure the target folder exists
mkdir -p "$TARGET_DIR_RELEASE_PRODUCT"
# The actual publication of the p2 repo produced by the build
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 5 ReleaseProducts.sh start : cp -dR \"$PRODUCT_FOLDER_TO_RELEASE\" \"$TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION\""
fi
if [ -d $PRODUCT_FOLDER_TO_RELEASE ]; then
	if [ -d $TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION ]; then
		echo "The nightly build $PRODUCT_FOLDER_TO_RELEASE is already release in $TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION."
	else
		cp -dR "$PRODUCT_FOLDER_TO_RELEASE" "$TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION"
	fi
else
	echo "The nightly build $PRODUCT_FOLDER_TO_RELEASE does not exist longer. It was probably erase previously."
fi
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 5 ReleaseProducts.sh end"
fi


# Manifest file
if [ "$LOG" = "true" ]; then
	echo "Run cmd 6 ReleaseProducts.sh start : create ManifestRelease$COMPONENT_NAME.txt"
fi
MANIFEST_NAME="ManifestRelease$COMPONENT_NAME.txt"
MANIFEST_FILE="$WORKSPACE/$MANIFEST_NAME"

echo "<<<ORGINE MANIFEST<<<" > $MANIFEST_FILE
cat $WORKSPACE/$TARGET_MANIFEST_NAME >>  $MANIFEST_FILE
echo "<<<ORGINE MANIFEST<<<" >> $MANIFEST_FILE
echo "" >> $MANIFEST_FILE
echo "<<< RELEASE MANIFEST<<<"  >> $MANIFEST_FILE
echo "Update release path : $TARGET_DIR_RELEASE_PRODUCT" >>  $MANIFEST_FILE
echo "Update release url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$VERSION" >>  $MANIFEST_FILE
echo "Products release url list :  " >>  $MANIFEST_FILE
for f in "$PRODUCT_FOLDER_TO_RELEASE/"*.zip ; do
	echo "    $URL_PRODUCT_PREFIX/$BUILD_TYPE/$VERSION/$(basename $f)" >> $MANIFEST_FILE
done
echo "Products release manifest url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$VERSION/$MANIFEST_NAME" >>  $MANIFEST_FILE
echo ">>> RELEASE MANIFEST>>>"  >> $MANIFEST_FILE
if [ "$LOG" = "true" ]; then
	echo "Run cmd 6 ReleaseProducts.sh end"
fi
# Copy manifest to published update
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 7 ReleaseProducts.sh start : cp -dR $MANIFEST_FILE $TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION"
fi
cp -dR $MANIFEST_FILE $TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION
if [ "$LOG" = "true" ]; then
	echo ">>> Run cmd 7 ReleaseProducts.sh end"
fi

echo "Release this night build  '$PRODUCT_FOLDER_TO_RELEASE' to publish folder '$TARGET_DIR_RELEASE_PRODUCT$BUILD_VERSION'" 
for f in "$PRODUCT_FOLDER_TO_RELEASE/"*.zip ; do
	echo "		Url Product $(basename $f) release : $URL_PRODUCT_PREFIX/$BUILD_TYPE/$VERSION/$(basename $f)" >> $MANIFEST_FILE
done

