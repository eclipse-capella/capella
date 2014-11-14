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
package org.polarsys.capella.core.projection.common.handlers.traceability;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Traceability using a HardLink reference between both elements (default implementation use a transfoLink)
 */
public class TraceabilityByLinksHandler extends CapellaTraceabilityHandler {

  @Override
  public void createAttachment(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    AbstractTrace link;
    try {
      link = TigerRelationshipHelper.createTransfoLink(sourceElement_p, targetElement_p, context_p.getTransfo());

      EObject container = getAvailableTraceContainer(sourceElement_p, targetElement_p, context_p);
      if (container != null && container instanceof Namespace) {
        ((Namespace) container).getOwnedTraces().add((TransfoLink) link);
      }
    } catch (TransfoException exception_p) {
      //Nothing yet
    }
  }

  /**
   * @param sourceElement_p
   * @param targetElement_p
   * @param context_p
   * @return
   */
  protected EObject getAvailableTraceContainer(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    Namespace namespace = null;
    EObject currentChoice = targetElement_p;
    //Retrieve a namespace according to transformed source and source's containers.
    while (currentChoice != null && namespace == null) {
      if (currentChoice != null && currentChoice instanceof Namespace) {
        namespace = (Namespace) currentChoice;
      } else {
        currentChoice = currentChoice.eContainer();
      }
    }
    return namespace;
  }

  @SuppressWarnings("unchecked")
  public List<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
    return (List<EObject>) Query.retrieveTransformedElements(source_p, context_p.getTransfo());
  }

  @SuppressWarnings("unchecked")
  public List<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz_p) {
    return (List<EObject>) Query.retrieveTransformedElements(source_p, context_p.getTransfo(), clazz_p);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
    return (List<EObject>) Query.retrieveSourceElements(source_p, context_p.getTransfo(), null);
  }

  protected boolean isValidLink(AbstractTrace trace_p, IContext context_p) {
    return !isTrace(trace_p, context_p);
  }

  public boolean isTrace(EObject element_p, IContext context_p) {
    return element_p instanceof TransfoLink;
  }

  public EObject getTargetElement(EObject trace_p, IContext context_p) {
    if (trace_p instanceof TransfoLink) {
      return ((TransfoLink) trace_p).getSourceElement();
    }
    return null;
  }

  public EObject getSourceElement(EObject trace_p, IContext context_p) {
    if (trace_p instanceof TransfoLink) {
      return ((TransfoLink) trace_p).getTargetElement();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isTraced(EObject element_p, IContext context_p) {
    return retrieveTracedElements(element_p, context_p).size() > 0;
  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context_p) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context_p) {
    //Nothing here
  }

}
