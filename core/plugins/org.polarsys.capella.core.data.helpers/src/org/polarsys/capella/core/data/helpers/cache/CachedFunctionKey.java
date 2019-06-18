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

  public CachedFunctionKey(Object... values) {
    this.values = values;

  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (this == obj)
      return true;
    if (obj instanceof CachedFunctionKey) {
      return Arrays.equals(values, ((CachedFunctionKey) obj).values);
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
