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
  public void createAttachment(EObject sourceElement, EObject targetElement, IContext context) {
    AbstractTrace link;
    try {
      link = TigerRelationshipHelper.createTransfoLink(sourceElement, targetElement, context.getTransfo());

      EObject container = getAvailableTraceContainer(sourceElement, targetElement, context);
      if (container != null && container instanceof Namespace) {
        ((Namespace) container).getOwnedTraces().add((TransfoLink) link);
      }
    } catch (TransfoException exception_p) {
      //Nothing yet
    }
  }

  /**
   * @param sourceElement
   * @param targetElement
   * @param context
   * @return
   */
  protected EObject getAvailableTraceContainer(EObject sourceElement, EObject targetElement, IContext context) {
    Namespace namespace = null;
    EObject currentChoice = targetElement;
    //Retrieve a namespace according to transformed source and source's containers.
    while (currentChoice != null && namespace == null) {
      if (currentChoice instanceof Namespace) {
        namespace = (Namespace) currentChoice;
      } else {
        currentChoice = currentChoice.eContainer();
      }
    }
    return namespace;
  }

  @SuppressWarnings("unchecked")
  public List<EObject> retrieveTracedElements(EObject source, IContext context) {
    return (List<EObject>) Query.retrieveTransformedElements(source, context.getTransfo());
  }

  @SuppressWarnings("unchecked")
  public List<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz) {
    return (List<EObject>) Query.retrieveTransformedElements(source, context.getTransfo(), clazz);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<EObject> retrieveSourceElements(EObject source, IContext context) {
    return (List<EObject>) Query.retrieveSourceElements(source, context.getTransfo(), null);
  }

  protected boolean isValidLink(AbstractTrace trace, IContext context) {
    return !isTrace(trace, context);
  }

  public boolean isTrace(EObject element, IContext context) {
    return element instanceof TransfoLink;
  }

  public EObject getTargetElement(EObject trace, IContext context) {
    if (trace instanceof TransfoLink) {
      return ((TransfoLink) trace).getSourceElement();
    }
    return null;
  }

  public EObject getSourceElement(EObject trace, IContext context) {
    if (trace instanceof TransfoLink) {
      return ((TransfoLink) trace).getTargetElement();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isTraced(EObject element, IContext context) {
    return retrieveTracedElements(element, context).size() > 0;
  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context) {
    //Nothing here
  }

}
