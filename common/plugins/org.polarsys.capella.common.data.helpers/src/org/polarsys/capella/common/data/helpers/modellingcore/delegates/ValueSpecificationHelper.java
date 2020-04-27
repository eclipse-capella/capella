/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

public class ValueSpecificationHelper {
	
  private static ValueSpecificationHelper instance;
  
  public static ValueSpecificationHelper getInstance() {
    if (instance == null) {
    	instance = new ValueSpecificationHelper();
    }
    return instance;
  }

  public Object doSwitch(ValueSpecification element, EStructuralFeature feature) {
	  // no helper found... searching in super classes...
      return AbstractTypedElementHelper.getInstance().doSwitch(element, feature);
  }
}
