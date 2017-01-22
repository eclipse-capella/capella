/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;

/**
 */
public class CapellaReadOnlyHelper {
  /**
   * Read only section handler (shared for all instances).
   */
  private static IReadOnlySectionHandler __readOnlySectionHandler;

  /**
   * Flag used to know if IReadOnlySectionHandler has been lookup.
   */
  private static boolean __alreadyLookup;

  /**
   * @param element_p
   * @param listener_p
   */
  public static IReadOnlySectionHandler register(EObject element_p, IReadOnlyListener listener_p) {
    IReadOnlySectionHandler readOnlyHandler = getReadOnlySectionHandler();
    if ((null != readOnlyHandler) && (null != element_p)) {
      readOnlyHandler.register(element_p, listener_p);
    }
    return readOnlyHandler;
  }

  /**
   * @param element_p
   * @param listener_p
   */
  public static IReadOnlySectionHandler unregister(EObject element_p, IReadOnlyListener listener_p) {
    IReadOnlySectionHandler readOnlyHandler = getReadOnlySectionHandler();
    if ((null != readOnlyHandler) && (null != element_p)) {
      readOnlyHandler.unregister(element_p, listener_p);
    }
    return readOnlyHandler;
  }

  /**
   * Get the unique {@link IReadOnlySectionHandler}.
   */
  public static IReadOnlySectionHandler getReadOnlySectionHandler() {
    if (!__alreadyLookup && (null == __readOnlySectionHandler)) {
      __readOnlySectionHandler = getReadOnlySectionHandlerContributor();
      __alreadyLookup = true;
    }
    return __readOnlySectionHandler;
  }

  /**
   * Get the unique {@link IReadOnlySectionHandler}.
   */
  private static IReadOnlySectionHandler getReadOnlySectionHandlerContributor() {
    // Load IReadOnlySectionHandler contributor if any.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(ModelHandlerPlugin.PLUGIN_ID, "readOnlySectionHandler"); //$NON-NLS-1$
    // Loop over contributed IReadOnlySectionHandler contributor, must be only one.
    if (configurationElements.length > 0) {
      return (IReadOnlySectionHandler) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
    }
    return null;
  }
}
