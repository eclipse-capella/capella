/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    if (instance == null)
      instance = new ValueSpecificationHelper();
    return instance;
  }

  public Object doSwitch(ValueSpecification element, EStructuralFeature feature) {
    Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractTypedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

}
