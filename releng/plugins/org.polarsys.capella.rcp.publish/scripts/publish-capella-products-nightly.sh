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


[ -z "$WORKSPACE" -o -z "$GIT_BRANCH" ] && {
     echo "Execution aborted.

One or more of the required variables is not set. They are normally
provided by the Hudson build.

- WORKSPACE  : the build workspace root.
- GIT_BRANCH : the name fo the Git branch being build/published.
"
    exit 1
}

######################################################################
# Setup
######################################################################

# Exit on error
set -e

# Component name
export COMPONENT_NAME="products"

# Product project name
export PRODUCT_PRJ_NAME="org.polarsys.capella.rcp.product"

# Target update project path
export PRODUCTS_TARGET_FOLDER="releng/plugins/$PRODUCT_PRJ_NAME/target"
export PRODUCTS_FOLDER="$PRODUCTS_TARGET_FOLDER/products"

# Get folder path contain this script
BASEDIR=$(dirname $0)

# Get script name
SCRIPT_NAME=$(basename $0)

# The type of build being published
export BUILD_TYPE="nightly"
export BUILD_TYPE_PREFIX="N"

# Extract global parameters (ie Publish fix part location)
. $BASEDIR/utils/global-parameters.sh $BUILD_TYPE

# Extract publish parameters (ie VERSION)
. $BASEDIR/utils/publish-parameters.sh $COMPONENT_NAME $BUILD_TYPE $BUILD_TYPE_PREFIX

# Ensure the target folder exists
echo "Cmd line : mkdir -p $TARGET_DIR_PRODUCT"
mkdir -p "$TARGET_DIR_PRODUCT"
# The actual publication of products
echo "Cmd line : mv -f "$WORKSPACE/$PRODUCTS_FOLDER/"capella-*.zip $TARGET_DIR_PRODUCT"
mv -f "$WORKSPACE/$PRODUCTS_FOLDER/"capella-*.zip "$TARGET_DIR_PRODUCT"

# Manifest file
MANIFEST_NAME="Manifest$COMPONENT_NAME.txt"
MANIFEST_FILE="$WORKSPACE/$PRODUCTS_TARGET_FOLDER/$MANIFEST_NAME"

# Write manifest in artefacts
echo "Write manifest : $MANIFEST_FILE"
echo "IC Build number : $BUILD_NUMBER" > $MANIFEST_FILE
echo "IC Build url : $MASTER_BUILD_URL/$BUILD_NUMBER/" >> $MANIFEST_FILE
echo "Products nightly path : $TARGET_DIR_PRODUCT" >>  $MANIFEST_FILE
echo "Products nightly url list :  " >>  $MANIFEST_FILE
echo "Products nightly url folder :  $URL_PRODUCT_PREFIX/$BUILD_TYPE/$FULL_VERSION/?d" >>  $MANIFEST_FILE
for f in "$WORKSPACE/$PRODUCTS_FOLDER/"capella-*.zip ; do
	echo "    $URL_PRODUCT_PREFIX/$BUILD_TYPE/$FULL_VERSION/$(basename $f)" >> $MANIFEST_FILE
done

echo "Products nightly manifest url :  $URL_PRODUCT_PREFIX/$BUILD_TYPE/$FULL_VERSION/$MANIFEST_NAME" >>  $MANIFEST_FILE

# Copy manifest to published site
echo "CMD line : cp -dR $MANIFEST_FILE $TARGET_DIR_PRODUCT"
cp -dR $MANIFEST_FILE $TARGET_DIR_PRODUCT

# Display Manifest on command output
cat $MANIFEST_FILE
