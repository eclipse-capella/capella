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
   */
  public static <P, R> R getCache(Function<P, R> function, P parameter) {

    if (enabled) {
      return cache.get(function, parameter);
    }
    return function.apply(parameter);
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
}
