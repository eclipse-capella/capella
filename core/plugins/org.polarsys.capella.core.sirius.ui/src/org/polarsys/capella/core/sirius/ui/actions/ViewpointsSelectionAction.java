/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The viewpoints selection action.
 */
public class ViewpointsSelectionAction extends BaseSelectionListenerAction {
  /**
   * Constructs the viewpoints selection action.
   */
  public ViewpointsSelectionAction() {
    super("Viewpoints selection"); //$NON-NLS-1$ 
    setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/obj16/Viewpoint.gif"));//$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    IStructuredSelection selection = getStructuredSelection();
    // Checks the selection.
    if ((null != selection) && !selection.isEmpty()) {
      Session session = getSessionFromSelection(selection);
      if (null != session) {
        ViewpointSelection.openViewpointsSelectionDialog(session);
      }
    }
  }

  protected Session getSessionFromSelection(IStructuredSelection selection_p) {
    // Gets the FIRST selected session from current selection.
    Session session = null;

    Iterator<?> iterator = selection_p.iterator();
    while (iterator.hasNext() && (null == session)) {
      Object object = iterator.next();
      if (object instanceof Session) {
        session = (Session) object;
      } else if (object instanceof IFile) {
        session = SessionHelper.getSessionForDiagramFile((IFile) object);
      }
    }
    return session;
  }
}
