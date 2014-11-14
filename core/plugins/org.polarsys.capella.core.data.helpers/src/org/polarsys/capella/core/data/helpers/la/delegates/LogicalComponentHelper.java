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
package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemRealization;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

public class LogicalComponentHelper {

  private static LogicalComponentHelper instance;

  private LogicalComponentHelper() {
    // do nothing
  }

  public static LogicalComponentHelper getInstance() {
    if (instance == null)
      instance = new LogicalComponentHelper();
    return instance;
  }

  public Object doSwitch(LogicalComponent element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(LaPackage.Literals.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS)) {
      ret = getSubLogicalComponents(element_p);
    } else if (feature_p.equals(LaPackage.Literals.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS)) {
      ret = getSystemRealizations(element_p);
    } else if (feature_p.equals(LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS)) {
      ret = getAllocatedLogicalFunctions(element_p);
    } else if (feature_p.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS)) {
      ret = getRealizingPhysicalComponents(element_p);
    } else if (feature_p.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEMS)) {
      ret = getRealizedSystems(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = SystemComponentHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<SystemRealization> getSystemRealizations(LogicalComponent element_p) {
    List<SystemRealization> ret = new ArrayList<SystemRealization>();
    for (ComponentAllocation componentAllocation : element_p.getProvisionedComponentAllocations()) {
      if (componentAllocation instanceof SystemRealization) {
        ret.add((SystemRealization) componentAllocation);
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getSubLogicalComponents(LogicalComponent element_p) {
    List<LogicalComponent> ret = new ArrayList<LogicalComponent>();
    for (Partition thePartition : element_p.getOwnedPartitions()) {
      Type representedElement = thePartition.getType();
      if (representedElement instanceof LogicalComponent) {
        ret.add((LogicalComponent) representedElement);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getAllocatedLogicalFunctions(LogicalComponent element_p) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (AbstractFunction function : element_p.getAllocatedFunctions()) {
      if (function instanceof LogicalFunction) {
        ret.add((LogicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getRealizingPhysicalComponents(LogicalComponent element_p) {
    List<PhysicalComponent> ret = new ArrayList<PhysicalComponent>();
    for (Component cpnt : element_p.getAllocatingComponents()) {
      if (cpnt instanceof PhysicalComponent) {
        ret.add((PhysicalComponent) cpnt);
      }
    }
    return ret;
  }

  protected List<System> getRealizedSystems(LogicalComponent element_p) {
    List<System> ret = new ArrayList<System>();
    for (Component cpnt : element_p.getAllocatedComponents()) {
      if (cpnt instanceof System) {
        ret.add((System) cpnt);
      }
    }
    return ret;
  }
}
