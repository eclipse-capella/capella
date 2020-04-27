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
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class CapellaTraceabilityHandler implements ITraceabilityHandler {

  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    if (targetElement_p != null) { // Allow transformation one to nothing
      //If not already traced, create a traceability link into it
      if (!retrieveTracedElements(sourceElement_p, context_p).contains(targetElement_p)) {
        createAttachment(sourceElement_p, targetElement_p, context_p);
      }
    }
  }

  public void createAttachment(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    //Nothing here
  }

  public String getId(EObject element_p, IContext context_p) {
    if (element_p instanceof ModelElement) {
      return ((ModelElement) element_p).getId();
    }
    return null;
  }

}
