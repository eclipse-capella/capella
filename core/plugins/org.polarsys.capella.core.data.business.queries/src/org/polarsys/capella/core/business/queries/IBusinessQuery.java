/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public interface IBusinessQuery {

	/**
   * Gets all the available elements for the business query
   *
   * @param element the current element
   * @return list of available elements
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element);

  /**
   * Gets all the current elements for the business query
   *
   * @param element the current element
   * @param onlyGenerated retrieve only generated elements or not
   * @return list of current elements
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated);

  /**
   *
   */
  public EClass getEClass();

  /**
   *
   */
  public List<EReference> getEStructuralFeatures();
}
