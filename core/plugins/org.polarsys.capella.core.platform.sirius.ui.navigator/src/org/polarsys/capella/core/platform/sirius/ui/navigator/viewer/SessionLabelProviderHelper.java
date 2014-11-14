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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;

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
        _labelProvider = new AdapterFactoryLabelProvider(ViewHelper.INSTANCE.createAdapterFactory());
    }
    return _labelProvider;
  } 
}
