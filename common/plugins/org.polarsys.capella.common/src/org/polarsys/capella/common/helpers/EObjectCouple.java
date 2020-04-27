/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param key
   * @param value
   */
  public EObjectCouple(EReference key, EClass value) {
    super(key, value);
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    boolean cr = super.equals(obj);
    if (!cr && (obj instanceof EObjectCouple)) {
      EObjectCouple eObjectCouple = (EObjectCouple) obj;
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
