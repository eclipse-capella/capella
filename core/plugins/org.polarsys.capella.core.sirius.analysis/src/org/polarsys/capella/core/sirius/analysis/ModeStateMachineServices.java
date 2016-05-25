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
package org.polarsys.capella.core.sirius.analysis;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.core.data.capellacommon.Region;

/**
 * Services for Mode State machine diagram.
 */
public class ModeStateMachineServices {
  
  /** A shared instance. */
  private static StateMachineServices _service;
  
  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static StateMachineServices getService() {
    if (_service == null) {
      _service = new StateMachineServices();
    }
    return _service;
  }
  
  public Region getRegionForTransitionMSM(EObject context, DDiagramElement delement) {
    Region region = null;

    EObject container = delement.eContainer();
    if (container instanceof DDiagram) {
      region = (Region) ((DSemanticDiagram) container).getTarget();
    } else {
      EObject obj = ((DNodeContainer) container).getTarget();
      region = (Region) obj;
    }
    return region;
  }
}
