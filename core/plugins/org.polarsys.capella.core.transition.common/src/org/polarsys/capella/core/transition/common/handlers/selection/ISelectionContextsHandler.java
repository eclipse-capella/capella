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
package org.polarsys.capella.core.transition.common.handlers.selection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ISelectionContextsHandler extends IHandler {

  /**
   * Returns the given SelectionContext
   */
  ISelectionContext getSelectionContext(IContext context, String id);

  /**
   * Returns the given SelectionContext according to the given element
   */
  ISelectionContext getSelectionContext(IContext context, String id, EObject eContext, EObject result);

}
