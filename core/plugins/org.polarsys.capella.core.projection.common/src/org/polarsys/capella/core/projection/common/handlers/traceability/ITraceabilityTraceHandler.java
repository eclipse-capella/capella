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
package org.polarsys.capella.core.projection.common.handlers.traceability;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public interface ITraceabilityTraceHandler extends ITraceabilityHandler {

  /**
   * Retrieve whether the given element is a trace
   */
  public boolean isTrace(EObject element_p, IContext context_p);

  /**
   * Retrieve source element of the given trace
   */
  public EObject getSourceElement(EObject trace_p, IContext context_p);

  /**
   * Retrieve target element of the given trace
   */
  public EObject getTargetElement(EObject trace_p, IContext context_p);

}
