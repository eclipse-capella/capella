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
package org.polarsys.capella.core.projection.interfaces.rules;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_LogicalInterface extends Rule_Interface {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_LogicalInterface() {
    super(PaPackage.Literals.LOGICAL_INTERFACE_REALIZATION);
  }
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return element_p.eContainer().eClass().equals(CsPackage.Literals.INTERFACE_PKG)
      && EObjectExt.isContainedBy(element_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
  }

}
