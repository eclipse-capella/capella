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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 */
public class InterfacesBlankRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    List<AbstractNodeMapping> returnedList = new ArrayList<>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.IDB_COMPONENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.IDB_EXCHANGE_ITEM_MAPPING_NAME));
    return returnedList;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    
    reorderElements(diagram);
  }
}
