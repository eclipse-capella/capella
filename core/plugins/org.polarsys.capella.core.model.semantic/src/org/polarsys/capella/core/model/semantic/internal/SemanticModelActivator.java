/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.semantic.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.core.model.semantic.prefs.ISemanticModelPreferences;
import org.polarsys.capella.core.model.semantic.prefs.SemanticModelPreferences;

public class SemanticModelActivator implements BundleActivator {

  public static final String PLUGIN_ID = "org.polarsys.capella.core.model.semantic"; //$NON-NLS-1$

  private static BundleContext context;
  private static SemanticModelActivator defaultActivator;
  private ISemanticModelPreferences preferences = new SemanticModelPreferences();

  static BundleContext getContext() {
    return context;
  }

  public static SemanticModelActivator getDefault() {
    return defaultActivator;
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext )
   */
  @Override
  public void start(BundleContext bundleContext) throws Exception {
    SemanticModelActivator.context = bundleContext;
    defaultActivator = this;
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext bundleContext) throws Exception {
    defaultActivator = null;
    SemanticModelActivator.context = null;
  }

  /**
   * Get the active preferences.
   */
  public ISemanticModelPreferences getPreferences() {
    return preferences;
  }

  /**
   * Allows test classes to set custom preference mocks.
   * @param preferences
   */
  public void setPreferences(ISemanticModelPreferences preferences) {
    this.preferences = preferences;
  }

}
