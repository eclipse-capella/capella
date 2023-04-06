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

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions.
 * @version 0.1.0
 */
public interface IGetAllQueries {
  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source
   *          element from which the search starts
   * @param targetType
   *          discriminating type
   */
  public Set<EObject> getAll(EObject source, Class<?> targetType);

  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source
   *          element from which the search starts
   * @param targetType
   *          discriminating type
   */
  public Set<EObject> getAll(EObject source, EClass targetType);

  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source
   *          element from which the search starts
   * @param predicate
   *          discriminating predicate
   */
  public Set<EObject> getAll(EObject source, Predicate<EObject> predicate);
  
  /**
   * Retrieve recursively EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source element from which the search starts
   * @param targetType discriminating type
   * @param filter list of the classes excluded from the getAll query
   */
  public Set<EObject> getAllFiltered(EObject source, EClass targetType, List<EClass> filter) ;
  
  /**
   * Retrieve EClasses that have the given EClass as super type.
   * TODO : getAllSubTypes with nspace param in case user wants to browse some metamodel extensions !!!
   * @param eClass supertype
   * @return sub types
   */
  public List<EClass> getLocalSubTypes(EClass eClass);
}
