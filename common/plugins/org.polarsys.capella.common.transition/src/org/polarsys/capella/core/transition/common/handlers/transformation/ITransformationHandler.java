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
   * Return whether the source element is already or will be transformed after the transformation
   */
  IStatus isOrWillBeTransformed(EObject source, IContext context);

  /**
   * Retrieve a root element containing the source. This root element should be traced in the transformed model (can be another element than the first
   * element of the eResource if this element is not traced)
   */
  EObject getLevelElement(EObject source, IContext context);

  /**
   * Retrieve the best traced element of the given element, matching given selection context, according to the current context, and the current transformation
   */
  EObject getBestTracedElement(EObject source, IContext context, ISelectionContext sContext);

  /**
   * Retrieve the target type of the source element with the transformation
   */
  EClass getTargetType(EObject source, IContext context);

  IStatus checkTransformRequired(EObject source, IContext context, EObject sourceElement);

  IStatus checkTransformRequired(EObject source, IContext context, EObject sourceElement, EObject targetElement);

  /**
   * 
   * 
   */

  EObject getBestTracedElement(EObject root, IContext context, EClass blockArchitecture);

  EObject getBestTracedElement(EObject root, IContext context, EClass blockArchitecture, EObject element, EObject result);

  void postTransformElement(EObject element, EObject result, IContext context);

}
