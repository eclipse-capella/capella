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

  public Object doSwitch(LogicalComponent element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS)) {
      ret = getSubLogicalComponents(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS)) {
      ret = getSystemRealizations(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS)) {
      ret = getAllocatedLogicalFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS)) {
      ret = getRealizingPhysicalComponents(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEMS)) {
      ret = getRealizedSystems(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = SystemComponentHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<SystemRealization> getSystemRealizations(LogicalComponent element) {
    List<SystemRealization> ret = new ArrayList<>();
    for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
      if (componentAllocation instanceof SystemRealization) {
        ret.add((SystemRealization) componentAllocation);
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getSubLogicalComponents(LogicalComponent element) {
    List<LogicalComponent> ret = new ArrayList<>();
    for (Partition thePartition : element.getOwnedPartitions()) {
      Type representedElement = thePartition.getType();
      if (representedElement instanceof LogicalComponent) {
        ret.add((LogicalComponent) representedElement);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getAllocatedLogicalFunctions(LogicalComponent element) {
    List<LogicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof LogicalFunction) {
        ret.add((LogicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getRealizingPhysicalComponents(LogicalComponent element) {
    List<PhysicalComponent> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatingComponents()) {
      if (cpnt instanceof PhysicalComponent) {
        ret.add((PhysicalComponent) cpnt);
      }
    }
    return ret;
  }

  protected List<System> getRealizedSystems(LogicalComponent element) {
    List<System> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatedComponents()) {
      if (cpnt instanceof System) {
        ret.add((System) cpnt);
      }
    }
    return ret;
  }
}
