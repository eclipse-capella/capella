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
package org.polarsys.capella.core.dashboard.hyperlinkadapter.epbs;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.refinement.commands.ScenarioRefinementCommand;

/**
 * Execute the Logical Scenario Refinement on the Scenarios of the Capability.
 */
public class ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter extends AbstractHyperlinkAdapter {
  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  public ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter(Project capellaProject_p, Session session_p) {
    super(capellaProject_p, session_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#linkPressed(org.eclipse.ui.forms.events.HyperlinkEvent, org.polarsys.capella.core.data.capellamodeller.Project, org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  protected void linkPressed(HyperlinkEvent event_p, Project capellaProject_p, Session session_p) {
    TransactionHelper.getExecutionManager(capellaProject_p).execute(
        new ScenarioRefinementCommand(getModelElement(_project), new NullProgressMonitor()));
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#getModelElement(org.polarsys.capella.core.data.capellamodeller.Project)
   */
  @Override
  protected ModelElement getModelElement(Project project_p) {
    return ModelQueryHelper.getPACapabilityRealizationPkg(project_p);
  }
}
