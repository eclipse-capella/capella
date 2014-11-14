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
package org.polarsys.capella.common.re.handlers.attributes;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IAttributeHandler extends IHandler {

  public boolean isUpdatable(EObject element_p, EStructuralFeature feature_p, IContext context_p);

  public Collection<EStructuralFeature> getNonUpdatableFeatures(EObject element_p, IContext context_p);

  public void setName(EObject object_p, String value_p, IContext context_p, IPropertyContext pContext_p);

  public String getName(EObject object_p, IContext context_p, IPropertyContext pContext_p);

  public boolean hasCustomName(EObject object_p, IContext context_p);

  public Collection<EObject> getCustoms(IContext context_p);

  public String getCustomName(EObject object_p, IContext context_p);

  /**
   * @param object_p
   * @param context_p 
   * @return
   */
  public boolean isManualSuffixable(Object object_p, IContext context_p);

  public boolean isSuffixable(Object object_p, IContext context_p);

  public void setManualSuffixable(EObject object_p, boolean value_p, IContext context_p);

  public void setSuffixable(EObject object_p, boolean value_p, IContext context_p);

  /**
   * Returns whether an element can be suffixed
   */
  public boolean isSuffixableElement(Object object_p, IContext context_p);

  public EObject getLocation(CatalogElementLink object_p, CatalogElementLink origin_p, IContext context_p);

  public EObject getCurrentLocation(CatalogElementLink object_p, IContext context_p);

  public void setCurrentLocation(CatalogElementLink object_p, EObject container_p, IContext context_p);

  public EObject getDefaultLocation(CatalogElementLink object_p, CatalogElementLink origin_p, IContext context_p);

  /**
   * @param replicable_p
   * @param setLinks_p
   * @param context_p
   * @return 
   */
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable_p, HashSet<CatalogElementLink> setLinks_p, IContext context_p);

  /**
   * @param element_p
   * @param value_p
   * @param iContext_p
   * @param pContext_p
   */
  public void unsetName(EObject element_p, String value_p, IContext iContext_p, IPropertyContext pContext_p);

  /**
   * @param link_p
   * @return
   */
  public CatalogElementLink getOppositeLink(CatalogElementLink link_p, IContext context);

}
