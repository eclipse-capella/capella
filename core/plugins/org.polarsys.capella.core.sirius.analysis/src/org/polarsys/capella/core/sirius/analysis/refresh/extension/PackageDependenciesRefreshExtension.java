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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;

import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 *
 */
public class PackageDependenciesRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram_p) {
    List<AbstractNodeMapping> returnedList = new ArrayList<AbstractNodeMapping>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME));
    return returnedList;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram dDiagram_p) {
    reorderElements(dDiagram_p);
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void postRefresh(DDiagram dDiagram_p) {
    // Nothing

  }

}
