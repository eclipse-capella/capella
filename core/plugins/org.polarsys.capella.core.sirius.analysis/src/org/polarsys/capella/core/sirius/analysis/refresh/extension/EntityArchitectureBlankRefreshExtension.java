/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;

/**
 * 
 */
//TODO : merge this refresh extension with component Architecture Refresh Extension 
public class EntityArchitectureBlankRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram) {

    FunctionalChainCache.getInstance().reset();
    
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(descriptor);

    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram);
    
    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------
    try {
      CsServices.getService().showABContextualElements(context, contextualElements);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }

    // -------------------------------------
    // Reorder elements in best containers
    // -------------------------------------
    try {
      CsServices.getService().refreshEntitiesArchitecture(getComponentMapping(diagram), diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void postRefresh(DDiagram diagram) {
    try {
      FunctionalChainServices.getFunctionalChainServices().updateFunctionalChainStyles(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFunctionalChainStyle, e);
    }

    FunctionalChainCache.getInstance().reset();
  }

  /**
   * @param diagram_p
   * @return
   */
  public ContainerMapping getComponentMapping(DDiagram diagram_p) {
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.OAB_ENTITY_MAPPING_NAME);
    }

    return null;
  }

}
