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
package org.polarsys.capella.core.sirius.ui.copyformat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class CapellaFormatDataManagerProvider implements IFormatDataManagerProvider {

  @Override
  public boolean provides(DDiagram diagram) {
    if (diagram instanceof DSemanticDiagram) {
      DSemanticDiagram dSem = (DSemanticDiagram) diagram;
      EObject semanticTarget = dSem.getTarget();
      return (CapellaResourceHelper.isSemanticElement(semanticTarget)) && !(semanticTarget instanceof Scenario);
    }
    return false;
  }

  @Override
  public SiriusFormatDataManager getFormatDataManager() {
    return new CapellaFormatDataManager();
  }

}
