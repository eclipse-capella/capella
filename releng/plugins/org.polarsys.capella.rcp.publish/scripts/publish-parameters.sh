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

# ====================================================================
#
# This script Publish update site from runtime core build to
# to http://download.polarsys.org/kitapha/updates
#
# ====================================================================

# Get script name
SCRIPT_NAME=$(basename $0)

# Get script parameter
# Verify parameter number => should be 3 
if [ "$#" -lt 3 ]
then
>&2 echo "ERREUR ${scriptName} : This script should take 1 parameters to run it."
>&2 echo "Command : ${scriptName} componentName  buildType buildTypePrefix"
	exit 98
fi

# Get component name
export COMPONENT_NAME=$1
export BUILD_TYPE=$2
export BUILD_TYPE_PREFIX=$3

# The full version (should be taken as an argument)
export VERSION="1.2.0"

# Streams are of the form 1.0.x: only keep major and minor version number parts
export STREAM=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+\.).*$/\1x/')
# The short version, common to all versions in that stream
export SHORT_VERSION=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+)\..*$/\1/')
# Converts the Hudson BUILD_ID (e.g. 2013-10-15_07-07-07) into the
# syntax we want for our update-sites (e.g. 20131015-070707)
export BUILD_TIMESTAMP=$(echo "$BUILD_ID" | sed -e 's/-//g' -e 's/_/-/')
# The timestamp in the p2 composite repos used to implement redirects
export P2_TIMESTAMP=$(date +"%s000")
# The full version for this build, e.g. 0.9.0-N20131015-070707
export FULL_VERSION="${VERSION}-${BUILD_TYPE_PREFIX}${BUILD_TIMESTAMP}"
# The folder for this particular build
export TARGET_DIR="$TARGET_ROOT/$COMPONENT_NAME/$FULL_VERSION"
# The folder for this particular build product
export TARGET_DIR_PRODUCT="$TARGET_ROOT_PRODUCT/$FULL_VERSION"
