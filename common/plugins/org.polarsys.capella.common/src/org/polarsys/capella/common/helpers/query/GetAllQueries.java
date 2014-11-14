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
package org.polarsys.capella.common.helpers.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IteratorKind;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;

/**
 * This class contains convenient static methods for querying a EMF model
 */
public class GetAllQueries implements IGetAllQueries {
  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source_p element from which the search starts
   * @param targetType_p discriminating type
   */
  public Set<EObject> getAll(EObject source_p, Class<?> targetType_p) {
    Set<EObject> result = null;
    if (null != source_p) {
      EObjectCondition condition = new EObjectTypeCondition(targetType_p);
      SELECT statement = new SELECT(new FROM(source_p, IteratorKind.HIERARCHICAL_LITERAL), new WHERE(condition));
      result = new HashSet<EObject>(statement.execute().getEObjects());
      // getAll should not retrieve source element
      result.remove(source_p);
    }
    return result;
  }

  /**
   * Retrieve recursively EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source_p element from which the search starts
   * @param targetType_p discriminating type
   * @param filter_p list of the classes excluded from the getAll query
   */

  public Set<EObject> getAllFiltered(EObject source_p, EClass targetType_p, List<EClass> filter_p) {
    Set<EObject> result = new HashSet<EObject>(1);

    if (source_p == null || targetType_p == null)
      return result;

    if (filter_p.contains(targetType_p)) {
      return result;
    }

    if (targetType_p.isSuperTypeOf(source_p.eClass())) {
      result.add(source_p);
    }

    for (EClass filter : filter_p) {
      if (filter.isSuperTypeOf(source_p.eClass())) {
        return result;
      }
    }

    EList<EObject> containedElements = source_p.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAllFiltered(object, targetType_p, filter_p));
    }

    return result;
  }

  /**
   * @see org.polarsys.capella.core.common.model.helpers.query.IGetAllQueries#getLocalSubTypes(org.eclipse.emf.ecore.EClass)
   */
  public List<EClass> getLocalSubTypes(EClass eClass_p) {
    List<EClass> result = new ArrayList<EClass>();
    EPackage pkg = getRootPackage(eClass_p.getEPackage());
    for (TreeIterator<EObject> iterator = pkg.eAllContents(); iterator.hasNext();) {
      Object object = iterator.next();
      if (object instanceof EClass) {
        if (eClass_p.isSuperTypeOf((EClass) object)) {
          result.add((EClass) object);
        }
      }
    }
    return result;
  }

  /**
   * Return the root package of the given package. Will return the root package from the same namespace. Will not look up into extensions of the current
   * resource.
   * @param ePackage_p
   * @return null if given ePackage_p is null a EPackage otherwise.
   */
  private EPackage getRootPackage(EPackage ePackage_p) {
    if (null == ePackage_p)
      return null;
    EPackage pkg = ePackage_p.getESuperPackage();
    if (null == pkg)
      return ePackage_p;
    return getRootPackage(pkg);
  }
}
