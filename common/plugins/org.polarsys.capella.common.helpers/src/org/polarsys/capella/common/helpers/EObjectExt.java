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
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * EObject helpers
 */
public class EObjectExt extends EcoreUtil2 {

  /**
   * @param currentElement_p
   * @param targetType_p
   * @return Set<EObject>
   */
  public static Set<EObject> getAll(EObject currentElement_p, EClass targetType_p) {
    List<EClass> filters = new ArrayList<EClass>();
    return MDEQueries.getInstance().getAllQueries().getAllFiltered(currentElement_p, targetType_p, filters);
  }

  /**
   * This method retrieves all Object of metaClass 'metaClass_p' who have a EReference 'eRef_p' toward the Object 'eObjectRef_p'
   * @param eObjectRef_p : EObject
   * @param metaClass_p : EClass
   * @param eRef_p : EReference relation
   * @return
   */
  public static List<EObject> getReferencers(EObject eObjectRef_p, EClass metaClass_p, EReference eRef_p) {
    List<EObject> returnedList = new ArrayList<EObject>();
    for (EObject anObject : getReferencers(eObjectRef_p, eRef_p)) {
      if (anObject.eClass().equals(metaClass_p) || anObject.eClass().getEAllSuperTypes().contains(metaClass_p)) {
        returnedList.add(anObject);
      }
    }
    return returnedList;
  }

  /**
   * This method retrieves all Object who have a EReference toward the EObject 'eObjectRef_p'
   * @param eObjectRef_p : EObject
   * @return The list of referencing elements
   */
  public static List<EObject> getReferencers(EObject eObjectRef_p) {
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObjectRef_p);
    if (domain instanceof SemanticEditingDomain) {
      SemanticEditingDomain editingDomain = (SemanticEditingDomain) domain;
      return getReferencers(eObjectRef_p, null, editingDomain);
    }
    return Collections.emptyList();
  }

  /**
   * This method retrieves all Object who have a EReference 'eRef_p' toward the EObject 'eObjectRef_p'
   * @param eObjectRef_p : EObject
   * @param eRef_p : EReference relation
   * @return The list of referencing elements
   */
  public static List<EObject> getReferencers(EObject eObjectRef_p, EReference eRef_p) {
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObjectRef_p);
    if (domain instanceof SemanticEditingDomain) {
      SemanticEditingDomain editingDomain = (SemanticEditingDomain) domain;
      return getReferencers(eObjectRef_p, eRef_p, editingDomain);
    }
    return Collections.emptyList();
  }

  /**
   * This method retrieves all Object who have a EReference 'eRef_p' toward the Object 'eObjectRef_p'
   * @param eObjectRef_p : EObject
   * @param eRef_p : EReference relation (if null, all references are considered)
   * @param editingDomain_p : SemanticEditingDomain
   * @return The list of referencing elements
   */
  public static List<EObject> getReferencers(EObject eObjectRef_p, EReference eRef_p, SemanticEditingDomain editingDomain_p) {
    List<EObject> result = new ArrayList<EObject>();

    ECrossReferenceAdapter crossReferencer = editingDomain_p.getCrossReferencer();
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObjectRef_p, true);
    for (Setting setting : inverseReferences) {
      if (eRef_p == null || eRef_p.equals(setting.getEStructuralFeature())) {
        if (!result.contains(setting.getEObject())) {
          result.add(setting.getEObject());
        }
      }
    }

    return result;
  }

  /**
   * This method retrieves all Object who have any EReference from 'eRefs_p' toward the Object 'eObjectRef_p'
   * @param eObjectRef_p : EObject
   * @param eRefs_p : EReference relations list
   * @return
   */
  public static List<EObject> getReferencers(EObject eObjectRef_p, List<EReference> eRefs_p) {
    List<EObject> result = new ArrayList<EObject>();

    for (EReference ref : eRefs_p) {
      result.addAll(getReferencers(eObjectRef_p, ref));
    }

    return result;
  }

  /**
   * True if the given element is instance of at least one of the given eClasses
   * @param elt_p
   * @param expectedContainerType_p
   * @return
   */
  public static boolean isInstanceOf(EObject elt_p, List<EClass> expectedContainerType_p) {
    if ((null == elt_p) || expectedContainerType_p.isEmpty()) {
      return false;
    }
    for (EClass eClass : expectedContainerType_p) {
      if (eClass.isInstance(elt_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param eobjects_p a collection of eobjects from which the resource set will be retrieved
   * @return if all eobjects belong to the same resource set, this resource set is returned. Otherwise null is returned.
   */
  public static ResourceSet getCommonResourceSet(Collection<? extends EObject> eobjects_p) {
    ResourceSet rs = null;
    for (EObject obj : eobjects_p) {
      if (obj.eResource() != null) {
        if (null == rs) {
          rs = obj.eResource().getResourceSet();
        } else {
          if (!rs.equals(obj.eResource().getResourceSet())) {
            return null;
          }
        }
      }
    }
    return rs;
  }

  /**
   * @param resources_p a collection of resources from which the resource set will be retrieved
   * @return if all resources belong to the same resource set, this resource set is returned. Otherwise null is returned.
   */
  public static ResourceSet getCommonResourceSet(List<Resource> resources_p) {
    ResourceSet rs = null;
    for (Resource res : resources_p) {
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
