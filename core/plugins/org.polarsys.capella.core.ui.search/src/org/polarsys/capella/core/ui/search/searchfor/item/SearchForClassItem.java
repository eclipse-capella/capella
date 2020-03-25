/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor.item;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.ui.search.searchfor.GetImagesFromEClassUtil;
import org.polarsys.capella.core.ui.search.searchfor.ItemContributionManager;

/**
 * 
 * Search item for EClass
 */
public class SearchForClassItem implements SearchForItem {
  private Object obj;

  public SearchForClassItem(Object obj) {
    this.obj = obj;
  }

  /**
   * Check if this search item covers the input object
   * 
   * @param eObj
   */
  public boolean cover(Object object) {
    return object instanceof EObject && obj instanceof EClass && ((EObject) object).eClass() == obj;
  }

  @Override
  public String getText() {
    return ((EClass) obj).getName();
  }

  @Override
  public Image getImage() {
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    Image img = null;
    if (adapterFactory instanceof ComposedAdapterFactory) {
      img = GetImagesFromEClassUtil.getInstance().getImageForEClass((EClass) obj,
          (ComposedAdapterFactory) adapterFactory);
    }
    return img;
  }

  public String getUniqueID() {
    return ((EClass) obj).getEPackage().getNsURI() + "/" + ((EClass) obj).getName();
  }

  @Override
  public Object getObject() {
    return obj;
  }

  /**
   * 
   * @return all the attributes of this class. Ideal place to plug extended attribute search
   */
  public List<Object> getAttributes() {
    List<Object> attributes = ((EClass) obj).getEAllAttributes().stream().collect(Collectors.toList());
    Set<SearchForAttributeItem> contributedAttributeItems = ItemContributionManager.getInstance()
        .getContributedAttributeItems();
    for (SearchForAttributeItem item : contributedAttributeItems) {
      if (item.cover(obj)) {
        Object attributeObj = item.getObject();
        if (attributeObj instanceof Collection) {
          attributes.addAll((Collection) attributeObj);
        } else {
          attributes.add(attributeObj);
        }
      }
    }
    return attributes;
  }
}
