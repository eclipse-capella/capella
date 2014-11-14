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

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * Open Capella Dashboard for current aird file (if related session is open).
 */
public class OpenCapellaDashboardAction extends BaseSelectionListenerAction {
  /**
   * Constructor.
   */
  protected OpenCapellaDashboardAction() {
    super(Messages.OpenCapellaDashboardAction_Title);
    setImageDescriptor(CapellaDashboardActivator.getDefault().getImageDescriptor(IImageKeys.IMG_CAPELLA_OVERVIEW));
  }

  /**
   * Get session from given selection.
   * @return
   */
  private Session getSession() {
    IStructuredSelection selection = getStructuredSelection();
    Session session = null;
    if (!selection.isEmpty()) {
      Object selectedElement = selection.getFirstElement();
      // Ensure given selection is aird file with an open session.
      if ((selectedElement instanceof IFile) && CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(((IFile) selectedElement).getFileExtension())) {
        session = SessionHelper.getSessionForDiagramFile((IFile) selectedElement);
      }
    }
    return session;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    Session session = getSession();
    if (null != session) {
      OpenSessionAction.openCapellaDashboard(session);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection_p) {
    return null != getSession();
  }
}
