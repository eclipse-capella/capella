/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.services.helper;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;

/**
 * Helper to ease Platform adapter usage.
 */
public class AdapterManagerHelper {
  /**
   * Get an adapted object for specified one according to given adapter type.<br>
   * First, the method checks if provided object is an adaptable, if so ask it to adapt to specified type.<br>
   * Second, ask the platform adapter manager to get it an adapted object without activating bundles.<br>
   * Last mile, ask the platform to activate bundles which are able to provide an adapted object.
   * @param object_p
   * @param adapterType_p
   * @return <code>null</code> if no adapted object found.
   */
  public static Object getAdapter(Object object_p, Class<?> adapterType_p) {
    Object result = null;
    // Preconditions
    if ((null == object_p) || (null == adapterType_p)) {
      return result;
    }
    // If specified object is adaptable, ask it directly.
    if (object_p instanceof IAdaptable) {
      result = ((IAdaptable) object_p).getAdapter(adapterType_p);
    }
    IAdapterManager adapterManager = Platform.getAdapterManager();
    if (null == result) {
      // Ask the platform to get the adapter.
      result = adapterManager.getAdapter(object_p, adapterType_p);
    }
    if (null == result) {
      // Ask the platform to load the adapter (i.e plug-in activation).
      result = adapterManager.loadAdapter(object_p, adapterType_p.getName());
    }
    return result;
  }
}
