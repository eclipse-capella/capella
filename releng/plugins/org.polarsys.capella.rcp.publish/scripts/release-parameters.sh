#!/bin/sh
# ====================================================================
# Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Thales - initial API and implementation
# ====================================================================


# Get script parameter
# Verify parameter number => should be 2
if [ "$#" -lt 3 ]
then
>&2 echo "ERREUR ${scriptName} : This script should take 1 parameters to run it."
>&2 echo "Command : ${scriptName} productName componentName  buildType"
	exit 98
fi

# Extract parameters
export PRODUCT_NAME=$1
export COMPONENT_NAME=$2
export BUILD_TYPE=$3

# The type of build being published
if [ "$BUILD_TYPE" = "release" ]; then
	export BUILD_TYPE_PREFIX="R"
elif [ "$BUILD_TYPE" = "milestone" ]; then
	export BUILD_TYPE_PREFIX="M"
elif [ "$BUILD_TYPE" = "stable" ]; then
	export BUILD_TYPE_PREFIX="S"
fi

# The root folder for all product
export KITAPHA_FOLDER_ROOT="/home/data/httpd/download.polarsys.org/$PRODUCT_NAME"
# The root folder for all Kitapha udpate sites
export KITAPHA_UPDATES_ROOT="$KITAPHA_FOLDER_ROOT/updates"
# The root folder where all the builds of the same type as this one
# are published
export TARGET_ROOT="$KITAPHA_UPDATES_ROOT/$BUILD_TYPE"

# The folder for this particular release updatesite
export TARGET_DIR_RELEASE="$TARGET_ROOT/$COMPONENT_NAME/$BUILD_VERSION"
# The folder for this particular release product  ???? vide pour le cas product !!!
export TARGET_DIR_RELEASE_PRODUCT="$TARGET_ROOT_PRODUCT/$BUILD_VERSION"
