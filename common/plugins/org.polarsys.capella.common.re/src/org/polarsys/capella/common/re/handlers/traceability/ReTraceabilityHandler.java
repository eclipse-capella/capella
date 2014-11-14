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
package org.polarsys.capella.common.re.handlers.traceability;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyListener;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReTraceabilityHandler extends LevelBasedTraceabilityHandler implements INotifyListener {

  CatalogElement _origin;
  CatalogElement source;

  public ReTraceabilityHandler(CatalogElement source_p, String identifier_p) {
    super(identifier_p);
    source = source_p;
  }

  /**
   * @param origin_p
   * @param root_p
   * @param identifier_p
   */
  public ReTraceabilityHandler(CatalogElement origin_p, CatalogElement root_p, String identifier_p) {
    super(identifier_p);
    _origin = origin_p;
    source = root_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    initializeRootMappings(context_p);
    return super.init(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getLevelElement(EObject source_p, IContext context_p) {
    return source;
  }

  protected CatalogElementLink getOriginLink(CatalogElementLink link_p) {
    CatalogElementLink result = link_p;
    while (result.getOrigin() != null) {
      result = result.getOrigin();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public String getId(EObject element_p, IContext context_p) {
    if (element_p instanceof CatalogElementLink) {
      System.out.println(0);
    }
    return super.getId(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeRootMappings(IContext context_p) {
    super.initializeRootMappings(context_p);

    if (_origin != null) { //Update replica
      System.out.println("origin != null");
    }
    if (source != null) {
      Collection<CatalogElement> sourceRE = ReplicableElementHandlerHelper.getInstance(context_p).getAllUsedReplicableElements(source);
      for (CatalogElement element : sourceRE) {

        for (CatalogElementLink link : element.getOwnedLinks()) {
          CatalogElementLink reLink = link;
          if (reLink.getOrigin() != null) {
            addMappings(reLink.getOrigin(), reLink.getTarget(), context_p);
          } else {
            addMappings(reLink, reLink.getTarget(), context_p);
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event_p, IContext context_p) {

  }

}
