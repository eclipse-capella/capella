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

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;

/**
 *
 */
public class DataFlowBlankRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);

    FunctionalChainCache.getInstance().reset();
    DDiagram openingDiagram = DiagramServices.getDiagramServices().getOpeningDiagram();
    // Avoid resetting the icon cache if it's not on the diagram currently being opened
    if (openingDiagram == null || openingDiagram == diagram) {
      DEdgeIconCache.getInstance().reset();
    }
    
    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------
    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram);

    try {
      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
      Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(descriptor);
      FaServices.getFaServices().showDFContextualElements(context, contextualElements);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }
    
    try {
      updateFunctionalExchangeCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFECategories, e);
    }
    // -------------------------------------
    // Commit elements
    // -------------------------------------

    try {
      context.commitDeferredActions();
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnCommitDeferredActions, e);
    }

    // -------------------------------------
    // Reorder elements in best containers
    // -------------------------------------

    try {
      FaServices.getFaServices().reorderFAElements(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }

  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {
    try {
      FunctionalChainServices.getFunctionalChainServices().updateFunctionalChainStyles(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFunctionalChainStyle, e);
    }

    FunctionalChainCache.getInstance().reset();
    
    super.postRefresh(diagram);
  }

  protected void updateFunctionalExchangeCategories(DDiagramContents diagramContents) {
    DDiagram diagram = diagramContents.getDDiagram();
    if (diagram.isSynchronized()) {
      // TODO Should I move this out of the if isSynchronized. Should it always switch FECategories?
      // Switch to FE categories
      EdgeMapping edgeMapping = FaServices.getFaServices().getMappingFECategory(diagram);
      if (edgeMapping != null) {
        Collection<ExchangeCategory> categories = new HashSet<>();
        for (DDiagramElement element : diagramContents.getDiagramElements(edgeMapping)) {
          EObject target = element.getTarget();
          if (target instanceof ExchangeCategory) {
            categories.add((ExchangeCategory) target);
          }
        }
        FaServices.getFaServices().switchFECategories(diagramContents, (DSemanticDecorator) diagram, categories, false);
      }
    }
  }
}
