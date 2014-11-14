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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public interface ITransformationHandler extends IHandler {

  /**
   * Return whether the source_p element is already or will be transformed after the transformation
   */
  public IStatus isOrWillBeTransformed(EObject source_p, IContext context_p);

  /**
   * Retrieve a root element containing the source_p. This root element should be traced in the transformed model (can be another element than the first
   * element of the eResource if this element is not traced)
   */
  public EObject getLevelElement(EObject source_p, IContext context_p);

  /**
   * Retrieve the best traced element of the given element, matching given selection context, according to the current context, and the current transformation
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p, ISelectionContext sContext_p);

  /**
   * Retrieve the target type of the source_p element with the transformation
   */
  public EClass getTargetType(EObject source_p, IContext context_p);

  public IStatus checkTransformRequired(EObject source_p, IContext context_p, EObject sourceElement);

  public IStatus checkTransformRequired(EObject source_p, IContext context_p, EObject sourceElement, EObject targetElement);

  /**
   * 
   * 
   */

  public EObject getBestTracedElement(EObject root_p, IContext context_p, EClass blockArchitecture_p);

  public EObject getBestTracedElement(EObject root_p, IContext context_p, EClass blockArchitecture_p, EObject element_p, EObject result_p);

}
