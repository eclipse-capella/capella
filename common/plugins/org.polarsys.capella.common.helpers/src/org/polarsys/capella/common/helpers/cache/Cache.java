/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers.cache;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cache {

  private Map<Object, Object> cachedResult = new ConcurrentHashMap<>();

  /**
   * 
   * @param <P> type of parameter
   * @param <R> type of result
   * @param function
   * @param parameter
   * @return cached result of function
   * 
   * Warning : It's recommended to use a CachedFunction instead and call {@link #get(CachedFunction, Object) }
   */
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

  @SuppressWarnings("unchecked")
  public <P, R> R get(CachedFunction<P, R> function, P parameter) {

    Couple<Function<P, R>, P> key = new Couple<>(function, parameter);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.withoutCache(parameter);
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

  @SuppressWarnings("unchecked")
  public <P1, P2, R> R get(CachedBiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {

    Triplet<BiFunction<P1, P2, R>, P1, P2> key = new Triplet<>(function, parameter1, parameter2);
    Object resultObject = cachedResult.get(key);
    if (resultObject == null) {
      R result = function.withoutCache(parameter1, parameter2);
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

  public <T, R> void clearCache(CachedFunction<T, R> f) {
	  List<Object> toBeRemoved =cachedResult.keySet().stream().filter(key -> key instanceof Couple).filter(couple -> ((Couple)couple).getFirst() == f).collect(Collectors.toList()) ;
	  for(Object keyToBeRemoved : toBeRemoved) {
		  cachedResult.remove(keyToBeRemoved);
	  }
  }

  public <T1, T2, R> void clearCache(CachedBiFunction<T1, T2, R> f) {
	  List<Object> toBeRemoved =cachedResult.keySet().stream().filter(key -> key instanceof Triplet).filter(triplet -> ((Triplet)triplet).getFirst() == f).collect(Collectors.toList()) ;
	  for(Object keyToBeRemoved : toBeRemoved) {
		  cachedResult.remove(keyToBeRemoved);
	  }
  }
}
