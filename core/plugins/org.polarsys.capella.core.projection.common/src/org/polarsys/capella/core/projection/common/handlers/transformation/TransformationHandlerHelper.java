/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.transformation;

import org.polarsys.capella.core.projection.common.constants.ITransitionConstants;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public class TransformationHandlerHelper {

  public static ITransformationHandler getInstance(IContext context_p) {
    ITransformationHandler handler = (ITransformationHandler) context_p.get(ITransitionConstants.TRANSFORMATION_HANDLER);
    if (handler == null) {
      handler = new TigerTransformationHandler();
      handler.init(context_p);
      context_p.put(ITransitionConstants.TRANSFORMATION_HANDLER, handler);
    }
    return handler;
  }

}
