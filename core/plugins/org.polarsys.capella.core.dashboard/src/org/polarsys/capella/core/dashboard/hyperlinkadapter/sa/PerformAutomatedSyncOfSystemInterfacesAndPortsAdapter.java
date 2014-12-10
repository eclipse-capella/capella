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
package org.polarsys.capella.core.dashboard.hyperlinkadapter.sa;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.refinement.commands.GenerateInterfaceDelegationsCommand;

/**
 * Perform an automated synchronization of System interfaces and Ports.
 */
public class PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter extends AbstractHyperlinkAdapter {
  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  public PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter(Session session_p) {
    super(session_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ModelElement getModelElement(Project project_p) {
    return ModelQueryHelper.getSystem(project_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void linkPressed(HyperlinkEvent event_p, Project capellaProject_p, Session session_p) {
    TransactionHelper.getExecutionManager(capellaProject_p).execute(new GenerateInterfaceDelegationsCommand(getModelElement(capellaProject_p)));
  }
}
