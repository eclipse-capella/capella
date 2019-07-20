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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Cache {

  private Map<Object, Object> cachedResult = new ConcurrentHashMap<>(2000, 0.75f, 1);

  public <P, R> R get(Function<P, R> function, P parameter) {
    CachedFunctionKey key = new CachedFunctionKey(function, parameter);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.apply(parameter);
      Optional<R> encapsulatedResult = getEncapsulatedResult(result);
      cachedResult.put(key, encapsulatedResult);
      return result;
    }
    @SuppressWarnings("unchecked")
    Optional<R> result = (Optional<R>) resultObject;
    return result.orElse(null);

  }

  public <P1, P2, R> R get(BiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {
    CachedFunctionKey key = new CachedFunctionKey(function, parameter1, parameter2);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.apply(parameter1, parameter2);
      Optional<R> encapsulatedResult = getEncapsulatedResult(result);
      cachedResult.put(key, encapsulatedResult);
      return result;
    }
    @SuppressWarnings("unchecked")
    Optional<R> result = (Optional<R>) resultObject;
    return result.orElse(null);

  }

  public <P1, P2, R> R getServiceInterpreter(BiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {
    CachedFunctionKey key = new CachedFunctionKey(function, parameter1, parameter2);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.apply(parameter1, parameter2);
      Optional<R> encapsulatedResult = getEncapsulatedResult(result);
      cachedResult.put(key, encapsulatedResult);
      return result;
    }
    @SuppressWarnings("unchecked")
    Optional<R> result = (Optional<R>) resultObject;
    return result.orElse(null);

  }

  @SuppressWarnings("unchecked")
  public <R> Optional<R> getEncapsulatedResult(R result) {
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
    return encapsulatedResult;
  }

  public void clearCache() {
    cachedResult.clear();
  }

}
