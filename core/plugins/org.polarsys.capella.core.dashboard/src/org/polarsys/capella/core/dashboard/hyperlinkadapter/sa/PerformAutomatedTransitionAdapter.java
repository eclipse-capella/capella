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

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.ui.actions.FunctionalTransitionAction;

/**
 * Perform an automated transition of Operational Activities.
 */
public class PerformAutomatedTransitionAdapter extends AbstractHyperlinkAdapter {
  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  public PerformAutomatedTransitionAdapter(Session session_p) {
    super(session_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#linkPressed(org.eclipse.ui.forms.events.HyperlinkEvent,
   *      org.polarsys.capella.core.data.capellamodeller.Project, org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  protected void linkPressed(HyperlinkEvent event_p, Project capellaProject_p, Session session_p) {
    FunctionalTransitionAction action = new FunctionalTransitionAction();
    action.selectionChanged(TransitionAction.DEFAULT_ACTION, new StructuredSelection(getModelElement(_project)));
    action.run(TransitionAction.DEFAULT_ACTION);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#getModelElement(org.polarsys.capella.core.data.capellamodeller.Project)
   */
  @Override
  protected ModelElement getModelElement(Project project_p) {
    return ModelQueryHelper.getRootOperationalActivity(project_p);
  }
}
