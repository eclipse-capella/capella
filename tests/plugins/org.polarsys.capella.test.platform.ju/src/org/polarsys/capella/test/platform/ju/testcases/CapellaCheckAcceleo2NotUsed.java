/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.File;
import java.util.List;

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

    File testPluginFolder = getPluginFolder();
    File capella = PlatformFilesHelper.findRootFolder(testPluginFolder);
    File core = PlatformFilesHelper.getSubFolder(capella, "core");
    File plugins = PlatformFilesHelper.getSubFolder(core, "plugins");
    File siriusAnalysis = PlatformFilesHelper.getSubFolder(plugins, pluginDir);

    assertTrue("Plugin folder not found: " + pluginDir, siriusAnalysis != null);

    List<File> manifestFiles = PlatformFilesHelper.getPluginFilesByNameEnding(siriusAnalysis, "MANIFEST.MF");
    assertTrue("Manifest not found for plugin: " + pluginDir, !manifestFiles.isEmpty());

    List<File> odesignFiles = PlatformFilesHelper.getPluginFilesByNameEnding(siriusAnalysis, "odesign");
    assertTrue("Odesign files not found for plugin: " + pluginDir, !odesignFiles.isEmpty());

    // check that the manifest does not contain legacy sirius code
    checkInFiles(manifestFiles, siriusLegacyDependency, siriusLegacyDependencyMsg);

    // check that the odesign files does not contain acceleo 2 queries
    checkInFiles(odesignFiles, acceleo2Pattern, acceleo2PatternMsg);
  }

  private void checkInFiles(List<File> files, String pattern, String message) {
    for (File f : files) {
      String line = PlatformFilesHelper.findInFile(f, pattern);
      String printMessage = NLS.bind(message, new String[] { f.getName(), line });
      assertTrue(printMessage, line == null);
    }
  }
}
