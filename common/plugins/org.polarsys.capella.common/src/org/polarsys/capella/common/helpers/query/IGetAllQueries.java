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

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions.
 * @version 0.1.0
 */
public interface IGetAllQueries {
  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source_p
   *          element from which the search starts
   * @param targetType_p
   *          discriminating type
   */
  public Set<EObject> getAll(EObject source_p, Class<?> targetType_p);
  
  /**
   * Retrieve recursively EObject instances corresponding to a type and accessible from a source object by successive compositions
   * 
   * @param source_p element from which the search starts
   * @param targetType_p discriminating type
   * @param filter_p list of the classes excluded from the getAll query
   */
  public Set<EObject> getAllFiltered(EObject source_p, EClass targetType_p, List<EClass> filter_p) ;
  
  /**
   * Retrieve EClasses that have the given EClass as super type.
   * TODO : getAllSubTypes with nspace param in case user wants to browse some metamodel extensions !!!
   * @param eClass_p supertype
   * @return sub types
   */
  public List<EClass> getLocalSubTypes(EClass eClass_p);
}
