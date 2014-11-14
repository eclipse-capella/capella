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
package org.polarsys.capella.core.model.semantic.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.core.model.semantic.prefs.ISemanticModelPreferences;
import org.polarsys.capella.core.model.semantic.prefs.SemanticModelPreferences;

public class SemanticModelActivator implements BundleActivator {

  public static final String PLUGIN_ID = "org.polarsys.capella.core.model.semantic"; //$NON-NLS-1$

  private static BundleContext __context;
  private static SemanticModelActivator __defaultActivator;
  private ISemanticModelPreferences _preferences = new SemanticModelPreferences();

  static BundleContext getContext() {
    return __context;
  }

  public static SemanticModelActivator getDefault() {
    return __defaultActivator;
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext )
   */
  @Override
  public void start(BundleContext bundleContext) throws Exception {
    SemanticModelActivator.__context = bundleContext;
    __defaultActivator = this;
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext bundleContext_p) throws Exception {
    __defaultActivator = null;
    SemanticModelActivator.__context = null;
  }

  /**
   * Get the active preferences.
   */
  public ISemanticModelPreferences getPreferences() {
    return _preferences;
  }

  /**
   * Allows test classes to set custom preference mocks.
   * @param preferences_p
   */
  public void setPreferences(ISemanticModelPreferences preferences_p) {
    _preferences = preferences_p;
  }

}
