/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.handlers.location;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public interface ILocationHandler extends IHandler {

  /**
   * Get the location of the link's target element as chosen by the user
   */
  public EObject getCurrentLocation(CatalogElementLink link, IContext context);

  /**
   * Set the location of the link's target element as chosen by the user
   */
  public void setCurrentLocation(CatalogElementLink link, EObject container, IContext context);

  /**
   * Get the location of the  link's target element. Such locations are locations were there is no choice,
   * for instance a port will be contained in its parent.
   */
  public EObject getLocation(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context);

  /**
   * Get the default location which can be used to store the link's target element.
   */
  public EObject getDefaultLocation(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context);

  /**
   * Remove all locations which have been computed
   */
  public void cleanLocations(IContext context);

  /**
   * Retrieve the feature to store the target in the targetContainer
   * by default, it will be source.eContainingFeature
   */
  public EStructuralFeature getFeature(EObject source, EObject target, EObject targetContainer, IContext context);

}
