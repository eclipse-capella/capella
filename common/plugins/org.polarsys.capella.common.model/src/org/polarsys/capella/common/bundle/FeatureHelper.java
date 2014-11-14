/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.bundle;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;

/**
 * Feature helper that provides services regarding Eclipse Features.
 */
public class FeatureHelper {

  /**
   * Capella Feature id.
   */
  private static final String CAPELLA_FEATURE_ID = "org.polarsys.capella.core.advance.feature"; //$NON-NLS-1$

  /**
   * Get the Capella feature version.
   * @param fullVersionNumber_p
   * @return a not <code>null</code> string.
   */
  public static String getCapellaVersion(boolean fullVersionNumber_p) {
    return (fullVersionNumber_p) ? FeatureHelper.getFullVersionNumber(CAPELLA_FEATURE_ID) : FeatureHelper.getShortVersionNumber(CAPELLA_FEATURE_ID);
  }
  
  /**
   * Get the current feature version from the runtime platform.<br>
   * @return the full version number including a qualifier if any (e.g 1.2.0.20100118-1720)
   */
  public static String getFullVersionNumber(String featureId_p) {
    String version = null;
    // Get bundle providers.
    IBundleGroupProvider[] bundleGroupProviders = Platform.getBundleGroupProviders();
    // Loop over bundle providers to get the one that provides given feature.
    for (IBundleGroupProvider bundleGroupProvider : bundleGroupProviders) {
      IBundleGroup[] bundleGroups = bundleGroupProvider.getBundleGroups();
      // Loop over bundles to retrieve the feature.
      for (IBundleGroup bundleGroup : bundleGroups) {
        if (featureId_p.equals(bundleGroup.getIdentifier())) {
          version = bundleGroup.getVersion();
          break;
        }
      }
      // Check if version was found.
      if (null != version) {
        break;
      }
    }
    return version;
  }

  /**
   * Get the current feature version from the runtime platform.<br>
   * @param featureId_p
   * @return the short version number i.e 3 numbers separated by '.' character (e.g 1.2.0).
   */
  public static String getShortVersionNumber(String featureId_p) {
    String fullVersionNumber = getFullVersionNumber(featureId_p);
    return (null != fullVersionNumber) ? fullVersionNumber.substring(0, 5) : null;
  }
}
