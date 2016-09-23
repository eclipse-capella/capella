/*******************************************************************************
 * Copyright (c) 2002, 2016 Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * utility class in order to copy Capella object
 * affecting a new id to each copied object.
 * see {@link Copier}
 */
public class CapellaCopier extends Copier {

  private static final long serialVersionUID = 1L;

  /**
   * Mainly duplicated from EcoreUtil.Copier class
   * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyAttribute(org.eclipse.emf.ecore.EAttribute, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject)
  {
    if (eObject.eIsSet(eAttribute))
    {
      if (FeatureMapUtil.isFeatureMap(eAttribute))
      {
        FeatureMap featureMap = (FeatureMap)eObject.eGet(eAttribute);
        for (int i = 0, size = featureMap.size(); i < size; ++i)
        {
          EStructuralFeature feature = featureMap.getEStructuralFeature(i);
          if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            Object value = featureMap.getValue(i);
            if (value != null)
            {
              copy((EObject)value);
            }
          }
        }
      }
      else if (eAttribute.isMany())
      {
        List<?> source = (List<?>)eObject.eGet(eAttribute);
        @SuppressWarnings("unchecked") List<Object> target = (List<Object>)copyEObject.eGet(getTarget(eAttribute));
        if (source.isEmpty())
        {
          target.clear();
        }
        else
        {
          target.addAll(source);
        }
      }
      else
      {        
        // We do not want to copy the unique identifier id.
        //This one has been generated during the instanciation of the new objects
        if (eAttribute != ModellingcorePackage.Literals.MODEL_ELEMENT__ID) {
          copyEObject.eSet(getTarget(eAttribute), eObject.eGet(eAttribute));
        }
      }
    }
  }
  
  /**
   * Copy a given {@link EObject} with an unique identifier.
   * @param eObject the {@link EObject} to copy.
   * @param copyRef boolean that indicates if should we includes references?
   * @return see description. 
   * @see ModelElement
   */
  public static EObject copyObject(final EObject eObject, final boolean copyRef) {
    
    EObject result = null;
    
    CapellaCopier copier = new CapellaCopier();
      result = copier.copy(eObject);
      if (copyRef) {
        copier.copyReferences();
      }
      
      return result;
  }
  
}
