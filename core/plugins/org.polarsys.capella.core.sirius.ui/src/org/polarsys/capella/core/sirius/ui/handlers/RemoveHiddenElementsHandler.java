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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * This CommandHandler allows to remove hidden elements from diagrams.
 */
public class RemoveHiddenElementsHandler extends AbstractDiagramCommandHandler {

  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    Object selectedElement = getSelection().getFirstElement();
    Session session = getSession(selectedElement);
    //
    // Compute representations to process.
    //
    Collection<DRepresentationDescriptor> representationsToRefresh = Collections.emptyList();
    if (selectedElement instanceof ModelElement) {
      // Selected element is a ModelElement, only diagrams under this ModelElement are considered.
      representationsToRefresh = new ArrayList<DRepresentationDescriptor>();
      Collection<DRepresentationDescriptor> allSessionRepresentations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
      for (DRepresentationDescriptor representation : allSessionRepresentations) {
          EObject associatedModelElement = representation.getTarget();
          if (EcoreUtil.isAncestor((ModelElement) selectedElement, associatedModelElement)) {
            representationsToRefresh.add(representation);
        }
      }
    } else if (selectedElement instanceof IFile) {
      // Selected element is an IFile (the aird file), all diagrams are processed.
      representationsToRefresh = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    }
    // Is there a representation to process ?
    if (representationsToRefresh.isEmpty()) {
      // No representation -> processing is over.
      MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.RemoveHiddenElementsHandler_ConfirmRefreshDialog_Title,
          Messages.RemoveHiddenElementsHandler_NoDiagramDialog_Text);
      return null;
    }
    // Ask user confirmation.
    MessageDialogWithToggle messageDialogWithToggle =
        new MessageDialogWithToggle(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.RemoveHiddenElementsHandler_ConfirmRefreshDialog_Title,
            null, Messages.RemoveHiddenElementsHandler_ConfirmRefreshDialog_Text, 0, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL },
            1, "Unsynchronize diagrams", true); //$NON-NLS-1$

    int returnCode = messageDialogWithToggle.open();
    boolean deleteHiddenConfirmed = (IDialogConstants.OK_ID == returnCode);
    boolean unsyncDiagrams = messageDialogWithToggle.getToggleState();

    if (!deleteHiddenConfirmed) {
      return null;
    }
    //
    // Create refresh job and schedule it.
    //
    Job job = new DeleteHiddenElementsJob(representationsToRefresh, session, unsyncDiagrams);
    job.setThread(Display.getDefault().getThread());
    job.setUser(true);
    job.schedule();

    return null;
  }

  /**
   * Get the Session associated with the given object.
   * @param object_p the object from which to find a Session (must be an IFile or a ModelElement).
   * @return the found session or <code>null</code> if no session can be found.
   */
  protected Session getSession(Object object_p) {
    Session session = null;
    if (object_p instanceof IFile) {
      session = SessionHelper.getSessionForDiagramFile((IFile) object_p);
    } else if (object_p instanceof ModelElement) {
      session = SessionManager.INSTANCE.getSession((ModelElement) object_p);
    }
    return session;
  }

  /**
   * CommandHandler is enabled if current selection contains an IFile or a ModelElement from which a Session can be get.
   * @return
   */
  @Override
  public boolean isEnabled() {
    IStructuredSelection currentSelection = getSelection();
    if (currentSelection.isEmpty()) {
      return false;
    }
    Object selectedElement = currentSelection.getFirstElement();
    // Check a session could be get from the selected element.
    Session selectedElementSession = getSession(selectedElement);
    return (null != selectedElementSession);
  }
}
