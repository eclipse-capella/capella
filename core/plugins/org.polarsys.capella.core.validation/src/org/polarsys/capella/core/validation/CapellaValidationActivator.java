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
package org.polarsys.capella.core.validation;

import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;
import org.polarsys.capella.core.validation.filter.CapellaConstraintFilter;

/**
 */
public class CapellaValidationActivator extends AbstractActivator {
  /**
   * Singleton instance.
   */
  private static CapellaValidationActivator __plugin;
  /**
   * Capella validator adapter.
   */
  private CapellaValidatorAdapter _capellaValidatorAdapter;

  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    _capellaValidatorAdapter = new CapellaValidatorAdapter();
    // Add a constraints filter, to disable all constraints that are not capella ones, e.g GMF ones.
    _capellaValidatorAdapter.getValidator().addConstraintFilter(new CapellaConstraintFilter());
    _capellaValidatorAdapter.initializeValidatorRegistry();

    ConstraintRegistry.getInstance().addConstraintListener(CapellaConstraintListener.getInstance());

    __plugin = this;
  }

  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __plugin = null;
    super.stop(context_p);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static CapellaValidationActivator getDefault() {
    return __plugin;
  }

  /**
   * Get the capella validator adapter.
   * @return a not <code>null</code> instance.
   */
  public CapellaValidatorAdapter getCapellaValidatorAdapter() {
    return _capellaValidatorAdapter;
  }
}
