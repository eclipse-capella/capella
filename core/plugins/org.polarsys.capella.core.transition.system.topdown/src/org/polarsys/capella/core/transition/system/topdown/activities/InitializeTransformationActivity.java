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
package org.polarsys.capella.core.transition.system.topdown.activities;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.TransformationConfiguration;

/**
 *
 */
public class InitializeTransformationActivity extends org.polarsys.capella.core.transition.system.activities.InitializeTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.InitializeTransformationActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultTraceabilityTransformationHandler() {
    ITraceabilityConfiguration configuration = new TransformationConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

}
