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
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.InformationServices;

/**
 */
public class ClassDiagramRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    List<AbstractNodeMapping> returnedList = new ArrayList<>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_DATA_PKG_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_INTERFACE_PKG_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_CLASS_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_ENUMERATION_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_COLLECTION_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_DATA_TYPE_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_BOOLEAN_TYPE_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CDB_EXCHANGE_ITEM_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CDB_INTERFACE_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CDB_DATAVALUE_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CDB_UNIT_MAPPING_NAME));
    return returnedList;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    
    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------
    try {

      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
      Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(descriptor);
      InformationServices.getService().showCDBContextualElements(diagram, contextualElements);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }

    // -------------------------------------
    // Reorder elements in best containers
    // -------------------------------------

    reorderElements(diagram);
  }
}
