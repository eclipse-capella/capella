/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.re.handlers.selection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.selection.TransformationSelectionContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReSelectionContext extends TransformationSelectionContext {
  @Override
  public boolean match(EObject source, EObject target, IContext context) {
    return super.match(source, target, context);
  }
}
