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


[ -z "$WORKSPACE" -o -z "$GIT_BRANCH" ] && {
     echo "Execution aborted.

One or more of the required variables is not set. They are normally
provided by the Hudson build.

- WORKSPACE  : the build workspace root.
- GIT_BRANCH : the name fo the Git branch being build/published.
"
    exit 1
}

# Get folder path contain this script
BASEDIR=$(dirname $0)
# Get script name
SCRIPT_NAME=$(basename $0)

# Get script parameter
# Verify parameter number => should be 6 
if [ "$#" -lt 8 ]
then
>&2 echo "ERREUR ${scriptName} : This script should take 8 parameters to run it."
>&2 echo "Command : ${scriptName} productName componentName updatePrjName updatePath version buildType buildTypePrefix TPPath"
	exit 99
fi

export PRODUCT_NAME=$1
export COMPONENT_NAME=$2
export UPDATE_PRJ_NAME=$3
export UPDATE_PATH=$4
export VERSION=$5
export BUILD_TYPE=$6
export BUILD_TYPE_PREFIX=$7
export TARGET_PATH=$8

######################################################################
# Setup
######################################################################

# Exit on error
set -e


# Manifest file
MANIFEST_NAME="Manifest$COMPONENT_NAME.txt"
MANIFEST_FILE="$WORKSPACE/$UPDATE_PATH/target/$MANIFEST_NAME"


# other parameters are defined in global-parameters.sh and publish-parameters.sh script and launched before this script.

######################################################################
# Publish the build
######################################################################

# Ensure the target folder exists
mkdir -p "$TARGET_DIR"
# The actual publication of the p2 repo produced by the build
cp -dR "$WORKSPACE/$UPDATE_PATH"/target/repository/* "$TARGET_DIR"
echo "Update site runtime core : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
# Publish the target platform definitions used, so that downstream projects can reference them
mkdir -p "$TARGET_DIR/targets"
cp -dR "$WORKSPACE"/$TARGET_PATH/* "$TARGET_DIR/targets"
echo "TP for runtime core TP : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION/targets"
# Publish a dump of the build environment, may be useful to debug
env | sort > "$TARGET_DIR/build_env.txt"
echo "Env for runtime core TP : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION/build_env.txt"

######################################################################
# Setup or update the redirects (implemented as composite repos)
######################################################################

# Create a p2 composite repo to setup a redirect
create_redirect() {
    FROM="$1"
    TO="$2"

    mkdir -p "$FROM"
    cat > "$FROM/compositeArtifacts.xml" <<EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeArtifactRepository version='1.0.0'?>
<repository name='$PRODUCT_NAME $COMPONENT_NAME' type='org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='$URL_PUBLISH_PREFIX/$TO'/>
  </children>
</repository>
EOF

    cat > "$FROM/compositeContent.xml" <<EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeMetadataRepository version='1.0.0'?>
<repository name='$PRODUCT_NAME $COMPONENT_NAME' type='org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='$URL_PUBLISH_PREFIX/$TO'/>
  </children>
</repository>
EOF

}

# First, a link for the $VERSION (e.g. "runtimecore/1.2.0" => "1.2.0-NYYYYMMDD-HHMM/luna")
create_redirect "$TARGET_ROOT/$COMPONENT_NAME/$VERSION" "$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
echo "Link runtime core Version : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$VERSION to $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION "
# Also create a link for the $STREAM (e.g. "runtimecore/1.2.x" => "1.2.0-NYYYYMMDD-HHMM/luna")
# and publish the zipped versions there, at stable URLs
create_redirect "$TARGET_ROOT/$COMPONENT_NAME/$STREAM" "$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
echo "Link runtime core Short Version : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$STREAM to $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION "
# Also update the global "latest" links if we are building master
#if [ "master" = "$GIT_BRANCH" -o "build_tycho_experimental" = "$GIT_BRANCH" ]; then
#    create_redirect "$TARGET_ROOT/$COMPONENT_NAME/latest" "$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
#    cp -dR "$WORKSPACE"/$UPDATE_PATH/target/$UPDATE_PRJ_NAME-*.zip "$TARGET_ROOT/$COMPONENT_NAME/$STREAM/$UPDATE_PRJ_NAME-$VERSION.zip"
#else
#	create_redirect "$TARGET_ROOT/$COMPONENT_NAME/$GIT_BRANCH/latest" "$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
#fi
create_redirect "$TARGET_ROOT/$COMPONENT_NAME/latest" "$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION"
echo "Link runtime core Latest : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/latest to $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION "
cp -dR "$WORKSPACE/$UPDATE_PATH"/target/$UPDATE_PRJ_NAME-*.zip "$TARGET_ROOT/$COMPONENT_NAME/$STREAM/$UPDATE_PRJ_NAME-$VERSION.zip"
echo "Zipped update site runtime core : $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$STREAM/$UPDATE_PRJ_NAME-$VERSION.zip"

# Write manifest in artefacts
echo "IC Build number : $BUILD_NUMBER" > $MANIFEST_FILE
echo "IC Build url : $MASTER_BUILD_URL/$BUILD_NUMBER/" >> $MANIFEST_FILE
echo "Update nightly path : $TARGET_DIR" >>  $MANIFEST_FILE
echo "Update nightly url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION" >>  $MANIFEST_FILE
echo "Update nightly manifest url :  $URL_PUBLISH_PREFIX/$BUILD_TYPE/$COMPONENT_NAME/$FULL_VERSION/$MANIFEST_NAME" >>  $MANIFEST_FILE
# Create update file to launch Capella
if [ "$LAUNCH_CAPELLA" = "true" ]; then
	echo "Launch capella : true" >>  $MANIFEST_FILE
	cp -dR $MANIFEST_FILE $TARGET_ROOT/$COMPONENT_NAME/latest/launch_cappella.txt
else
	echo "Launch capella : false" >>  $MANIFEST_FILE
fi

# Copy manifest to published update
cp -dR $MANIFEST_FILE $TARGET_DIR