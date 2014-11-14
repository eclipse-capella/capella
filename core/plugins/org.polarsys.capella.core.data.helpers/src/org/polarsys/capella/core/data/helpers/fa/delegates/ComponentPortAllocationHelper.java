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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentPortAllocationHelper {
  private static ComponentPortAllocationHelper instance;

  private ComponentPortAllocationHelper() {
    // do nothing
  }

  public static ComponentPortAllocationHelper getInstance() {
    if (instance == null)
      instance = new ComponentPortAllocationHelper();
    return instance;
  }

  public Object doSwitch(ComponentPortAllocation element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT_ALLOCATION__ALLOCATING_PORT)) {
      ret = getAllocatingPort(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT_ALLOCATION__ALLOCATED_PORT)) {
      ret = getAllocatedPort(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected Port getAllocatingPort(ComponentPortAllocation element_p) {
    TraceableElement source = element_p.getSourceElement();
    if (source instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }
    return null;
  }

  protected Port getAllocatedPort(ComponentPortAllocation element_p) {
    TraceableElement target = element_p.getTargetElement();
    if (target instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }
    return null;
  }
}
