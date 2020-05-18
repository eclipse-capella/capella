/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;

/**
 */
public class CapellaReadOnlyHelper {
    
  private static Object syncObj = new Object();
  /**
   * Read only section handler (shared for all instances).
   */
  private static IReadOnlySectionHandler readOnlySectionHandler;
  
  /**
   * Flag used to know if IReadOnlySectionHandler has been lookup.
   */
  private static boolean alreadyLookup;
  
  private CapellaReadOnlyHelper () {
      // To hide the implicit public one.
  }

  /**
   * @param element
   * @param listener
   */
  public static IReadOnlySectionHandler register(EObject element, IReadOnlyListener listener) {
    IReadOnlySectionHandler readOnlyHandler = getReadOnlySectionHandler();
    if ((null != readOnlyHandler) && (null != element)) {
      readOnlyHandler.register(element, listener);
    }
    return readOnlyHandler;
  }

  /**
   * @param element
   * @param listener
   */
  public static IReadOnlySectionHandler unregister(EObject element, IReadOnlyListener listener) {
    IReadOnlySectionHandler readOnlyHandler = getReadOnlySectionHandler();
    if ((null != readOnlyHandler) && (null != element)) {
      readOnlyHandler.unregister(element, listener);
    }
    return readOnlyHandler;
  }

  /**
   * Get the unique {@link IReadOnlySectionHandler}.
   */
  public static IReadOnlySectionHandler getReadOnlySectionHandler() {
    synchronized (syncObj) {
        if (!alreadyLookup && readOnlySectionHandler == null) {
            readOnlySectionHandler = getContributedReadOnlySectionHandler();
            alreadyLookup = true;
        }
    }
    return readOnlySectionHandler;
  }

  /**
   * Get the unique {@link IReadOnlySectionHandler}.
   */
  private static IReadOnlySectionHandler getContributedReadOnlySectionHandler() {
    // Load IReadOnlySectionHandler contributor if any.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(ModelHandlerPlugin.PLUGIN_ID, "readOnlySectionHandler"); //$NON-NLS-1$
    // Loop over contributed IReadOnlySectionHandler and add them to the delegate.
    if (configurationElements.length > 0) {
      ReadOnlySectionHandlerDelegate delegate = new ReadOnlySectionHandlerDelegate();
      for(IConfigurationElement cfgElement : configurationElements){
        delegate.addHandler((IReadOnlySectionHandler) ExtensionPointHelper.createInstance(cfgElement, ExtensionPointHelper.ATT_CLASS));
      }
      return delegate;
    }
    // If no one is contributed, return null.
    return null;
  }
}
