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

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelCache {

  private static final Cache cache = new Cache();

  private static boolean enabled = false;

  private ModelCache() {
    // To hide the implicit public on
  }

  /**
   * 
   * @param function
   * @param parameter
   * @return If enabled, return the cached result if any or apply the function to the given parameter and cache the
   *         result before returning it.
   */
  public static <P, R> R getCache(Function<P, R> function, P parameter) {

    if (enabled) {
      return cache.get(function, parameter);
    }
    return function.apply(parameter);
  }

  /**
   * 
   * @param function
   * @param parameter1
   * @param parameter2
   * @return If enabled, return the cached result if any or apply the function to the given parameters and cache the
   *         result before returning it.
   */
  public static <P1, P2, R> R getCache(BiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {

    if (enabled) {
      return cache.get(function, parameter1, parameter2);
    }
    return function.apply(parameter1, parameter2);
  }

  /**
   * 
   * @param function
   * @param parameter1
   * @param parameter2
   * @return If enabled, return the cached result if any or apply the function to the given parameters and cache the
   *         result before returning it.
   */
  public static <P1, P2, R> R getServiceInterpreterCache(BiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {

    if (enabled) {
      return cache.getServiceInterpreter(function, parameter1, parameter2);
    }
    return function.apply(parameter1, parameter2);
  }

  /**
   * 
   * Enable the cache.
   */
  public static void enable() {
    enabled = true;
  }

  /**
   * Disable the cache and remove all the entries from it.
   */
  public static void disable() {
    enabled = false;
    cache.clearCache();

  }

  /**
   * 
   * @return true if the cache is enabled.
   */
  public static boolean isEnabled() {
    return enabled;
  }

  /**
   * 
   * @return only the CachedFunctionKey keys
   */
  public static Set<CachedFunctionKey> getCachedFunctionKeys() {
    return cache.getCacheKeys().stream().filter(CachedFunctionKey.class::isInstance).map(key -> (CachedFunctionKey) key)
        .collect(Collectors.toSet());
  }

  /**
   * 
   * @return all the Cache Keys
   */
  public static Set<Object> getCacheKeys() {
    return cache.getCacheKeys();
  }

  /**
   * Removes all entries from this cache. The cache will be empty after this call returns.
   */
  public static void clearCache() {
    cache.clearCache();
  }
}
