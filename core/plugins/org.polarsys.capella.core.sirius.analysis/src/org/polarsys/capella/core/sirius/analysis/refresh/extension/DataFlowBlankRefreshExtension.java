/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;

/**
 *
 */
public class DataFlowBlankRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram_p) {

    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------
    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram_p);

    try {
      Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(diagram_p);
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
      FaServices.getFaServices().reorderFAElements(diagram_p);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }

  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void postRefresh(DDiagram diagram_p) {
    try {
      FunctionalChainServices.getFunctionalChainServices().updateFunctionalChainStyles(diagram_p);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFunctionalChainStyle, e);
    }

  }

  protected void updateFunctionalExchangeCategories(DDiagramContents context_p) {

    DDiagram diagram = context_p.getDDiagram();

    if (diagram.isSynchronized()) {
      FaServices service = FaServices.getFaServices();
      Collection<EObject> categories = new HashSet<EObject>();

      // Switch to FE categories
      EdgeMapping edgeMapping = service.getMappingFECategory(diagram);
      if (edgeMapping != null) {
        for (DDiagramElement element : context_p.getDiagramElements(edgeMapping)) {
          if ((element.getTarget() != null) && (element.getTarget() instanceof ExchangeCategory)) {
            categories.add(element.getTarget());
          }
        }
        FaServices.getFaServices().switchFECategories(context_p, (DSemanticDecorator) context_p.getDDiagram(), categories);
      }

    } else {
      FaServices.getFaServices().updateFECategories(context_p);
    }

  }

}
