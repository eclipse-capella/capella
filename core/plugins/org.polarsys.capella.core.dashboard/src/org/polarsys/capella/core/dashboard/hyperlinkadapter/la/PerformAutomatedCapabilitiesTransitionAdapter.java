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
package org.polarsys.capella.core.dashboard.hyperlinkadapter.la;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.ui.actions.CapabilityTransitionAction;

/**
 * Perform an automated transition of Capabilities from source architecture to target architecture
 */
public class PerformAutomatedCapabilitiesTransitionAdapter extends AbstractHyperlinkAdapter {

  /*
	 * 
	 */
  private ModelElement transitionSourceModel;

  /**
   * Constructor.
   * @param capellaProject_p
   * @param sourceArchitecture
   * @param session_p
   */
  public PerformAutomatedCapabilitiesTransitionAdapter(BlockArchitecture sourceArchitecture, Session session_p) {
    super(session_p);
    this.transitionSourceModel = sourceArchitecture;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#linkPressed(org.eclipse.ui.forms.events.HyperlinkEvent,
   *      org.polarsys.capella.core.data.capellamodeller.Project, org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  protected void linkPressed(HyperlinkEvent event, Project capellaProject_p, Session session_p) {
    CapabilityTransitionAction action = new CapabilityTransitionAction();
    action.selectionChanged(TransitionAction.DEFAULT_ACTION, new StructuredSelection(getModelElement(_project)));
    action.run(TransitionAction.DEFAULT_ACTION);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#getModelElement(org.polarsys.capella.core.data.capellamodeller.Project)
   */
  @Override
  protected ModelElement getModelElement(Project project) {
    return this.transitionSourceModel;
  }
}
