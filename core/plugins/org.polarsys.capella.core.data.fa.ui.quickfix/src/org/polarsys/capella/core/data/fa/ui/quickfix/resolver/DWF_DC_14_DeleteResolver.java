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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * QuickFix allowing to delete invalid PortAllocation(s) from a ComponentPort.
 */
public class DWF_DC_14_DeleteResolver extends AbstractDeleteCommandResolver {

  
  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj_p) {
    // Precondition.
    if (!(obj_p instanceof ComponentPort)) {
      return null;
    }
    // Get invalid PortAllocations to delete.
    ComponentPort componentPort = (ComponentPort) obj_p;
    List<PortAllocation> portAllocationsToDelete = new ArrayList<PortAllocation>();
    for (PortAllocation portAllocation : componentPort.getOutgoingPortAllocations()) {
      TraceableElement sourceElement = portAllocation.getSourceElement();
      TraceableElement targetElement = portAllocation.getTargetElement();
      if ((sourceElement instanceof ComponentPort) && (targetElement instanceof ActivityNode)) {
        // Check target Function is nested in source Component.
        Boolean result = PortExt.isRelatedComponentAllocatingRelatedFunction((ComponentPort) sourceElement, (ActivityNode) targetElement);
        if (Boolean.FALSE.equals(result)) {
          portAllocationsToDelete.add(portAllocation);
        }
      } else {
        portAllocationsToDelete.add(portAllocation);
      }
    }
    return portAllocationsToDelete;
  }
 

}
