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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

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
   * Retrieve target element of  the given trace
   */
  public EObject getTargetElement(EObject trace_p, IContext context_p);

}
