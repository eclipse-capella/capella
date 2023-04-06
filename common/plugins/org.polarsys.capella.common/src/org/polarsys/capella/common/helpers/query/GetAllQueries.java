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

package org.polarsys.capella.common.helpers.query;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * This class contains convenient static methods for querying a EMF model
 */
public class GetAllQueries implements IGetAllQueries {
  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source
   *          element from which the search starts
   * @param targetType
   *          discriminating type
   */
  public Set<EObject> getAll(EObject source, Class<?> targetType) {

    Set<EObject> result = new LinkedHashSet<EObject>();

    if (source == null || targetType == null)
      return result;

    if (targetType.isAssignableFrom(source.getClass())) {
      result.add(source);
    }

    EList<EObject> containedElements = source.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAll(object, targetType));
    }

    return result;
  }

  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source
   *          element from which the search starts
   * @param targetType
   *          discriminating type
   */
  public Set<EObject> getAll(EObject source, EClass targetType) {

    Set<EObject> result = new LinkedHashSet<EObject>();

    if (source == null || targetType == null)
      return result;

    if (targetType.isSuperTypeOf(source.eClass())) {
      result.add(source);
    }

    EList<EObject> containedElements = source.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAll(object, targetType));
    }

    return result;
  }

  /**
   * Retrieve EObject instances corresponding to a predicate and accessible from a source object by successive
   * compositions
   * 
   * @param <T>
   * 
   * @param source
   *          element from which the search starts
   * @param predicate
   *          discriminating predicate
   */
  public Set<EObject> getAll(EObject source, Predicate<EObject> predicate) {

    Set<EObject> result = new LinkedHashSet<EObject>();

    if (source == null || predicate == null)
      return result;

    if (predicate.test(source)) {
      result.add(source);
    }

    EList<EObject> containedElements = source.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAll(object, predicate));
    }

    return result;
  }

  /**
   * Retrieve recursively EObject instances corresponding to a type and accessible from a source object by successive
   * compositions
   * 
   * @param source
   *          element from which the search starts
   * @param targetType
   *          discriminating type
   * @param filter
   *          list of the classes excluded from the getAll query
   */

  public Set<EObject> getAllFiltered(EObject source, EClass targetType, List<EClass> filter) {
    Set<EObject> result = new LinkedHashSet<EObject>();

    if (source == null || targetType == null)
      return result;

    if (filter.contains(targetType)) {
      return result;
    }

    if (targetType.isSuperTypeOf(source.eClass())) {
      result.add(source);
    }

    for (EClass cls : filter) {
      if (cls.isSuperTypeOf(source.eClass())) {
        return result;
      }
    }

    EList<EObject> containedElements = source.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAllFiltered(object, targetType, filter));
    }

    return result;
  }

  /**
   * @see org.polarsys.capella.core.common.model.helpers.query.IGetAllQueries#getLocalSubTypes(org.eclipse.emf.ecore.EClass)
   */
  public List<EClass> getLocalSubTypes(EClass eClass) {
    List<EClass> result = new ArrayList<EClass>();
    EPackage pkg = getRootPackage(eClass.getEPackage());
    for (TreeIterator<EObject> iterator = pkg.eAllContents(); iterator.hasNext();) {
      Object object = iterator.next();
      if (object instanceof EClass) {
        if (eClass.isSuperTypeOf((EClass) object)) {
          result.add((EClass) object);
        }
      }
    }
    return result;
  }

  /**
   * Return the root package of the given package. Will return the root package from the same namespace. Will not look
   * up into extensions of the current resource.
   * 
   * @param ePackage
   * @return null if given ePackage is null a EPackage otherwise.
   */
  private EPackage getRootPackage(EPackage ePackage) {
    if (null == ePackage)
      return null;
    EPackage pkg = ePackage.getESuperPackage();
    if (null == pkg)
      return ePackage;
    return getRootPackage(pkg);
  }
}
