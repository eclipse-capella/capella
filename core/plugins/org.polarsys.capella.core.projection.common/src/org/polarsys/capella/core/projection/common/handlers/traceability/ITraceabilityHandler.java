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
package org.polarsys.capella.core.projection.common.handlers.traceability;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.IHandler;

/**
 */
public interface ITraceabilityHandler extends IHandler {

  /**
   * Attach a traceability item between both elements
   */
  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p);

  /**
   * Retrieve all traced elements for the given element
   */
  public List<EObject> retrieveTracedElements(EObject source_p, IContext context_p);

  /**
   * Retrieve all traced elements for the given element
   */
  public List<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz_p);

  /**
   * Retrieve whether the given element is traced by an element
   */
  public boolean isTraced(EObject element_p, IContext context_p);

  /**
   * Retrieve all source elements for the given element
   */
  public List<EObject> retrieveSourceElements(EObject source_p, IContext context_p);

  /**
   * @param element_p
   * @return
   */
  public String getId(EObject element_p, IContext context_p);

}
