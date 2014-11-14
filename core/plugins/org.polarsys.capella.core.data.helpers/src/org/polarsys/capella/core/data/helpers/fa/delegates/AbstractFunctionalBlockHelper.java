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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class AbstractFunctionalBlockHelper {
  private static AbstractFunctionalBlockHelper instance;

  private AbstractFunctionalBlockHelper() {
    // Private constructor.
  }

  public static AbstractFunctionalBlockHelper getInstance() {
    if (instance == null)
      instance = new AbstractFunctionalBlockHelper();
    return instance;
  }

  public Object doSwitch(AbstractFunctionalBlock element_p, EStructuralFeature feature_p) {

    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS)) {
      ret = getAllocatedFunctions(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS)) {
      ret = getFunctionalAllocations(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypeHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<ComponentFunctionalAllocation> getFunctionalAllocations(AbstractFunctionalBlock element_p) {
    List<AbstractTrace> traces = element_p.getOutgoingTraces();
    List<ComponentFunctionalAllocation> ret = new ArrayList<ComponentFunctionalAllocation>();

    for (AbstractTrace trace : traces) {
      if (trace instanceof ComponentFunctionalAllocation) {
        ret.add((ComponentFunctionalAllocation) trace);
      }
    }

    return ret;
  }

  protected List<AbstractFunction> getAllocatedFunctions(AbstractFunctionalBlock element_p) {
    List<ComponentFunctionalAllocation> allocs = element_p.getFunctionalAllocations();
    List<AbstractFunction> ret = new ArrayList<AbstractFunction>();
    for (ComponentFunctionalAllocation componentFunctionalAllocation : allocs) {
      // Avoid gmf cross referencing issue.
      AbstractFunction function = componentFunctionalAllocation.getFunction();
      // Make sure function is not null.
      if (null != function) {
        ret.add(function);
      }
    }
    return ret;
  }
}
