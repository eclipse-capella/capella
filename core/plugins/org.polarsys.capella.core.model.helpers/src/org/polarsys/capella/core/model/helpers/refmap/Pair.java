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
package org.polarsys.capella.core.model.helpers.refmap;

/**
 */
public class Pair<A, B> {
  private final A _firstValue;
  private final B _secondValue;

  public Pair(A firstValue, B secondValue) {
    _firstValue = firstValue;
    _secondValue = secondValue;
  }

  @Override
  public int hashCode() {
    return _firstValue.hashCode() + (31 * _secondValue.hashCode());
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj_p) {
    if (obj_p instanceof Pair<?, ?>) {
      Pair<?, ?> p = (Pair<?, ?>) obj_p;
      if (p.getFirstValue().equals(getFirstValue()) && p.getSecondValue().equals(getSecondValue())) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the _firstValue
   */
  public A getFirstValue() {
    return _firstValue;
  }

  /**
   * @return the _secondValue
   */
  public B getSecondValue() {
    return _secondValue;
  }
}
