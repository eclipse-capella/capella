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

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class ModelCache {

  private static final Cache cache = new Cache();

  private static boolean enabled = false;
  
  private static ReentrantLock lock = new ReentrantLock();
  
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
    lock.lock();
    try {
      if (enabled) {
        return cache.get(function, parameter);
      }
      return function.apply(parameter);
      
    } finally {
      lock.unlock();
    }
  }

  /**
   * Enable the cache.
   */
  public static void enable() {
    lock.lock();
    enabled = true;
    lock.unlock();
  }

  /**
   * Disable the cache and remove all the entries from it.
   */
  public static void disable() {
    lock.lock();
    enabled = false;
    cache.clearCache();
    lock.unlock();
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
    lock.lock();
    cache.clearCache();
    lock.unlock();
  }
}
