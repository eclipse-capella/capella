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
package org.polarsys.capella.core.sirius.ui.copylayout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ILayoutDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class CapellaLayoutDataManagerProvider implements ILayoutDataManagerProvider {

  public boolean provides(DDiagram diagram) {
    if (diagram instanceof DSemanticDiagram) {
      DSemanticDiagram dSem = (DSemanticDiagram) diagram;
      EObject semanticTarget = dSem.getTarget();
      return (CapellaResourceHelper.isSemanticElement(semanticTarget)) && !(semanticTarget instanceof Scenario);
    }
    return false;
  }

  public SiriusLayoutDataManager getLayoutDataManager() {
    return new CapellaLayoutDataManager();
  }

}
