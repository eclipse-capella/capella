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

package org.polarsys.capella.core.model.helpers.refmap;

/**
 */
public class Pair<A, B> {
  private final A firstValue;
  private final B secondValue;

  public Pair(A firstValue, B secondValue) {
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }

  @Override
  public int hashCode() {
    return firstValue.hashCode() + (31 * secondValue.hashCode());
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair<?, ?>) {
      Pair<?, ?> p = (Pair<?, ?>) obj;
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
    return firstValue;
  }

  /**
   * @return the _secondValue
   */
  public B getSecondValue() {
    return secondValue;
  }
}
