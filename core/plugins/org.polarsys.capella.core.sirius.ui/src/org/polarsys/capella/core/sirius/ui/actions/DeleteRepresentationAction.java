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
package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.DeleteRepresentationCommand;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * The actions allowing to delete representation from selection.
 */
public class DeleteRepresentationAction extends BaseSelectionListenerAction {
  /**
   * Constructs the actions allowing to delete representation from selection.
   */
  public DeleteRepresentationAction() {
    super("Delete"); //$NON-NLS-1$
    setActionDefinitionId(IWorkbenchCommandConstants.EDIT_DELETE);
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Gets selected representations from the current selection.
    IStructuredSelection selection = getStructuredSelection();

    Collection<DRepresentationDescriptor> selectedRepresentations = RepresentationHelper.getSelectedDescriptors(selection.toList());
    
    if (!selectedRepresentations.isEmpty()) {
      int deletedDiagramCount = selectedRepresentations.size();
      String contextualMessage = null;
      String name = String.join(", ",
          selectedRepresentations.stream().map(d -> d.getName()).collect(Collectors.toList()));

      if (deletedDiagramCount == 1) {
        contextualMessage = NLS.bind(Messages.DeleteRepresentationAction_One_Diagram_Message, name);
      } else {
        contextualMessage = NLS.bind(Messages.DeleteRepresentationAction_Multiple_Diagram_Message, name);
      }
      
      if (MessageDialog.openConfirm(null, Messages.DeleteRepresentationAction_Title,
          Messages.DeleteRepresentationAction_Message + contextualMessage)) {
        
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(selectedRepresentations);
        if (null != domain) {
          DeleteRepresentationCommand command = new DeleteRepresentationCommand(domain, selectedRepresentations);
          domain.getCommandStack().execute(command);
        }
      }
    }
  }

}
