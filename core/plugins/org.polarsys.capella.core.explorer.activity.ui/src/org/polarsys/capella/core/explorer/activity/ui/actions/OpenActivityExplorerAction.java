/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.explorer.activity.ui.CapellaActivityExplorerActivator;
import org.polarsys.capella.core.explorer.activity.ui.IImageKeys;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * Open Activity Explorer for current aird file (if related session is open).
 */
public class OpenActivityExplorerAction extends BaseSelectionListenerAction {
  /**
   * Constructor.
   */
  public OpenActivityExplorerAction() {
    super(Messages.OpenActivityExplorerAction_Title);
    setImageDescriptor(CapellaActivityExplorerActivator.getDefault().getImageDescriptor(IImageKeys.IMG_MENU_ACTIVITY_EXPLORER));
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
      OpenSessionAction.openActivityExplorer(session);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    return null != getSession();
  }
}
