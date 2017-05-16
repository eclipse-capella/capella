/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.InternalEList;

public class SemanticCrossReferencer extends ECrossReferenceAdapter {
  
  boolean proxyResolutionEnabled = true;

  public Collection<EStructuralFeature.Setting> getInverseReferences(EObject eObject, EStructuralFeature feature, boolean resolve) {
    Collection<EStructuralFeature.Setting> result = new ArrayList<EStructuralFeature.Setting>();

    if (feature == null) {
      return result;
    }

    if (resolve) {
      resolveAll(eObject);
    }

    EObject eContainer = eObject.eContainer();
    if ((eContainer != null) && feature.equals(eObject.eContainmentFeature())) {
      result.add(((InternalEObject) eContainer).eSetting(eObject.eContainmentFeature()));
    }

    Collection<EStructuralFeature.Setting> nonNavigableInverseReferences = inverseCrossReferencer.get(eObject);
    if (nonNavigableInverseReferences != null) {
      for (EStructuralFeature.Setting setting : nonNavigableInverseReferences) {
        if (feature.equals(setting.getEStructuralFeature())) {
          result.add(setting);
        }
      }
    }

    if (feature instanceof EReference) {
      EReference eReference = (EReference) feature;
      EReference eOpposite = eReference.getEOpposite();
      if ((eOpposite != null) && !eOpposite.isContainer() && eObject.eIsSet(eOpposite)) {
        if (eOpposite.isMany()) {
          Object collection = eObject.eGet(eOpposite);
          for (@SuppressWarnings("unchecked")
          Iterator<EObject> j = resolve() ? ((Collection<EObject>) collection).iterator() : ((InternalEList<EObject>) collection).basicIterator(); j.hasNext();) {
            InternalEObject referencingEObject = (InternalEObject) j.next();
            result.add(referencingEObject.eSetting(eReference));
          }
        } else {
          result.add(((InternalEObject) eObject.eGet(eOpposite, resolve())).eSetting(eReference));
        }
      }
    }
    return result;
  }
  
  public void enableProxyResolution(boolean enabled){
    proxyResolutionEnabled = enabled;
  }
  
  public boolean isProxyResolutionEnabled(){
    return proxyResolutionEnabled;
  }
}
