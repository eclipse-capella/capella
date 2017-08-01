#!/bin/sh
# ====================================================================
# Copyright (c) 2017 THALES GLOBAL SERVICES.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#      Obeo - initial implementation retrieved from Sirius
#    Thales - adaptation for Capella
# ====================================================================

[ -z "$WORKSPACE" -o -z "$GIT_BRANCH" ] && {
     echo "Execution aborted.

One or more of the required variables are not set. They are normally provided by the Hudson build.

- WORKSPACE  : the build workspace root.
- GIT_BRANCH : the name for the Git branch being build/published.
"
    exit 1
}

######################################################################
# Setup
######################################################################

# Exit on error
set -e

# The full version (should be taken as an argument)
export VERSION="1.2.0"

# The name of the target Eclipse release (could be taken as an argument)
export PLATFORM="neon"

# The type of build being published
export BUILD_TYPE="nightly"
export BUILD_TYPE_PREFIX="N"

# The root folder for all Capella udpate sites
export CAPELLA_UPDATES_ROOT="/home/data/httpd/download.polarsys.org/capella/core/updates"

# Streams are of the form 1.0.x: only keep major and minor version number parts
export STREAM=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+\.).*$/\1x/')

# The short version, common to all versions in that stream
export SHORT_VERSION=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+)\..*$/\1/')

# Converts the Hudson BUILD_ID (e.g. 2013-10-15_07-07-07) into the
# syntax we want for our update-sites (e.g. 20131015-070707)
export BUILD_TIMESTAMP=$(echo "$BUILD_ID" | sed -e 's/-//g' -e 's/_/-/')

# The timestamp in the p2 composite repos used to implement redirects
export P2_TIMESTAMP=$(date +"%s000")

# The full version for this build, e.g. 1.2.0-N20160130-010203
export FULL_VERSION="${VERSION}-${BUILD_TYPE_PREFIX}${BUILD_TIMESTAMP}"

# The root folder where all the builds of the same type as this one are published
export TARGET_ROOT="$CAPELLA_UPDATES_ROOT/$BUILD_TYPE"

# The folder for this particular build
export TARGET_DIR="$TARGET_ROOT/$FULL_VERSION/$PLATFORM"

######################################################################
# Publish the build
######################################################################
export WKS="."

# Ensure the target folder exists
mkdir -p "$TARGET_DIR"
# The actual publication of the p2 repository produced by the build
cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.site/target/repository/* "$TARGET_DIR"
cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.site/target/org.polarsys.capella.rcp-updateSite-*.zip "$TARGET_ROOT/$FULL_VERSION/org.polarsys.capella.rcp-updateSite-$FULL_VERSION-$PLATFORM.zip"
# Publish the target platform definitions used, so that downstream projects can reference them
mkdir -p "$TARGET_DIR/targets"
cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.target/full/* "$TARGET_DIR/targets"
cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.target/headless/* "$TARGET_DIR/targets"
# Publish a dump of the build environment, may be useful to debug
env | sort > "$TARGET_DIR/build_env.txt"

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
<repository name='Polarsys Capella' type='org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='http://download.polarsys.org/capella/core/updates/$TO'/>
  </children>
</repository>
EOF

    cat > "$FROM/compositeContent.xml" <<EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeMetadataRepository version='1.0.0'?>
<repository name='Polarsys Capella' type='org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='http://download.polarsys.org/capella/core/updates/$TO'/>
  </children>
</repository>
EOF

}

# First, a link for the $VERSION (e.g. "1.2.0/neon" => "1.2.0-NYYYYMMDD-HHMM/neon")
#create_redirect "$TARGET_ROOT/$VERSION/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
# Also create a link for the $STREAM (e.g. "1.2.x/neon" => "1.2.0-NYYYYMMDD-HHMM/neon")
create_redirect "$TARGET_ROOT/$STREAM/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
# And publish the zipped versions there, at stable URLs
cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.site/target/org.polarsys.capella.rcp-updateSite-*.zip "$TARGET_ROOT/$STREAM/org.polarsys.capella.rcp-updateSite-$VERSION-$PLATFORM.zip"
# Also update the global "latest" links if we are building master
if [ "origin/bugzilla/000097/experimentalPlatformBuild" = "$GIT_BRANCH" ]; then
    create_redirect "$TARGET_ROOT/latest/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
    cp -dR "$WKS"/releng/plugins/org.polarsys.capella.rcp.site/target/org.polarsys.capella.rcp-updateSite-*.zip "$TARGET_ROOT/latest/org.polarsys.capella.rcp-updateSite-$VERSION-$PLATFORM.zip"
fi
