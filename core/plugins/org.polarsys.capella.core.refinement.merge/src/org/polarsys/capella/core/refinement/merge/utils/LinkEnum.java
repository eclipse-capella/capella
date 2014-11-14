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
package org.polarsys.capella.core.refinement.merge.utils;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 *
 */
public enum LinkEnum {

  MERGE_LINK (
      InteractionPackage.Literals.MERGE_LINK 
  ), 
  REFINEMENT_LINK(
      InteractionPackage.Literals.REFINEMENT_LINK
  );
  
  /** number of linked elements for such case (for nominal case)*/
  private EClass _eClass;
  
  /** Constructor */
  LinkEnum(EClass eClass) {
    _eClass = eClass;
  }
  
  /** accessor on the literal */
  public String getLiteral() {
    return _eClass.getName();
  }

  /** accessor on the EClass field of the enum */
  public EClass getEClass() {
    return _eClass;
  }
  
  /** check the type of a given AbstractTrace */
  public boolean isSameType(AbstractTrace tr_p) {
    return _eClass == tr_p.eClass();
  }
  
}
