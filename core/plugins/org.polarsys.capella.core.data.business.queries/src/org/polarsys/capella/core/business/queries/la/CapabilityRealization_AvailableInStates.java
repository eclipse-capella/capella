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
package org.polarsys.capella.core.business.queries.la;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.business.queries.interaction.AbstractCapability_AvailableInStates;
import org.polarsys.capella.core.data.la.LaPackage;

/**
 * Return current and available States and Modes of AbstractCapability
 */
public class CapabilityRealization_AvailableInStates extends AbstractCapability_AvailableInStates {
  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return LaPackage.Literals.CAPABILITY_REALIZATION;
  }
}
