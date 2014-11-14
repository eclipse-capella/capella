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
package org.polarsys.capella.common.helpers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * Couple of [EObject - EObject] objects<br>
 * This class can be used to instantiate a multiple key as a Map key
 */
public class EObjectCouple extends Couple<EObject, EObject> {
  /**
   * Default constructor
   * @param key_p
   * @param value_p
   */
  public EObjectCouple(EReference key_p, EClass value_p) {
    super(key_p, value_p);
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj_p) {
    boolean cr = super.equals(obj_p);
    if (!cr && (obj_p instanceof EObjectCouple)) {
      EObjectCouple eObjectCouple = (EObjectCouple) obj_p;
      cr = (getKey() == eObjectCouple.getKey());
      cr = (cr) ? (getValue() == eObjectCouple.getValue()) : false;
    }
    return cr;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    // Based on Thinking In Java book : Overriding hashCode( ) chapter 11
    int result = 17;
    result = 37 * result + getKey().hashCode();
    result = 37 * result + getValue().hashCode();
    return result;
  }
}
