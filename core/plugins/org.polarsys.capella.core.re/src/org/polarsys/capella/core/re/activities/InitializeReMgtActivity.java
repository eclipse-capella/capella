/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.activities;

import org.polarsys.capella.core.re.handlers.traceability.CapellaLocationTraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;

/**
 *
 */
public class InitializeReMgtActivity extends org.polarsys.capella.common.re.activities.InitializeReMgtActivity {

  public static final String ID = InitializeReMgtActivity.class.getCanonicalName();

  /**
   * Create default transformation traceability handler for common transition
   * @return
   */
  @Override
  protected IHandler createDefaultTraceabilityLocationHandler() {
    return new CompoundTraceabilityHandler(new CapellaLocationTraceabilityConfiguration());
  }
}
