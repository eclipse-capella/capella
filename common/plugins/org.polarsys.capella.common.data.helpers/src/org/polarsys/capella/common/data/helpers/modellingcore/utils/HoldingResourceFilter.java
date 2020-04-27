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

package org.polarsys.capella.common.data.helpers.modellingcore.utils;

import org.eclipse.emf.ecore.EObject;

/**
 */
public class HoldingResourceFilter {

  // Singleton instance
  private static HoldingResourceFilter _instance;

  /**
   * Default constructor
   */
  private HoldingResourceFilter() {
    // do nothing
  }

  /**
   * Singleton accessor
   */
  public static HoldingResourceFilter getInstance() {
    if (_instance == null) {
      _instance = new HoldingResourceFilter();
    }
    return _instance;
  }

  public boolean isHoldByHoldingResource(EObject obj) {
    //    SemanticEditingDomain editDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain(obj);
    //    HoldingResource holdRes = editDomain.getHoldingResource();

    //    return ((obj.eResource() != null) && obj.eResource().equals(holdRes));
    return false;
  }
}
