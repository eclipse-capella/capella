/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.vp.ms.Comparison;

/**
 * @generated
 */
public class ComparisonHelper {

  private static final ComparisonHelper instance = new ComparisonHelper();

  /**
   * @generated
   */
  public static ComparisonHelper getInstance() {
    return instance;
  }

  /**
   * @generated
   */
  public Object doSwitch(Comparison object, EStructuralFeature feature) {
    // handle derivated feature

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
        .doSwitch(object, feature);

  }

}