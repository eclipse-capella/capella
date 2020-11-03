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

# Get script name
SCRIPT_NAME=$(basename $0)

# Get script parameter
# Verify parameter number => should be 3 
if [ "$#" -lt 1 ]
then
>&2 echo "ERREUR ${scriptName} : This script should take 1 parameters to run it."
>&2 echo "Command : ${scriptName} buildType"
	exit 97
fi

# Get component name
export BUILD_TYPE=$1

# product Name
export PRODUCT_NAME=capella

# Target platform path
export TARGET_PATH="releng/plugins/org.polarsys.capella.targets/full"

# Job name (already provided by jenkins)
# export JOB_NAME=capella-master

# The root folder for all Capella artifacts
export PUBLISH_FOLDER_ROOT="/home/data/httpd/download.eclipse.org/$PRODUCT_NAME/core"

# The root folder for all Capella udpate sites
export PUBLISH_UPDATES_ROOT="$PUBLISH_FOLDER_ROOT/updates"

# The root folder for all Capella products
export PUBLISH_PRODUCTS_ROOT="$PUBLISH_FOLDER_ROOT/products"

# Publish url
export URL_PUBLISH_PREFIX="http://download.eclipse.org/$PRODUCT_NAME/core/updates"
export URL_PRODUCT_PREFIX="http://download.eclipse.org/$PRODUCT_NAME/core/products"

# Master Build Url
export MASTER_BUILD_URL="https://hudson.polarsys.org/$PRODUCT_NAME/job/$JOB_NAME"

# The root folder where all the builds of the same type as this one
# are published
export TARGET_ROOT="$PUBLISH_UPDATES_ROOT/$BUILD_TYPE"

# The root folder where all products are published
export TARGET_ROOT_PRODUCT="$PUBLISH_PRODUCTS_ROOT/$BUILD_TYPE"
