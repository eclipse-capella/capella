/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import java.util.function.BiFunction;

@FunctionalInterface
public interface CachedBiFunction<T1, T2, R> extends BiFunction<T1, T2, R> {

  /**
   * Applies this function to the given argument.
   *
   * @param t1
   *          the function 1st argument
   * @param t2
   *          the function 2nd argument
   * @return the function result
   */
  R withoutCache(T1 t1, T2 t2);

  @Override
  default R apply(T1 t1, T2 t2) {
    return ModelCache.getOrApply(this, t1, t2);
  }

  default R get(T1 t1, T2 t2) {
    return apply(t1, t2);
  }

  default void clearCache() {
    ModelCache.clearCache(this);
  }

}