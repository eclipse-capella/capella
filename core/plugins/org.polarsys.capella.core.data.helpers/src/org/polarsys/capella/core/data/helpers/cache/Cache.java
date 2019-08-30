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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Cache {

  private Map<Object, Object> cachedResult = new HashMap<>();

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <P, R> R get(Function<P, R> function, P parameter) {

    Couple<P, R> key = new Couple(parameter, function);

    if (!cachedResult.containsKey(key)) {

      R result = function.apply(parameter);

      if (result instanceof List<?>) {
        cachedResult.put(key, Collections.unmodifiableList((List<?>) result));
      } else if (result instanceof Set<?>) {
        cachedResult.put(key, Collections.unmodifiableSet((Set<?>) result));
      } else {
        cachedResult.put(key, result);
      }
      return result;
    }
    return (R) cachedResult.get(key);
  }

  public void clearCache() {
    cachedResult.clear();
  }
}
