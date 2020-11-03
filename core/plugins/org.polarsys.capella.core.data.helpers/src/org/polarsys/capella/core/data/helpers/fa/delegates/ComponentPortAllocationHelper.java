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

  public Object doSwitch(ComponentPortAllocation element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.COMPONENT_PORT_ALLOCATION__ALLOCATING_PORT)) {
      ret = getAllocatingPort(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT_ALLOCATION__ALLOCATED_PORT)) {
      ret = getAllocatedPort(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Port getAllocatingPort(ComponentPortAllocation element) {
    TraceableElement source = element.getSourceElement();
    if (source instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }
    return null;
  }

  protected Port getAllocatedPort(ComponentPortAllocation element) {
    TraceableElement target = element.getTargetElement();
    if (target instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }
    return null;
  }
}
