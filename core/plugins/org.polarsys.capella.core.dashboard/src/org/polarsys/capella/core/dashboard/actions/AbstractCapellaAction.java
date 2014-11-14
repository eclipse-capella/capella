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
package org.polarsys.capella.core.dashboard.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.core.dashboard.actions.util.PopupMenuLinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;

/**
 * Base class to implement actions triggered by {@link PopupMenuLinkAdapter}.
 */
public abstract class AbstractCapellaAction extends Action {
  /**
   * Capella project.
   */
  private Project _project;
  /**
   * Sirius session.
   */
  private Session _session;

  /**
   * Constructor.
   * @param text_p
   * @param image_p
   * @param capellaProject_p
   * @param session_p
   */
  public AbstractCapellaAction(String text_p, ImageDescriptor image_p, Project capellaProject_p, Session session_p) {
    super(text_p, image_p);
    _project = capellaProject_p;
    _session = session_p;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    doRun(_project, _session);
  }

  /**
   * Do run this action.
   * @param capellaProject_p
   * @param session_p
   */
  protected abstract void doRun(Project capellaProject_p, Session session_p);
}
