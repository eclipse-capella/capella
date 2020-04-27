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
  public EObject getBestTracedElement(EObject source, IContext context, ISelectionContext sContext) {
    // If some choices, we should ask to the user to make a choice
    // Retrieve the first matching element
    for (EObject target : TraceabilityHandlerHelper.getInstance(context).retrieveTracedElements(source, context)) {
      if ((sContext == null) || sContext.match(source, target, context)) {
        return target;
      }
    }
    return null;
  }

}
