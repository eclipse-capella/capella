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
package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

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

  public Object doSwitch(PhysicalFunction element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_ACTORS)) {
      ret = getAllocatorPhysicalActors(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_COMPONENTS)) {
      ret = getAllocatorPhysicalComponents(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS)) {
      ret = getRealizedLogicalFunctions(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS)) {
      ret = getContainedPhysicalFunctions(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS)) {
      ret = getChildrenPhysicalFunctions(element_p);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<PhysicalActor> getAllocatorPhysicalActors(PhysicalFunction element_p) {
    List<PhysicalActor> ret = new ArrayList<PhysicalActor>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof PhysicalActor) {
          ret.add((PhysicalActor) src);
        }
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getAllocatorPhysicalComponents(PhysicalFunction element_p) {
    List<PhysicalComponent> ret = new ArrayList<PhysicalComponent>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof PhysicalComponent) {
          ret.add((PhysicalComponent) src);
        }
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getRealizedLogicalFunctions(PhysicalFunction element_p) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (FunctionRealization functionRealization : element_p.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof LogicalFunction) {
        ret.add((LogicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getContainedPhysicalFunctions(PhysicalFunction element_p) {
    List<PhysicalFunction> ret = new ArrayList<PhysicalFunction>();
    for (AbstractFunction function : element_p.getOwnedFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getChildrenPhysicalFunctions(PhysicalFunction element_p) {
    List<PhysicalFunction> ret = new ArrayList<PhysicalFunction>();
    for (AbstractFunction function : element_p.getSubFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }
}
