/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

public class PhysicalFunctionHelper {
  private static PhysicalFunctionHelper instance;

  private PhysicalFunctionHelper() {
    // do nothing
  }

  public static PhysicalFunctionHelper getInstance() {
    if (instance == null) {
      instance = new PhysicalFunctionHelper();
    }
    return instance;
  }

  public Object doSwitch(PhysicalFunction element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS)) {
      ret = getAllocatingPhysicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS)) {
      ret = getRealizedLogicalFunctions(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS)) {
      ret = getContainedPhysicalFunctions(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS)) {
      ret = getChildrenPhysicalFunctions(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<PhysicalComponent> getAllocatingPhysicalComponents(PhysicalFunction element) {
    List<PhysicalComponent> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof PhysicalComponent) {
          ret.add((PhysicalComponent) src);
        }
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getRealizedLogicalFunctions(PhysicalFunction element) {
    List<LogicalFunction> ret = new ArrayList<>();
    for (FunctionRealization functionRealization : element.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof LogicalFunction) {
        ret.add((LogicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getContainedPhysicalFunctions(PhysicalFunction element) {
    List<PhysicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getOwnedFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getChildrenPhysicalFunctions(PhysicalFunction element) {
    List<PhysicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getSubFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }
}
