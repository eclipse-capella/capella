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
package org.polarsys.capella.common.re.handlers.transformation;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.DefaultTransformationHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReTransformationHandler extends DefaultTransformationHandler {

  @Override
  public EObject getBestTracedElement(EObject source_p, IContext context_p, ISelectionContext sContext_p) {
    // If some choices, we should ask to the user to make a choice
    // Retrieve the first matching element
    for (EObject target : TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(source_p, context_p)) {
      if ((sContext_p == null) || sContext_p.match(source_p, target, context_p)) {
        return target;
      }
    }
    return null;
  }

}
