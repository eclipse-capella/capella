/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.IHandler;

/**
 */
public interface ITransformationHandler extends IHandler {

  /**
   * Return whether the source_p element is already or will be transformed after the transformation
   */
  public IStatus isOrWillBeTransformed(EObject source_p, IContext context_p);

  /**
   * Return whether the source_p element is already or will be transformed after the transformation
   */
  public IStatus isOrWillBeTransformedTo(EObject source_p, IContext context_p, EClass clazz);

  /**
   * Retrieve the best traced element of the given element, according to the current context, and the current transformation
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p);

  /**
   * Retrieve the best traced element of the given element, typed by class_p, according to the current context, and the current transformation
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p, EClass class_p);

  /**
   * Retrieve the target type of the source_p element with the transformation
   */
  public EClass getTargetType(EObject source_p, IContext context_p);

}
