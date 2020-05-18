/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 */
public interface IBusinessQuery {

	/**
   * Gets all the available elements for the business query
   *
   * @param element the current element
   * @return list of available elements
   */
  public List<EObject> getAvailableElements(EObject element);

  /**
   * Gets all the current elements for the business query
   *
   * @param element the current element
   * @param onlyGenerated retrieve only generated elements or not
   * @return list of current elements
   */
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated);

  /**
   *
   */
  public EClass getEClass();

  /**
   *
   */
  public List<EReference> getEStructuralFeatures();
}
