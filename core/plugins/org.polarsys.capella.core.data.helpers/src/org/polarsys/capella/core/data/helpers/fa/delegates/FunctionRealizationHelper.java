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

  public Object doSwitch(FunctionRealization element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.FUNCTION_REALIZATION__ALLOCATING_FUNCTION)) {
      ret = getAllocatingFunction(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTION_REALIZATION__ALLOCATED_FUNCTION)) {
      ret = getAllocatedFunction(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionAllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected AbstractFunction getAllocatingFunction(FunctionRealization element_p) {
    TraceableElement ret = element_p.getSourceElement();
    if (null != ret && ret instanceof AbstractFunction)
      return (AbstractFunction) ret;
    return null;
  }

  protected AbstractFunction getAllocatedFunction(FunctionRealization element_p) {
    TraceableElement ret = element_p.getTargetElement();
    if (null != ret && ret instanceof AbstractFunction)
      return (AbstractFunction) ret;
    return null;
  }
}
