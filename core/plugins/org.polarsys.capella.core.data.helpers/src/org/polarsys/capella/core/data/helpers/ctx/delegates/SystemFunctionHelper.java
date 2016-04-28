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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

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

    if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATOR_ACTORS)) {
      ret = getAllocatorActors(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATOR_SYSTEMS)) {
      ret = getAllocatorSystems(element);
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

  protected List<Actor> getAllocatorActors(SystemFunction element) {
    List<Actor> ret = new ArrayList<Actor>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Actor) {
          ret.add((Actor) src);
        }
      }
    }
    return ret;
  }

  protected List<System> getAllocatorSystems(SystemFunction element) {
    List<System> ret = new ArrayList<System>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof System) {
          ret.add((System) src);
        }
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getRealizedOperationalActivities(SystemFunction element) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (FunctionRealization functionRealization : element.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof OperationalActivity) {
        ret.add((OperationalActivity) abstractFunction);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getRealizingLogicalFunctions(SystemFunction element) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (FunctionRealization functionRealization : element.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof LogicalFunction) {
        ret.add((LogicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getContainedSystemFunctions(SystemFunction element) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (AbstractFunction function : element.getOwnedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getChildrenSystemFunctions(SystemFunction element) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (AbstractFunction function : element.getSubFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }
}
