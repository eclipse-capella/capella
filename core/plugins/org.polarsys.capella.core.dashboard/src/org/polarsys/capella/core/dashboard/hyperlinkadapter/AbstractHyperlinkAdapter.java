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
package org.polarsys.capella.core.dashboard.hyperlinkadapter;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Base class to implement an {@link HyperlinkAdapter} in Capella context.
 */
public abstract class AbstractHyperlinkAdapter extends HyperlinkAdapter {
  /**
   * Capella project.
   */
  protected Project _project;
  /**
   * Sirius session.
   */
  private Session _session;

  /**
   * Constructor.
   * @param capellaProject_p
   */
  public AbstractHyperlinkAdapter(Project capellaProject_p, Session session_p) {
    _project = capellaProject_p;
    _session = session_p;
  }

  /**
   * Get the model element that the run is performed against.<br>
   * @param project_p
   * @return
   */
  protected abstract ModelElement getModelElement(Project project_p);

  /**
   * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  public void linkActivated(HyperlinkEvent event_p) {
    linkPressed(event_p, _project, _session);
  }

  /**
   * Called when link is activated i.e pressed by the end-user.
   * @param event_p
   * @param capellaProject_p
   * @param session_p
   */
  protected abstract void linkPressed(HyperlinkEvent event_p, Project capellaProject_p, Session session_p);
}
