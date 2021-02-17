/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.Objects;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Copied from org.polarsys.capella.core.data.helpers.cache.Couple available in org.polarsys.capella.core.data.helpers on v1.4.x branch. 
 * and introduced in commit 3352f4d77005bb11837d291b13a435f2b16406a8  [2427] Add a cache for faster category tools refreshes. 
 */
public class Couple<O1, O2> {

  private O1 o1;
  private O2 o2;

  /**
   * Constructor
   * 
   * @param o1
   * @param o2
   */
  public Couple(O1 o1, O2 o2) {
    this.o1 = o1;
    this.o2 = o2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(o1,o2);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Couple other = (Couple) obj;
    if (o1 == null) {
      if (other.o1 != null)
        return false;
    } else if (!o1.equals(other.o1)) {
      return false;
    }
    if (o2 == null) {
      if (other.o2 != null)
        return false;
    } else if (!o2.equals(other.o2)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String keyString = (null != o1) ? o1.toString() : ICommonConstants.EMPTY_STRING;
    String valueString = (null != o2) ? o2.toString() : ICommonConstants.EMPTY_STRING;
    return new StringBuffer(keyString).append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER).append(valueString)
        .append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER).toString();
  }
}
