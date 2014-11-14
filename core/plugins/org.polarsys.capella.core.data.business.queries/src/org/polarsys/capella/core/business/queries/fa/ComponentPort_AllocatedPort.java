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
package org.polarsys.capella.core.business.queries.fa;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.fa.FaPackage;

/**
 */
public class ComponentPort_AllocatedPort extends Port_AllocatedPort {

  /**
   * 
   */
  public EClass getEClass() {
    return FaPackage.Literals.COMPONENT_PORT;
  }
}
