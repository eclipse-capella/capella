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
package org.polarsys.capella.core.transition.common.handlers.selection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationSelectionContext implements ISelectionContext {

  /**
   * {@inheritDoc}
   */
  public boolean match(EObject source_p, EObject target_p, IContext context_p) {
    EClass targetType = TransformationHandlerHelper.getInstance(context_p).getTargetType(source_p, context_p);
    if (!((targetType == null) || targetType.isInstance(target_p))) {
      return false;
    }
    return true;

  }
}
