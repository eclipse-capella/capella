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
  public CatalogElement createInitialReplica(CatalogElement source, CatalogElement target, IContext context);

  /**
   * Create CatalogElementsLinks for the given ReplicableElement and given links
   */
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable, Collection<CatalogElementLink> setLinks, IContext context);

  /**
   * For a link of the source or target CatalogElement, retrieve the related link stored in the opposite replica
   * (source=>target, target=>source)
   */
  public CatalogElementLink getOppositeLink(CatalogElementLink link, IContext context);

}
