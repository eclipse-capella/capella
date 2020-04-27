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

  public Object doSwitch(AbstractFunctionalBlock element, EStructuralFeature feature) {

    Object ret = null;

    if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS)) {
      ret = getAllocatedFunctions(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS)) {
      ret = getFunctionalAllocations(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypeHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ComponentFunctionalAllocation> getFunctionalAllocations(AbstractFunctionalBlock element) {
    List<AbstractTrace> traces = element.getOutgoingTraces();
    List<ComponentFunctionalAllocation> ret = new ArrayList<>();

    for (AbstractTrace trace : traces) {
      if (trace instanceof ComponentFunctionalAllocation) {
        ret.add((ComponentFunctionalAllocation) trace);
      }
    }

    return ret;
  }

  protected List<AbstractFunction> getAllocatedFunctions(AbstractFunctionalBlock element) {
    List<ComponentFunctionalAllocation> allocs = element.getFunctionalAllocations();
    List<AbstractFunction> ret = new ArrayList<>();
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
