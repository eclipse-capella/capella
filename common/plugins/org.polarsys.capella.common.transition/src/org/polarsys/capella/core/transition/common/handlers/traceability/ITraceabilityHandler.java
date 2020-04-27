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
package org.polarsys.capella.core.transition.common.handlers.traceability;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ITraceabilityHandler extends IHandler {

  static ITraceabilityHandler DEFAULT = null;

  /**
   * Attach a traceability item between both elements
   */
  void attachTraceability(EObject sourceElement, EObject targetElement, IContext context);

  /**
   * Retrieve all traced elements for the given element
   */
  Collection<EObject> retrieveTracedElements(EObject source, IContext context);

  @Deprecated
  Collection<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz);

  /**
   * Retrieve whether the given element is traced by an element
   */
  @Deprecated
  boolean isTraced(EObject element, IContext context);

  /**
   * Retrieve all source elements for the given element
   */
  Collection<EObject> retrieveSourceElements(EObject source, IContext context);

  /**
   * @param element
   * @return
   */
  @Deprecated
  String getId(EObject element, IContext context);
}
