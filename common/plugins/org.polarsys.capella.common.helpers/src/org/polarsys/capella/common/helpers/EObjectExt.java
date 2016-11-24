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
package org.polarsys.capella.common.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.common.platform.sirius.ted.SemanticCrossReferencer;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * EObject helpers
 */
public class EObjectExt extends EcoreUtil2 {

  /**
   * @param currentElement
   * @param targetType
   * @return Set<EObject>
   */
  public static Set<EObject> getAll(EObject currentElement, EClass targetType) {
    List<EClass> filters = new ArrayList<EClass>();
    return MDEQueries.getInstance().getAllQueries().getAllFiltered(currentElement, targetType, filters);
  }

  /**
   * This method retrieves all Object of metaClass 'metaClass' who have a EReference 'eRef' toward the Object 'eObjectRef'
   * @param eObjectRef : EObject
   * @param metaClass : EClass
   * @param eRef : EReference relation
   * @return
   */
  public static List<EObject> getReferencers(EObject eObjectRef, EClass metaClass, EReference eRef) {
    List<EObject> returnedList = new ArrayList<EObject>();
    for (EObject anObject : getReferencers(eObjectRef, eRef)) {
      if (anObject.eClass().equals(metaClass) || anObject.eClass().getEAllSuperTypes().contains(metaClass)) {
        returnedList.add(anObject);
      }
    }
    return returnedList;
  }

  /**
   * This method retrieves all Object who have a EReference toward the EObject 'eObjectRef'
   * @param eObjectRef : EObject
   * @return The list of referencing elements
   */
  public static List<EObject> getReferencers(EObject eObjectRef) {
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObjectRef);
    if (domain instanceof SemanticEditingDomain) {
      SemanticEditingDomain editingDomain = (SemanticEditingDomain) domain;
      return getReferencers(eObjectRef, null, editingDomain);
    }
    return Collections.emptyList();
  }

  /**
   * This method retrieves all Object who have a EReference 'eRef' toward the EObject 'eObjectRef'
   * @param eObjectRef : EObject
   * @param eRef : EReference relation
   * @return The list of referencing elements
   */
  public static <T extends EObject> List<T> getReferencers(EObject eObjectRef, EReference eRef) {
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObjectRef);
    if (domain instanceof SemanticEditingDomain) {
      SemanticEditingDomain editingDomain = (SemanticEditingDomain) domain;
      return (List) getReferencers(eObjectRef, eRef, editingDomain);
    }
    return Collections.emptyList();
  }

  /**
   * This method retrieves all Object who have a EReference 'eRef' toward the Object 'eObjectRef'
   * @param eObjectRef : EObject
   * @param eRef : EReference relation (if null, all references are considered)
   * @param editingDomain : SemanticEditingDomain
   * @return The list of referencing elements
   */
  public static <T extends EObject> List<T> getReferencers(EObject eObjectRef, EReference eRef, SemanticEditingDomain editingDomain) {
    List<T> result = new ArrayList<T>();

    SemanticCrossReferencer crossReferencer = editingDomain.getCrossReferencer();
    if (eRef == null) {
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObjectRef, true);
      for (Setting setting : inverseReferences) {
        if (!result.contains(setting.getEObject())) {
          result.add((T) setting.getEObject());
        }
      }

    } else {
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObjectRef, eRef, true);
      for (Setting setting : inverseReferences) {
        if (!result.contains(setting.getEObject())) {
          result.add((T) setting.getEObject());
        }
      }
    }

    return result;
  }

  /**
   * This method retrieves all Object who have any EReference from 'eRefs' toward the Object 'eObjectRef'
   * @param eObjectRef : EObject
   * @param eRefs : EReference relations list
   * @return
   */
  public static List<EObject> getReferencers(EObject eObjectRef, List<EReference> eRefs) {
    List<EObject> result = new ArrayList<EObject>();

    for (EReference ref : eRefs) {
      result.addAll(getReferencers(eObjectRef, ref));
    }

    return result;
  }

  /**
   * True if the given element is instance of at least one of the given eClasses
   * @param elt
   * @param expectedContainerType
   * @return
   */
  public static boolean isInstanceOf(EObject elt, List<EClass> expectedContainerType) {
    if ((null == elt) || expectedContainerType.isEmpty()) {
      return false;
    }
    for (EClass eClass : expectedContainerType) {
      if (eClass.isInstance(elt)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param eObjects a collection of EObject(s) from which the resource set will be retrieved
   * @return if all EObject(s) belong to the same resource set, this resource set is returned. Otherwise null is returned.
   */
  public static ResourceSet getCommonResourceSet(Collection<? extends EObject> eObjects) {
    ResourceSet resourceSet = null;
    for (EObject obj : eObjects) {
      Resource eResource = obj.eResource();
      if (eResource != null) {
        if (null == resourceSet) {
          resourceSet = eResource.getResourceSet();
        } else {
          if (!resourceSet.equals(eResource.getResourceSet())) {
            return null;
          }
        }
      }
    }
    return resourceSet;
  }

  /**
   * @param resources a collection of resources from which the resource set will be retrieved
   * @return if all resources belong to the same resource set, this resource set is returned. Otherwise null is returned.
   */
  public static ResourceSet getCommonResourceSet(List<Resource> resources) {
    ResourceSet rs = null;
    for (Resource res : resources) {
      if (null == rs) {
        rs = res.getResourceSet();
      } else {
        if (!rs.equals(res.getResourceSet())) {
          return null;
        }
      }
    }
    return rs;
  }
}
