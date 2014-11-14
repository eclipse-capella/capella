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
package org.polarsys.capella.common.re.handlers.replicable;

import java.util.Collection;

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Handler used to create and manipulate ReplicableElement
 */
public interface IReplicableElementHandler extends IHandler {

  /**
   * Fill the target replicable element with informations of the source replicable element
   */
  public CatalogElement createInitialReplica(CatalogElement source_p, CatalogElement target_p, IContext context_p);

  /**
   * Create CatalogElementsLinks for the given ReplicableElement and given links
   */
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable_p, Collection<CatalogElementLink> setLinks_p, IContext context_p);

  /**
   * For a link of the source or target CatalogElement, retrieve the related link stored in the opposite replica
   * (source=>target, target=>source)
   */
  public CatalogElementLink getOppositeLink(CatalogElementLink link_p, IContext context);

}
