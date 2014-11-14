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
package org.polarsys.capella.core.dashboard.actions.la;

import java.util.List;

import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Section filter for Allocate logical function to logical components.
 */
public class AllocateLogicalFunctionToLogicalComponentsSectionFilteringAction extends AbstractLAViewerFilteringAction {
  /**
   * Constructor.
   * @param capellaArchitecturePage_p
   */
  public AllocateLogicalFunctionToLogicalComponentsSectionFilteringAction(AbstractCapellaArchitectureDashboardPage capellaArchitecturePage_p) {
    super(capellaArchitecturePage_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.actions.AbstractViewerFilteringAction#getRetainedRepresentationDescriptions()
   */
  @Override
  protected List<String> getRetainedRepresentationDescriptions() {
    String[] retainedRepresentationDescriptions =
        { IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME, IDiagramNameConstants.LOGICAL_COMPONENTS_LOGICAL_FUNCTIONS_DIAGRAM_NAME,
         IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME };
    return MiscHelper.asList(retainedRepresentationDescriptions);
  }
}
