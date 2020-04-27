/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;

public class ComponentPortAllocationEndHelper {
  private static ComponentPortAllocationEndHelper instance;

  private ComponentPortAllocationEndHelper() {
    // do nothing
  }

  public static ComponentPortAllocationEndHelper getInstance() {
    if (instance == null)
      instance = new ComponentPortAllocationEndHelper();
    return instance;
  }

  public Object doSwitch(ComponentPortAllocationEnd element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.COMPONENT_PORT_ALLOCATION_END__OWNING_COMPONENT_PORT_ALLOCATION)) {
      ret = getOwningComponentPortAllocation(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  private ComponentPortAllocation getOwningComponentPortAllocation(ComponentPortAllocationEnd element) {
    EObject parent = element.eContainer();
    if (parent instanceof ComponentPortAllocation) {
      return (ComponentPortAllocation)parent;
    }
    return null;
  }

}
