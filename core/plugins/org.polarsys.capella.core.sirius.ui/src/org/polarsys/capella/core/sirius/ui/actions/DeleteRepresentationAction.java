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

package org.polarsys.capella.core.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
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
    setActionDefinitionId("org.eclipse.ui.edit.delete"); //$NON-NLS-1$
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
    List<DRepresentation> selectedRepresentations = getRepresentationsFromSelection();
    if (!selectedRepresentations.isEmpty()) {
      int deletedDiagramCount = selectedRepresentations.size();
      String contextualMessage = null;
      if (deletedDiagramCount == 1) {
        contextualMessage =
            StringHelper.formatMessage(Messages.DeleteRepresentationAction_One_Diagram_Message, new String[] { selectedRepresentations.get(0).getName() });
      } else {
        contextualMessage =
            StringHelper.formatMessage(Messages.DeleteRepresentationAction_Multiple_Diagram_Message, new String[] { String.valueOf(deletedDiagramCount) });
      }
      if (MessageDialog.openConfirm(null, Messages.DeleteRepresentationAction_Title, Messages.DeleteRepresentationAction_Message + contextualMessage)) {
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(selectedRepresentations);
        if (null != domain) {
          DeleteRepresentationCommand command = new DeleteRepresentationCommand(domain, selectedRepresentations);
          domain.getCommandStack().execute(command);
        }
      }
    }
  }

  // Gets the selected representations from the current selection.
  private List<DRepresentation> getRepresentationsFromSelection() {
    List<DRepresentation> representations = new ArrayList<DRepresentation>();

    IStructuredSelection structuredSelection = getStructuredSelection();
    for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
      Object selectedObject = iterator.next();

      if (selectedObject instanceof ItemWrapper) {
        selectedObject = ((ItemWrapper) selectedObject).getWrappedObject();
      }

      // Builds representations list with representation which are semantic decorators.
      // Semantic decorators allow to retrieve the parent session of each representation.
      if ((selectedObject instanceof DRepresentation) && (selectedObject instanceof DSemanticDecorator)) {
        DRepresentation selectedRepresentation = (DRepresentation) selectedObject;
        representations.add(selectedRepresentation);
      }
    }
    return representations;
  }
}
