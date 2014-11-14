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
package org.polarsys.capella.core.business.queries.cs;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.business.queries.fa.Port_AllocatedPort;
import org.polarsys.capella.core.data.cs.CsPackage;

/**
 */
public class PhysicalPort_AllocatedFunctionPorts extends Port_AllocatedPort {

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_PORT;
  }
}
