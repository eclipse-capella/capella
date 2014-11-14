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

package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;

/**
 * The Capella adapter factory.
 */
public class CapellaNavigatorAdapterFactory {
  // The adapter factory.
  private static AdapterFactory _adapterFactory;

  /**
   * Gets the Capella adapter factory singleton.
   * @return The Capella adapter factory.
   */
  public final static AdapterFactory getAdapterFactory() {
    if (null == _adapterFactory) {
      _adapterFactory = createAdapterFactory();
    }
    return _adapterFactory;
  }

  // Creates the composed adapter factory.
  private static AdapterFactory createAdapterFactory() {
    // Use this one to have Sirius labels and images correctly displayed in the navigator.
    return ViewHelper.INSTANCE.createAdapterFactory();
  }
}
