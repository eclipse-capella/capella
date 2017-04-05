/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.vp.ms.OrOperation;

/**
 * @generated
 */
public class OrOperationHelper {

  private static final OrOperationHelper instance = new OrOperationHelper();

  /**
   * @generated
   */
  public static OrOperationHelper getInstance() {
    return instance;
  }

  /**
   * @generated
   */
  public Object doSwitch(OrOperation object, EStructuralFeature feature) {
    // handle derivated feature

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper.getInstance()
        .doSwitch(object, feature);

  }

}