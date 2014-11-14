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
package org.polarsys.capella.core.re.activities;

import org.polarsys.capella.core.re.handlers.traceability.CapellaMatchConfiguration;
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
  protected IHandler createDefaultTraceabilityTransformationHandler() {
    return new CompoundTraceabilityHandler(new CapellaMatchConfiguration());
  }
}
