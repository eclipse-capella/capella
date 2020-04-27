/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation;

import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class MoveRepresentationAction extends Action {

  private static final ImageDescriptor ACTION_IMAGE_DESCRIPTOR = AbstractUIPlugin
      .imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/forward.gif"); //$NON-NLS-1$

  private DAnalysis targetAnalysis;
  private DAnalysisSession session;
  private Collection<DRepresentationDescriptor> descriptors;

  public MoveRepresentationAction(DAnalysisSession session, Collection<DRepresentationDescriptor> descriptors,
      DAnalysis targetAnalysis) {

    String actionLabel = Messages.MoveRepresentationAction_Title + targetAnalysis.eResource().getURI();
    setId(actionLabel);
    setText(actionLabel);
    setImageDescriptor(ACTION_IMAGE_DESCRIPTOR);

    this.targetAnalysis = targetAnalysis;
    this.session = session;
    this.descriptors = descriptors;
  }

  @Override
  public void run() {
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(descriptors);

    if (executionManager == null) {
      return;
    }

    executionManager.execute(new AbstractReadWriteCommand() {
      public void run() {
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
          for (DRepresentationDescriptor descriptor : descriptors) {
            closeOpenedEditor(uiSession, descriptor);
          }
        }
        for (DRepresentationDescriptor descriptor : descriptors) {
          session.moveRepresentation(targetAnalysis, descriptor);
        }

        // The representation is moved to a new resource, so the dynamic menu item needs to be reconstructed in order to
        // take into account this change.
        try {
          ISelectionProvider selectionProvider = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .getActivePart().getSite().getSelectionProvider();

          // We force the selection update in order to reconstruct the dynamic menu.
          ISelection currentSelection = selectionProvider.getSelection();
          selectionProvider.setSelection(currentSelection);

        } catch (NullPointerException e) {
          // Safely to do nothing.
        }

      }

      private void closeOpenedEditor(IEditingSession uiSession, DRepresentationDescriptor descriptor) {
        if (descriptor.isLoadedRepresentation()) {
          IEditorPart editor = uiSession.getEditor(descriptor.getRepresentation());
          if (editor != null) {
            editor.getEditorSite().getPage().closeEditor(editor, false);
          }
        }
      }
    });
  }
}
