/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Singleton class used in order to store semantic objects
 * to delete from meta-model (and thus their Links) during
 * the merge process.
 *
 */
public class MergeGarbage {
  
  /** singleton instance */
  final public static MergeGarbage INSTANCE = new MergeGarbage();
  
  /** {@link EObject} to remove */
  protected List<EObject> _removedObjects;
  
  private MergeGarbage() {
    _removedObjects = new ArrayList<EObject>();
  }

  /**
   * Add a {@link Collection} of {@link EObject} to the merge garbage
   * @param col
   */
  public void addAll(Collection<EObject> col) {
    _removedObjects.addAll(col);
    return;
  }
  
  /**
   * Add an {@link EObject} to the merge garbage
   * @param eObject
   */
  public void add(EObject eObject) {
    _removedObjects.add(eObject);
    
    return;
  }
  
  /**
   * Use Merge garbage as tool in order to delete some {@link EObject}
   * @param objects
   */
  @SuppressWarnings("unchecked")
  public void delete(List<EObject> objects) {
    
    EObject container = null;
    EStructuralFeature feature;
    EList<EObject> list = null;
    for (EObject eobject: objects) {
      container = eobject.eContainer();
      if (null != container) {
        feature = eobject.eContainingFeature();
        list = (EList<EObject>)container.eGet(feature);
        list.remove(eobject);
      }
    }
    
    clear(objects);
    
    return;
  }
  
  private void clear(List<EObject> objects) {
    
    // Ensure that Link to the removed object are well cleaned
    for (EObject eobject: objects) {
      if ( 
          ModellingcorePackage.Literals.ABSTRACT_TRACE.isSuperTypeOf(eobject.eClass())
      ) {
        LinkUtils.deleteLink((AbstractTrace) eobject);
      } else if (
          ModellingcorePackage.Literals.TRACEABLE_ELEMENT.isSuperTypeOf(eobject.eClass())
      ) {
        LinkUtils.removeAllLinksOn((TraceableElement) eobject);
      }
      
    }
    
    // delete the objects using the delete command
    MergeUtils.deleteElements(objects);
    
    return;
  }
  
  
  public void clear() {
    
    clear(_removedObjects);
        
    //clear the list
    _removedObjects.clear();
    
    return;
  }
  
}
