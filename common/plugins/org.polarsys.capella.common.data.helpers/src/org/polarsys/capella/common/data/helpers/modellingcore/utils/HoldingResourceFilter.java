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
package org.polarsys.capella.common.data.helpers.modellingcore.utils;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.HoldingResource;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

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

  public boolean isHoldByHoldingResource(EObject obj_p) {
    SemanticEditingDomain editDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain();
    HoldingResource holdRes = editDomain.getHoldingResource();

    return ((obj_p.eResource() != null) && obj_p.eResource().equals(holdRes));
  }
}
