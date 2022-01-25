/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * 
 */
public class ImporterRegistry {
  /**
   * Contribution importer extension-point id.
   */
  static final String CONTRIBUTION_EXTENSION_ID = "fileimporter"; //$NON-NLS-1$
  /**
   * Singleton instance.
   */
  private static ImporterRegistry __instance;
  /**
   * Map of contributed importers per file extension.
   */
  private Map<String, List<IFileImporter>> _importerContributions;

  /**
   * Get the singleton instance.
   */
  public static ImporterRegistry getInstance() {
    if (null == __instance) {
      __instance = new ImporterRegistry();
    }
    return __instance;
  }

  /**
   * Constructor.
   */
  private ImporterRegistry() {
    // Instantiate the map that holds all contributions.
    _importerContributions = new HashMap<String, List<IFileImporter>>(0);
    // Load contributions.
    IConfigurationElement[] contributors = ExtensionPointHelper.getConfigurationElements(FrameworkUtil.getBundle(ImporterRegistry.class).getSymbolicName(), CONTRIBUTION_EXTENSION_ID);
    for (IConfigurationElement contributorElement : contributors) {
      String extension = (String) contributorElement.getAttribute("extension");
      // Instantiate current contribution.
      IFileImporter importer = (IFileImporter) ExtensionPointHelper.createInstance(contributorElement, ExtensionPointHelper.ATT_CLASS);
      // Get existing contributions for related extension
      List<IFileImporter> contributionsForRelatedExtension = _importerContributions.get(extension);
      if (null == contributionsForRelatedExtension) {
        // No existing contributions, initialize a new collection.
        contributionsForRelatedExtension = new ArrayList<IFileImporter>(1);
        _importerContributions.put(extension, contributionsForRelatedExtension);
      }
      // Add current contribution for the related extension.
      contributionsForRelatedExtension.add(importer);
    }
  }

  /**
   * Get all contributed importers for a given file extension.
   * @param extension
   * @return a not <code>null</code> list.
   */
  public List<IFileImporter> getImporters(String extension) {
    List<IFileImporter> result = new ArrayList<IFileImporter>();
    Iterator<Entry<String, List<IFileImporter>>> entries = _importerContributions.entrySet().iterator();
    // Iterate over all contributed importers.
    while (entries.hasNext()) {
      Map.Entry<String, java.util.List<IFileImporter>> entry = entries.next();
      // Get the extension.
      String currentExtension = entry.getKey();
      // Select this entry if criteria match.
      if (currentExtension.equals(extension)) {
        result.addAll(entry.getValue());
      }
    }
    return result;
  }
}
