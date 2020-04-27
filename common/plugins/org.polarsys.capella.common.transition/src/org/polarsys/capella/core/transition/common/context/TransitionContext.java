/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.context;

/**
 *
 */
import java.util.Collection;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.transformation.context.GenericTransformationContext;

public class TransitionContext extends GenericTransformationContext {

  public static final IContext EMPTY_CONTEXT = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return System.identityHashCode(this) + "\n" + super.toString();
  }

  @Override
  public void reset() {
    for (Object key : getKeys()) {
      Object value = get(key);
      if ((value != null) && (value instanceof IHandler)) {
        ((IHandler) value).dispose(this);
      }
    }

    //Sometimes handler doesn't dispose correctly their created collections. empty them
    for (Object key : getKeys()) {
      Object value = get(key);
      if ((value != null) && (value instanceof Collection<?>)) {
        ((Collection<?>) value).clear();
      }
    }

    super.reset();
  }
}
