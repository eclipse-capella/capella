/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 */
public class SessionLabelProviderHelper {

  private ILabelProvider _labelProvider;

  private static SessionLabelProviderHelper _instance;

  public static SessionLabelProviderHelper getInstance() {
    if (null == _instance) {
      _instance = new SessionLabelProviderHelper();
    }
    return _instance;
  }

  private SessionLabelProviderHelper() {
    // do nothing
  }

  public ILabelProvider getSessionLabelProvider() {
    if (_labelProvider == null) {
        _labelProvider = new AdapterFactoryLabelProvider(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
    }
    return _labelProvider;
  } 
}
