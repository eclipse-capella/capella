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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * The action allowing to rename representations.
 */
public class RenameRepresentationAction extends BaseSelectionListenerAction {
  /**
   * Constructs the action allowing to rename representations.
   */
  public RenameRepresentationAction() {
    super("Rename"); //$NON-NLS-1$
    setActionDefinitionId("org.eclipse.ui.edit.rename"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection_p) {
    // The text control should not be editable if the element is readonly (locked by other)
    IReadOnlySectionHandler handler = CapellaReadOnlyHelper.getReadOnlySectionHandler();

    if (handler != null) {
      for (Iterator<?> iterator = selection_p.iterator(); iterator.hasNext();) {
        Object selectedObject = iterator.next();

        if (selectedObject instanceof DRepresentation) {
          if (handler.isLockedByOthers((DRepresentation) selectedObject)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Gets all the selected representations.
    List<DRepresentation> representations = new ArrayList<DRepresentation>();
    IStructuredSelection structuredSelection = getStructuredSelection();
    for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
      Object selectedObject = iterator.next();
      if (selectedObject instanceof ItemWrapper) {
        selectedObject = ((ItemWrapper) selectedObject).getWrappedObject();
      }
      if (selectedObject instanceof DRepresentation) {
        representations.add((DRepresentation) selectedObject);
      }
    }

    // Parses the selected representations and rename them.
    for (DRepresentation representation : representations) {
      final String oldName = (representation.getName() != null) ? representation.getName() : StringUtil.EMPTY_STRING;

      // To provide a title we need sub-classing the RenameDialog.
      RenameDialog dialog = new RenameDialog(Display.getDefault().getActiveShell(), oldName) {
        /**
         * @see org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog#open()
         */
        @Override
        public int open() {
          // The super class calls create() too late, let's call it as the super super class does.
          create();
          return super.open();
        }

        /**
         * @see org.eclipse.ui.dialogs.SelectionStatusDialog#create()
         */
        @Override
        public void create() {
          super.create();
          setTitle(Messages.RenameRepresentationAction_Title);
          // Set the old name here allow to have the preferred size correctly computed, even if the old name was already provided with the constructor.
          setOldName(oldName);

          // Force the window to compute its preferred size.
          getShell().setSize(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));
        }
      };
      if (Window.OK == dialog.open()) {
        String newName = dialog.getNewName();
        if (!oldName.equals(newName)) {
          TransactionHelper.getExecutionManager(representation).execute(new RenameRepresentationCommand(representation, newName));
        }
      }
    }
  }

  // The rename representation command.
  private class RenameRepresentationCommand extends AbstractReadWriteCommand {
    // The new name to apply.
    private String _name;
    // The selected representation.
    private DRepresentation _representation;

    /**
     * Constructs the command allowing to rename the selected representation.
     * @param representation_p The representation.
     * @param name_p The new name to apply.
     */
    public RenameRepresentationCommand(DRepresentation representation_p, String name_p) {
      _name = name_p;
      _representation = representation_p;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
      _representation.setName(_name);
    }
  }
}
