/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.services;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * The Capella services provider.
 */
public abstract class CapellaServicesProvider {
  // Singleton.
  private static CapellaServicesProvider _instance;

  // The extension point identifier.
  private static String EXT_POINT_ID = "capellaServicesProvider"; //$NON-NLS-1$
  // The plug-in identifier.
  private static String PLUGIN_ID = "org.polarsys.capella.core.services"; //$NON-NLS-1$

  /**
   * Return set of services specific to edition.
   * @return Set of editing services.
   */
  public abstract IEditingServices getEditingServices();

  /**
   * Return set of services specific to Project.
   * @return Set of project services.
   */
  public abstract IProjectServices getProjectServices();

  /**
   * Retrieve concrete Capella services provider.
   * @return The concrete Capella services provider.
   */
  public static CapellaServicesProvider getReferencedElement() {
    if (null == _instance) {
      IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(PLUGIN_ID, EXT_POINT_ID);
      IConfigurationElement configurationElement = configurationElements[0];
      _instance = (CapellaServicesProvider) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
    }
    return _instance;
  }
}
