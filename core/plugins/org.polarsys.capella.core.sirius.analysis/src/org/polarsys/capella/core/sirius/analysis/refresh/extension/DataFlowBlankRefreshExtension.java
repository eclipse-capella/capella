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

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
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

    try {
      DDiagramContents diagramContent = new DDiagramContents(diagram_p);
      Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(diagram_p);
      FaServices.getFaServices().showDFContextualElements(diagramContent, contextualElements);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
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

}
