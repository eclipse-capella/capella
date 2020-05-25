/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.helpers.cache;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Cache {

  private Map<Object, Object> cachedResult = new ConcurrentHashMap<>();

  @SuppressWarnings("unchecked")
  public <P, R> R get(Function<P, R> function, P parameter) {

    Couple<Function<P, R>, P> key = new Couple<>(function, parameter);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.apply(parameter);
      Optional<R> encapsulatedResult;

      if (result instanceof List<?>) {
        encapsulatedResult = (Optional<R>) Optional.of(Collections.unmodifiableList((List<?>) result));
      } else if (result instanceof Set<?>) {
        encapsulatedResult = (Optional<R>) Optional.of(Collections.unmodifiableSet((Set<?>) result));
      } else if (result instanceof Map<?, ?>) {
        encapsulatedResult = (Optional<R>) Optional.of(Collections.unmodifiableMap((Map<?, ?>) result));
      } else {
        encapsulatedResult = Optional.ofNullable(result);
      }
      cachedResult.put(key, encapsulatedResult);

      return result;
    }
    Optional<R> result = (Optional<R>) resultObject;

    return result.orElse(null);

  }

  public void clearCache() {
    cachedResult.clear();
  }
}
