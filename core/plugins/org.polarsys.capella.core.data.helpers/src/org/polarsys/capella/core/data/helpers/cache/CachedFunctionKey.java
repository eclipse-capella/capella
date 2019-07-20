/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.cache;

import java.util.Arrays;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class CachedFunctionKey {
  private Object[] values;
  int hashCode = 0;
  
  public CachedFunctionKey(Object... values) {
    this.values = values;
    hashCode = Arrays.deepHashCode(values);
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CachedFunctionKey) {
      CachedFunctionKey key = (CachedFunctionKey) obj;
      if (key.values != null && values != null) {
        return check(key.values, values);
      }
    }
    return false;
  }

  private boolean check(Object[] values2, Object[] values3) {
    if (values2.length == values3.length) {
      for (int i=0; i<values3.length; i++) {
        if (values2[i] instanceof Object[] && values3[i] instanceof Object[]) {
          if (!check((Object[])values2[i], (Object[])values3[i])) {
            return false;
          }
        } else if (values2[i]!=values3[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    String functionString = (null != values[0]) ? values[0].toString() : ICommonConstants.EMPTY_STRING;
    return new StringBuffer(functionString).append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER)
        .append(Arrays.toString(values)).append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER).toString();
  }

}
