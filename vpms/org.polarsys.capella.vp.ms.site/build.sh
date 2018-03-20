#*******************************************************************************
# Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#   
# Contributors:
#    Thales - initial API and implementation
#*******************************************************************************


# Download and install buckminster
#
ant_installer=director-buckminster-b3-install.ant
test -f "$ant_installer" || curl -L -O https://raw.githubusercontent.com/LorenzoBettini/director-buckminster-b3-install/master/"$ant_installer"
ant -Dbase.dir.install=. -f "$ant_installer" install-buckminster-4.3


# Download and unzip capella 1.2.0
#
if [ ! -d capella ]; then
   curl -s -L -o capella-1.2.x.zip http://download.polarsys.org/capella/core/products/releases/1.2.0-R20171103-050121/capella-1.2.0.201711030906-linux-gtk-x86_64.zip
   unzip capella-1.2.x.zip
   rm capella-1.2.x.zip
fi


# Build
#
site_dir=${WORKSPACE}/git/vpms/org.polarsys.capella.vp.ms.site/

buckminster_out="${WORKSPACE}/buckminster.output"
buckminster_temp="${WORKSPACE}/buckminster.temp"

rm -rf "$buckminster_out"

buckminster-4.3/buckminster -Dbuckminster.output.root="$buckminster_out" \
  -Dbuckminster.temp.root="$buckminster_temp" \
  -Dqualifier.replacement.*=generator:buildTimestamp \
  -Dgenerator.buildTimestamp.format=\'v\'yyyyMMdd-HHmm \
  -Dcbi.include.source=true \
  -Dtarget.os=* \
  -Dtarget.ws=* \
  -Dtarget.arch=* \
  -Dworkspace="$WORKSPACE" -data ws --scriptfile ${site_dir}/commands.txt

if [ $? -eq 0 ]; then
  rm -rf "$buckminster_temp"
  feature=$(find buckminster.output/org.polarsys.capella.vp.ms.site_*.*.*-eclipse.feature/site.p2/features/org.polarsys.capella.vp.ms.feature* | tail -n1)
  version=$(basename $feature | perl -pe 's/org.polarsys.capella.vp.ms.feature_(.*).jar/$1/')
  simpleversion=$(echo $version | perl -pe 's/(\d+\.\d+\.\d+).*/$1/')

  echo $version
  echo $simpleversion

  rm -rf capella-vpms-addon-dropins-*
  mkdir capella-vpms-addon-dropins-$version
  
  cp -r $buckminster_out/org.polarsys.capella.vp.ms.site_${simpleversion}-eclipse.feature/site.p2/features capella-vpms-addon-dropins-$version
  cp -r $buckminster_out/org.polarsys.capella.vp.ms.site_${simpleversion}-eclipse.feature/site.p2/plugins capella-vpms-addon-dropins-$version

  rm -rf capella-vpms-addon-site-*
  mkdir capella-vpms-addon-site-$version

  cp -r $buckminster_out/org.polarsys.capella.vp.ms.site_${simpleversion}-eclipse.feature/site.p2/* capella-vpms-addon-site-$version

  rm -rf $buckminster_out
fi
