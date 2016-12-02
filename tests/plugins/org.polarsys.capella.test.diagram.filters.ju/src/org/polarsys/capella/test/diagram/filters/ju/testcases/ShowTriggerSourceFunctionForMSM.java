/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class ShowTriggerSourceFunctionForMSM extends DiagramObjectFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "ShowTriggerSourceFunctionFilter"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "[MSM] Default Region"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IMappingNameConstants.SHOW_TRIGGER_SOURCE_FUNCTION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { "77a5784e-0929-493a-a84d-057cebabbac7" //$NON-NLS-1$
    });
  }

  @Override
  protected boolean isFiltered(DDiagramElement elt) {
    if (elt.getTarget() instanceof StateTransition) {
      StateTransition transition = (StateTransition) elt.getTarget();
      if (transition.getId().equals(getFilteredObjetIDs().get(0)))
        return (elt.getName().equals("[SystemFunction 1 ->] FunctionalExchange 1" //$NON-NLS-1$
            ));
    }
    return super.isFiltered(elt);
  }
}
