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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;

public class SystemFunctionHelper {
  private static SystemFunctionHelper instance;

  private SystemFunctionHelper() {
    // do nothing
  }

  public static SystemFunctionHelper getInstance() {
    if (instance == null) {
      instance = new SystemFunctionHelper();
    }
    return instance;
  }

  public Object doSwitch(SystemFunction element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS)) {
      ret = getAllocatingSystemComponents(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES)) {
      ret = getRealizedOperationalActivities(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS)) {
      ret = getRealizingLogicalFunctions(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS)) {
      ret = getContainedSystemFunctions(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS)) {
      ret = getChildrenSystemFunctions(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<SystemComponent> getAllocatingSystemComponents(SystemFunction element) {
    List<SystemComponent> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof SystemComponent) {
          ret.add((SystemComponent) src);
        }
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getRealizedOperationalActivities(SystemFunction element) {
    List<OperationalActivity> ret = new ArrayList<>();
    for (FunctionRealization functionRealization : element.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof OperationalActivity) {
        ret.add((OperationalActivity) abstractFunction);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getRealizingLogicalFunctions(SystemFunction element) {
    List<LogicalFunction> ret = new ArrayList<>();
    for (FunctionRealization functionRealization : element.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof LogicalFunction) {
        ret.add((LogicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getContainedSystemFunctions(SystemFunction element) {
    List<SystemFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getOwnedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getChildrenSystemFunctions(SystemFunction element) {
    List<SystemFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getSubFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }
}
