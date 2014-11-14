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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.viewpoint.DDiagram;

import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class DataFlowBlankRefreshExtensionProvider implements IRefreshExtensionProvider {

  private static final DataFlowBlankRefreshExtension REFRESH_EXTENSION = new DataFlowBlankRefreshExtension();

  public DataFlowBlankRefreshExtensionProvider() {
    // Nothing
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#getRefreshExtension(org.eclipse.sirius.DDiagram)
   */
  public IRefreshExtension getRefreshExtension(DDiagram viewPoint_p) {
    return REFRESH_EXTENSION;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#provides(org.eclipse.sirius.DDiagram)
   */
  public boolean provides(DDiagram viewPoint_p) {
    String name = viewPoint_p.getDescription().getName();
    return name.endsWith(DiagramDescriptionConstants.DATA_FLOW_BLANK_DIAGRAM_NAME)
           || name.equals(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME);
  }
}
