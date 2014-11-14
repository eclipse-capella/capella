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
package org.polarsys.capella.core.business.queries.pa;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.business.queries.fa.AbstractFunction_AvailableInStates;
import org.polarsys.capella.core.data.pa.PaPackage;

/**
 */
public class PhysicalFunction_AvailableInstates extends AbstractFunction_AvailableInStates {

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return PaPackage.Literals.PHYSICAL_FUNCTION;
  }
}
