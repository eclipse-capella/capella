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

check_folder(){
	typeCheck=$1
	folderToCheck=$2
	echo "$typeCheck release : List of file and folder $folderToCheck/"
	if [ -d $folderToCheck ]; then
		ls -al $folderToCheck/
	fi
}

# Get folder path contain this script
BASEDIR=$(dirname $0)

if [ "$LOG" = "true" ]; then
	check_folder Before $PUBLISH_FOLDER_ROOT/updates/release
	check_folder Before $PUBLISH_FOLDER_ROOT/products/release
	check_folder Before $PUBLISH_FOLDER_ROOT/updates/milstone
	check_folder Before $PUBLISH_FOLDER_ROOT/products/milestone
	check_folder Before $PUBLISH_FOLDER_ROOT/updates/stable
	check_folder Before $PUBLISH_FOLDER_ROOT/products/stable
	echo "Before release : df -h $PUBLISH_FOLDER_ROOT"
	df -h $PUBLISH_FOLDER_ROOT/
fi

# Set srcipt project location in job Kitalpha_build_tycho_experimental
echo "Launch the release of Capella SDK and products"
. $BASEDIR/ReleaseProducts.sh
. $BASEDIR/ReleaseUpdateCapella.sh

if [ "$LOG" = "true" ]; then
	check_folder After $PUBLISH_FOLDER_ROOT/updates/release
	check_folder After $PUBLISH_FOLDER_ROOT/products/release
	check_folder After $PUBLISH_FOLDER_ROOT/updates/milstone
	check_folder After $PUBLISH_FOLDER_ROOT/products/milestone
	check_folder After $PUBLISH_FOLDER_ROOT/updates/stable
	check_folder After $PUBLISH_FOLDER_ROOT/products/stable
	echo "After release : df -h /home/data/httpd/download.eclipse.org/capella"
	df -h $PUBLISH_FOLDER_ROOT
fi
