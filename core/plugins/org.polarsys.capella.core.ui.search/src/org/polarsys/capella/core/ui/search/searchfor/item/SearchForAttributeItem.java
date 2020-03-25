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
import java.util.HashSet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.swt.graphics.Image;
@SuppressWarnings("rawtypes")
public class SearchForAttributeItem implements SearchForItem {
  private Object attribute;

  public SearchForAttributeItem() {
    // An attribute item can represent many EAttributes with the same name
    this.attribute = new HashSet<>();
  }

  /**
   * 
   * @param eObj
   * @return the search text matched from this search item
   */
  public String getTextToSearch(EObject eObj) {
    Object att = getAttributeFor(eObj);
    if (att instanceof EAttribute) {
      return (String) eObj.eGet((EAttribute) att);
    }
    return null;
  }

  @Override
  public String getText() {
    if (attribute instanceof Collection) {
      Object anAttribute = ((Collection) attribute).iterator().next();
      if (anAttribute instanceof EAttribute) {
        return ((EAttribute) anAttribute).getName();
      }
    }
    return null;
  }

  @Override
  public Image getImage() {
    return ExtendedImageRegistry.INSTANCE.getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EAttribute"));
  }

  @Override
  public Object getObject() {
    return attribute;
  }

  @SuppressWarnings("unchecked")
  public void addAttribute(Object obj) {
    if (attribute instanceof Collection) {
      ((Collection) attribute).add(obj);
    }
  }

  public boolean cover(Object object) {
    return getAttributeFor(object) != null;
  }
  
  
  public boolean represent(Object att) {
    if (attribute instanceof Collection) {
      return ((Collection) attribute).contains(att);
    }
    return attribute == att;
  }
  
  /**
   * 
   * @param eObj
   * @return the attribute compatible with the eObj
   */
  public Object getAttributeFor(Object obj) {
    if (obj instanceof EObject && attribute instanceof Collection) {
      for (Object att : ((Collection) attribute)) {
        if (att instanceof EAttribute && ((EObject) obj).eClass().getEAllAttributes().contains(att)) {
          return att;
        }
      }
    }
    return null;
  }  
}
