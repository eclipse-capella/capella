/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipFile;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaCheckAcceleo2NotUsed extends BasicTestCase {
  private static final String pluginDir = "org.polarsys.capella.core.sirius.analysis";
  private static final String siriusLegacyDependency = "org.eclipse.sirius.query.legacy";
  private static final String acceleo2Pattern = "&lt;%.*%>";
  private static final String siriusLegacyDependencyMsg = "Dependency {1} found in {0}.";
  private static final String acceleo2PatternMsg = "Acceleo2 query detected in {0}. Found line: {1}.";

  @Override
  public void test() throws Exception {

    ZipFile siriusAnalysis = PlatformFilesHelper.getPluginJar(pluginDir);

    assertTrue("Plugin folder not found: " + pluginDir, siriusAnalysis != null);

    List<String> manifestFiles = PlatformFilesHelper.getJarFilesByNameEnding(siriusAnalysis, "MANIFEST.MF");
    assertTrue("Manifest not found for plugin: " + pluginDir, !manifestFiles.isEmpty());

    List<String> odesignFiles = PlatformFilesHelper.getJarFilesByNameEnding(siriusAnalysis, "odesign");
    assertTrue("Odesign files not found for plugin: " + pluginDir, !odesignFiles.isEmpty());

    // check that the manifest does not contain legacy sirius code
    checkInFiles(manifestFiles, siriusLegacyDependency, siriusLegacyDependencyMsg);

    // check that the odesign files does not contain acceleo 2 queries
    checkInFiles(odesignFiles, acceleo2Pattern, acceleo2PatternMsg);
  }

  private void checkInFiles(List<String> paths, String pattern, String message) throws IOException {
    for (String path : paths) {
      File file = File.createTempFile(path, null);
      String line = PlatformFilesHelper.findInFile(file, pattern);
      String printMessage = NLS.bind(message, new String[] { file.getName(), line });
      assertTrue(printMessage, line == null);
    }
  }
}
