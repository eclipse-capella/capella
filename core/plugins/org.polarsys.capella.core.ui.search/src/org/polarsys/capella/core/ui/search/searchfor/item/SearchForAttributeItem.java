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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.swt.graphics.Image;

public class SearchForAttributeItem implements SearchForItem {
  // An attribute item can represent many attributes with the same name
  private Set<Object> attributes;

  public SearchForAttributeItem() {
    this.attributes = new HashSet<>();
  }

  /**
   * 
   * @param eObj
   * @return the search text matched from this search item
   */
  public String getTextToSearch(EObject eObj) {
    Object attribute = getAttributeFor(eObj);
    if (attribute instanceof EAttribute) {
      return (String) eObj.eGet((EAttribute) attribute);
    }
    return null;
  }

  @Override
  public String getText() {
    return ((EAttribute) attributes.iterator().next()).getName();
  }

  @Override
  public Image getImage() {
    return ExtendedImageRegistry.INSTANCE.getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EAttribute"));
  }

  @Override
  public Object getObject() {
    return attributes;
  }
  
  public void addAttribute(Object obj) {
    attributes.add(obj);
  }

  /**
   * Check if this search item covers the input attribute
   * 
   * @param attribute
   */
  public boolean cover(Object attribute) {
    return attributes.contains(attribute);
  }
  
  /**
   * 
   * @param eObj
   * @return the attribute compatible with the eObj
   */
  public Object getAttributeFor(EObject eObj) {
    for (Object att : attributes) {
      if (att instanceof EAttribute && eObj.eClass().getEAllAttributes().contains(att)) {
        return att;
      }
    }
    return null;
  }
  
}
