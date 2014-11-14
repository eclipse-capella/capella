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

  public Object doSwitch(SystemFunction element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATOR_ACTORS)) {
      ret = getAllocatorActors(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATOR_SYSTEMS)) {
      ret = getAllocatorSystems(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES)) {
      ret = getRealizedOperationalActivities(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS)) {
      ret = getRealizingLogicalFunctions(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS)) {
      ret = getContainedSystemFunctions(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS)) {
      ret = getChildrenSystemFunctions(element_p);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<Actor> getAllocatorActors(SystemFunction element_p) {
    List<Actor> ret = new ArrayList<Actor>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Actor) {
          ret.add((Actor) src);
        }
      }
    }
    return ret;
  }

  protected List<System> getAllocatorSystems(SystemFunction element_p) {
    List<System> ret = new ArrayList<System>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof System) {
          ret.add((System) src);
        }
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getRealizedOperationalActivities(SystemFunction element_p) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (FunctionRealization functionRealization : element_p.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof OperationalActivity) {
        ret.add((OperationalActivity) abstractFunction);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getRealizingLogicalFunctions(SystemFunction element_p) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (FunctionRealization functionRealization : element_p.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof LogicalFunction) {
        ret.add((LogicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getContainedSystemFunctions(SystemFunction element_p) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (AbstractFunction function : element_p.getOwnedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }

  protected List<SystemFunction> getChildrenSystemFunctions(SystemFunction element_p) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (AbstractFunction function : element_p.getSubFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }
}
