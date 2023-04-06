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
package org.polarsys.capella.common.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.polarsys.capella.common.helpers.cache.CachedBiFunction;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * EObject helpers
 */
public class EObjectExt extends EcoreUtil2 {

  static CachedBiFunction<EObject, EClass, Set<EObject>> getAll_withEClass = MDEQueries.getInstance()
      .getAllQueries()::getAll;
  static CachedBiFunction<EObject, Predicate<EObject>, Set<EObject>> getAll_withPredicate = MDEQueries.getInstance()
      .getAllQueries()::getAll;

  /**
   * @param currentElement
   * @param targetType
   * @return Set<EObject>
   */
  public static Set<EObject> getAll(EObject currentElement, EClass targetType) {
    return new LinkedHashSet<EObject>(getAll_withEClass.get(currentElement, targetType));
  }

  /**
   * @param currentElement
   * @param targetType
   * @return Set<EObject>
   */
  public static Set<EObject> getAllFiltered(EObject currentElement, EClass targetType, List<EClass> filters) {
    return MDEQueries.getInstance().getAllQueries().getAllFiltered(currentElement, targetType, filters);
  }

  /**
   * @param currentElement
   * @param targetType
   * @return Set<EObject>
   */
  public static Set<EObject> getAll(EObject currentElement, Predicate<EObject> predicate) {
    return new LinkedHashSet<EObject>(getAll_withPredicate.get(currentElement, predicate));
  }

  /**
   * This method retrieves all Object of metaClass 'metaClass' who have a EReference 'eRef' toward the Object
   * 'eObjectRef'
   * 
   * @param eObjectRef
   *          : EObject
   * @param metaClass
   *          : EClass
   * @param eRef
   *          : EReference relation
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
   * 
   * @param eObjectRef
   *          : EObject
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
   * 
   * @param eObjectRef
   *          : EObject
   * @param eRef
   *          : EReference relation
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
   * 
   * @param eObjectRef
   *          : EObject
   * @param eRef
   *          : EReference relation (if null, all references are considered)
   * @param editingDomain
   *          : SemanticEditingDomain
   * @param ignoreDerivedFeature
   *          : whether derived feature is ignored while finding referencing objects
   * @return The list of referencing elements
   */
  public static <T extends EObject> List<T> getReferencers(EObject eObjectRef, EReference eRef,
      SemanticEditingDomain editingDomain, boolean ignoreDerivedFeature) {
    List<T> result = new ArrayList<T>();

    SiriusCrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();
    if (eRef == null) {
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObjectRef,
          editingDomain.getCrossReferencer().isResolveProxyEnabled());
      for (Setting setting : inverseReferences) {
        if (ignoreDerivedFeature && setting.getEStructuralFeature().isDerived())
          continue;
        if (!result.contains(setting.getEObject())) {
          result.add((T) setting.getEObject());
        }
      }

    } else {
      if (ignoreDerivedFeature && eRef.isDerived())
        return result;
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObjectRef, eRef,
          editingDomain.getCrossReferencer().isResolveProxyEnabled());
      for (Setting setting : inverseReferences) {
        if (!result.contains(setting.getEObject())) {
          result.add((T) setting.getEObject());
        }
      }
    }

    return result;
  }

  /**
   * This method retrieves all Object who have a EReference 'eRef' toward the Object 'eObjectRef'
   * 
   * @param eObjectRef
   *          : EObject
   * @param eRef
   *          : EReference relation (if null, all references are considered)
   * @param editingDomain
   *          : SemanticEditingDomain
   * @return The list of referencing elements
   */
  public static <T extends EObject> List<T> getReferencers(EObject eObjectRef, EReference eRef,
      SemanticEditingDomain editingDomain) {
    return getReferencers(eObjectRef, eRef, editingDomain, false);
  }

  /**
   * This method retrieves all Object who have any EReference from 'eRefs' toward the Object 'eObjectRef'
   * 
   * @param eObjectRef
   *          : EObject
   * @param eRefs
   *          : EReference relations list
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
   * 
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
   * @param eObjects
   *          a collection of EObject(s) from which the resource set will be retrieved
   * @return if all EObject(s) belong to the same resource set, this resource set is returned. Otherwise null is
   *         returned.
   */
  public static ResourceSet getCommonResourceSet(Collection<? extends EObject> eObjects) {
    ResourceSet resourceSet = null;
    for (EObject obj : eObjects) {
      if (obj == null) {
        return null;
      }
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
   * @param resources
   *          a collection of resources from which the resource set will be retrieved
   * @return if all resources belong to the same resource set, this resource set is returned. Otherwise null is
   *         returned.
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

  /**
   * This method shall replace EObjectLabelProviderHelper.getText() until bugzilla 2036 is solved
   */
  public static String getText(EObject object) {
    IItemLabelProvider provider = getItemLabelProvider(object);
    String label = ICommonConstants.EMPTY_STRING;

    if (null != provider) {
      label = provider.getText(object);
    }
    return label;
  }

  /**
   * Get the generated item provider for given object.
   * 
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no provider is found.
   */
  public static IItemLabelProvider getItemLabelProvider(EObject object) {
    // Precondition.
    if (null == object) {
      return null;
    }

    EditingDomain editingDomain = TransactionHelper.getEditingDomain(object);

    // Precondition.
    if (null == editingDomain) {
      editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(object);
    }

    // Precondition.
    if (null == editingDomain || !(editingDomain instanceof AdapterFactoryEditingDomain)) {
      return null;
    }
    return (IItemLabelProvider) ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory().adapt(object,
        IItemLabelProvider.class);
  }
}
