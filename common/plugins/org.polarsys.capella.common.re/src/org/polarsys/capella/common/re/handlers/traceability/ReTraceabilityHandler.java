/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public ReTraceabilityHandler(CatalogElement source, String identifier) {
    super(identifier);
    this.source = source;
  }

  /**
   * @param origin
   * @param root
   * @param identifier
   */
  public ReTraceabilityHandler(CatalogElement origin, CatalogElement root, String identifier) {
    super(identifier);
    _origin = origin;
    source = root;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    initializeRootMappings(context);
    return super.init(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getLevelElement(EObject source, IContext context) {
    return this.source;
  }

  protected CatalogElementLink getOriginLink(CatalogElementLink link) {
    CatalogElementLink result = link;
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
  public String getId(EObject element, IContext context) {
    return super.getId(element, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeRootMappings(IContext context) {
    super.initializeRootMappings(context);

    if (_origin != null) { //Update replica
      System.out.println("origin != null");
    }
    if (source != null) {
      Collection<CatalogElement> sourceRE = ReplicableElementHandlerHelper.getInstance(context).getAllUsedReplicableElements(source);
      for (CatalogElement element : sourceRE) {

        for (CatalogElementLink link : element.getOwnedLinks()) {
          CatalogElementLink reLink = link;
          if (reLink.getOrigin() != null) {
            addMappings(reLink.getOrigin(), reLink.getTarget(), context);
          } else {
            addMappings(reLink, reLink.getTarget(), context);
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event, IContext context) {
    //Nothing here
  }

}
