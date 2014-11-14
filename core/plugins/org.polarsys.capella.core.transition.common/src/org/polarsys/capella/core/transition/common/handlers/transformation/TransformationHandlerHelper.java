/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.transformation;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationHandlerHelper {

  public static ITransformationHandler getInstance(IContext context_p) {
    ITransformationHandler handler = (ITransformationHandler) context_p.get(ITransitionConstants.TRANSFORMATION_HANDLER);
    if (handler == null) {
      handler = new DefaultTransformationHandler();
      handler.init(context_p);
      context_p.put(ITransitionConstants.TRANSFORMATION_HANDLER, handler);
    }
    return handler;
  }

}
