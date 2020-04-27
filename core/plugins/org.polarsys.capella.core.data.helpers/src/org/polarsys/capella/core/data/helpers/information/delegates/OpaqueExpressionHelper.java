/*******************************************************************************
 * Copyright (c) 2006, 201* THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.ValueSpecificationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;

public class OpaqueExpressionHelper {

  private static OpaqueExpressionHelper instance;

  public static OpaqueExpressionHelper getInstance(){
    if(instance == null){
      instance = new OpaqueExpressionHelper();
    }
    return instance;
  }

  public Object doSwitch(OpaqueExpression object, EStructuralFeature feature) {

    // no helper found... searching in super classes...
    Object ret = CapellaElementHelper.getInstance().doSwitch(object, feature);
    
    if (null == ret) {
      ret = ValueSpecificationHelper.getInstance().doSwitch(object, feature);
    }

    return ret;
  }

}
