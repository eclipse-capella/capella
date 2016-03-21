/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.command.recorder.core;

import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.command.recorder.core.preferences.internal.RecorderCorePreferenceInitializer;
import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class RecorderCoreActivator extends AbstractActivator {

  // The shared instance
  private static RecorderCoreActivator plugin;
  /*
   * 
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.common.command.recorder.core"; //$NON-NLS-1$

  /**
   * The constructor
   */
  public RecorderCoreActivator() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    new RecorderCorePreferenceInitializer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static RecorderCoreActivator getDefault() {
    return plugin;
  }
}
