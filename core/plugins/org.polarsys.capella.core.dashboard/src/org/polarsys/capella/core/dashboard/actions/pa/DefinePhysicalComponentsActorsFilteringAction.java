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
package org.polarsys.capella.core.dashboard.actions.pa;

import java.util.List;

import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Section filter for Define Physical Components and Actors.
 */
public class DefinePhysicalComponentsActorsFilteringAction extends AbstractPAViewerFilteringAction {
  /**
   * Constructor.
   * @param capellaArchitecturePage_p
   */
  public DefinePhysicalComponentsActorsFilteringAction(AbstractCapellaArchitectureDashboardPage capellaArchitecturePage_p) {
    super(capellaArchitecturePage_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.actions.AbstractViewerFilteringAction#getRetainedRepresentationDescriptions()
   */
  @Override
  protected List<String> getRetainedRepresentationDescriptions() {
    String[] retainedRepresentationDescriptions = {
      IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME,
      IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME,
      IDiagramNameConstants.PHYSICAL_ACTOR_LOGICAL_ACTOR_DIAGRAM_NAME
    };
    return MiscHelper.asList(retainedRepresentationDescriptions);
  }
}
