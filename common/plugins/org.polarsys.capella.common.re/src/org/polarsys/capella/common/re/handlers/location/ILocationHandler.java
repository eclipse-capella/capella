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
package org.polarsys.capella.common.re.handlers.location;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public interface ILocationHandler extends IHandler {

  /**
   * The location of element after choices made by user 
   */
  public EObject getCurrentLocation(CatalogElementLink object_p, IContext context_p);

  /**
   * Set the location of element after choices made by user 
   */
  public void setCurrentLocation(CatalogElementLink object_p, EObject container_p, IContext context_p);

  /**
   * Retrieve the location for the given object_p.
   * Such location are locations were there is no choice, for instance a port will be contained in its parent.
   */
  public EObject getLocation(CatalogElementLink object_p, CatalogElementLink origin_p, IContext context_p);

  /**
   * The default location which can be used to store the link
   */
  public EObject getDefaultLocation(CatalogElementLink object_p, CatalogElementLink origin_p, IContext context_p);

  /**
   * Remove all locations which have been computed 
   */
  public void cleanLocations(IContext context_p);

  /**
   * Retrieve the feature to store the target_p in the targetContainer_p
   * by default, it will be source_p.eContainingFeature
   */
  public EStructuralFeature getFeature(EObject source_p, EObject target_p, EObject targetContainer_p, IContext context_p);

}
