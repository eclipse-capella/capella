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
package org.polarsys.capella.core.refinement.merge;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * The activator class controls the plug-in life cycle
 */
public class MergeActivator extends Plugin  {

  /**
   * The logger
   */
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(
      IReportManagerDefaultComponents.REFINEMENT
  );

  /** Accessor on logger */
  public Logger getLogger() {
    return _logger;
  }
  
  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.common.refinement.merge"; //$NON-NLS-1$

  // The shared instance
  private static MergeActivator plugin;

  /**
   * The constructor
   */
  public MergeActivator() {
    plugin = this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
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
  public static MergeActivator getDefault() {
    return plugin;
  }

  public void earlyStartup() {
    // TODO : Lazy registration of the business model.
  }
}
