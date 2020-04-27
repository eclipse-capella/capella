/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class FunctionRealizationHelper {
  private static FunctionRealizationHelper instance;

  private FunctionRealizationHelper() {
    // do nothing
  }

  public static FunctionRealizationHelper getInstance() {
    if (instance == null)
      instance = new FunctionRealizationHelper();
    return instance;
  }

  public Object doSwitch(FunctionRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTION_REALIZATION__ALLOCATING_FUNCTION)) {
      ret = getAllocatingFunction(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTION_REALIZATION__ALLOCATED_FUNCTION)) {
      ret = getAllocatedFunction(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionAllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected AbstractFunction getAllocatingFunction(FunctionRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (ret instanceof AbstractFunction)
      return (AbstractFunction) ret;
    return null;
  }

  protected AbstractFunction getAllocatedFunction(FunctionRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (ret instanceof AbstractFunction)
      return (AbstractFunction) ret;
    return null;
  }
}
