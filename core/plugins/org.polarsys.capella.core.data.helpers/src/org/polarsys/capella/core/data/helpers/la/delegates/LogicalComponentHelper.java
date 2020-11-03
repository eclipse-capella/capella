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

package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
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
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS)) {
      ret = getAllocatedLogicalFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS)) {
      ret = getRealizingPhysicalComponents(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS)) {
      ret = getRealizedSystemComponents(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ComponentHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<LogicalComponent> getSubLogicalComponents(LogicalComponent element) {
    List<LogicalComponent> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        Type type = ((Part) feature).getType();
        if (type instanceof LogicalComponent)
          ret.add((LogicalComponent) type);
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
    return element.getRealizingComponents().stream().filter(PhysicalComponent.class::isInstance)
        .map(PhysicalComponent.class::cast).collect(Collectors.toList());
  }

  protected List<SystemComponent> getRealizedSystemComponents(LogicalComponent element) {
    return element.getRealizedComponents().stream().filter(SystemComponent.class::isInstance)
        .map(SystemComponent.class::cast).collect(Collectors.toList());
  }
}
