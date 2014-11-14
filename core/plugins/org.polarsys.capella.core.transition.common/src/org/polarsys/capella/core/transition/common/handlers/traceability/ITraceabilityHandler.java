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
package org.polarsys.capella.core.transition.common.handlers.traceability;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public interface ITraceabilityHandler extends IHandler {

  public static ITraceabilityHandler DEFAULT = null;

  /**
   * Attach a traceability item between both elements
   */
  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p);

  /**
   * Retrieve all traced elements for the given element
   */
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p);

  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz);

  /**
   * Retrieve whether the given element is traced by an element
   */
  @Deprecated
  public boolean isTraced(EObject element_p, IContext context_p);

  /**
   * Retrieve all source elements for the given element
   */
  public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p);

  /**
   * @param element_p
   * @return
   */
  @Deprecated
  public String getId(EObject element_p, IContext context_p);
}
