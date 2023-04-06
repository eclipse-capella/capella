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

import java.util.function.Function;

@FunctionalInterface
public interface CachedFunction<T, R> extends Function<T, R> {

  /**
   * Applies this function to the given argument.
   *
   * @param t
   *          the function argument
   * @return the function result
   */
  R withoutCache(T t);

  @Override
  default R apply(T t) {
    return ModelCache.getOrApply(this, t);
  }

  default R get(T t) {
    return apply(t);
  }

  default void clearCache() {
    ModelCache.clearCache(this);
  }

}