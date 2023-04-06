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

import java.util.function.Function;

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
   * @deprecated Use a CachedFunction instead to ensure that the cache is shared with all calls to this function 
   *         rather than having a cache per calls. see {@link #getOrApply(CachedFunction, Object) GetOrApply}
   */
  @Deprecated
  public static <P, R> R getCache(Function<P, R> function, P parameter) {

    if (enabled) {
      return cache.get(function, parameter);
    }
    return function.apply(parameter);
  }
  /**
   * 
   * @param function
   * @param parameter
   * @return If enabled, return the cached result if any or apply the function to the given parameter and cache the
   *         result before returning it.
   */
  static <P, R> R getOrApply(CachedFunction<P, R> function, P parameter) {
    if (enabled) {
      return cache.get(function, parameter);
    }
    return function.withoutCache(parameter);
  }

  /**
   * 
   * @param function
   * @param parameter
   * @return If enabled, return the cached result if any or apply the function to the given parameter and cache the
   *         result before returning it.
   */
  static <P1, P2, R> R getOrApply(CachedBiFunction<P1, P2, R> function, P1 parameter1, P2 parameter2) {
    if (enabled) {
      return cache.get(function, parameter1, parameter2);
    }
    return function.withoutCache(parameter1, parameter2);
  }


  /**
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
   * Removes all entries from this cache. The cache will be empty after this call returns.
   */
  public static void clearCache() {
    cache.clearCache();
  }

  /**
   * Removes this entry from this cache.
   */
  public static <T, R> void clearCache(CachedFunction<T, R> e) {
    cache.clearCache(e);
  }

  /**
   * Removes this entry from this cache.
   */
  public static <T1, T2, R> void clearCache(CachedBiFunction<T1, T2, R> e) {
    cache.clearCache(e);
  }
}
