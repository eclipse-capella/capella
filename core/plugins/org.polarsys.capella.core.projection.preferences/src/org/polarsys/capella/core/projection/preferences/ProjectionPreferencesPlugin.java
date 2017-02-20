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
package org.polarsys.capella.core.projection.preferences;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 * The activator class controls the plug-in life cycle
 * (for compatibility only)
 */
@Deprecated
public class ProjectionPreferencesPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.projection.preferences"; //$NON-NLS-1$

  // The shared instance
  private static ProjectionPreferencesPlugin plugin;

  /**
   * The constructor
   */
  public ProjectionPreferencesPlugin() {
    // do nothing
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static ProjectionPreferencesPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns whether the LC2PC strategy is set by the user to Leaf-Strategy
   */
  public boolean isLC2PCLeafStrategy() {
    return new PreferenceHelper().isLC2PCLeafStrategy();
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of interfaces
   */
  public boolean transitionExchangeItemWhileInterfaceTransition() {
    return new PreferenceHelper().transitionExchangeItemWhileInterfaceTransition();
  }

  /**
   * Returns whether the functional elements should be transitioned while transition of component
   */
  public boolean transitionFunctionalElementWhileComponentTransition() {
    return new PreferenceHelper().transitionFunctionalElementWhileComponentTransition();
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of functional elements (FunctionPort, FunctionalExchange, Connection)
   */
  public boolean transitionExchangeItemWhileFunctionalTransition() {
    return new PreferenceHelper().transitionExchangeItemWhileFunctionalTransition();
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of communicationLinks
   */
  public boolean transitionExchangeItemWhileComponentTransition() {
    return new PreferenceHelper().transitionExchangeItemWhileComponentTransition();
  }

  /**
   * Returns whether the datatype should be transitioned while transition of exchange items
   */
  public boolean transitionDatatypeWhileExchangeItemTransition() {
    return new PreferenceHelper().transitionDatatypeWhileExchangeItemTransition();
  }

  /**
   * Returns whether the state machine should be transitioned while transition of component
   */
  public boolean transitionStateMachineWhileComponentTransition() {
    return new PreferenceHelper().transitionStateMachineWhileComponentTransition();
  }

  /**
   * Returns whether the interface should be transitioned while transition of component
   */
  public boolean transitionInterfaceWhileComponentTransition() {
    return new PreferenceHelper().transitionInterfaceWhileComponentTransition();
  }

  /**
   * Returns whether the interface generation should create standard port instead of flow ports
   */
  public boolean generateStandardPortRatherThanFlowPort() {
    return new PreferenceHelper().generateStandardPortRatherThanFlowPort();
  }

  /**
   * Returns whether the interface generation should generate ports and allocate interface to ports
   */
  public boolean generateComponentPort() {
    return new PreferenceHelper().generateComponentPort();
  }

}
