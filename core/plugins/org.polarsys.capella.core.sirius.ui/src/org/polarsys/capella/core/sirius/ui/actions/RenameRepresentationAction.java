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
package org.polarsys.capella.core.sirius.ui.actions;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
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
    setActionDefinitionId(IWorkbenchCommandConstants.FILE_RENAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    try {
      return !getRenamableRepresentationDescriptors(selection).isEmpty();
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        return false;
      } else {
        throw e;
      }
    }
  }

  /**
   * The action is enabled if at least one valid representation is not readonly (locked by other)
   */
  private List<DRepresentationDescriptor> getRenamableRepresentationDescriptors(IStructuredSelection selection) {
    IReadOnlySectionHandler handler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    return RepresentationHelper.getSelectedDescriptors(selection.toList()).stream()
        .filter(RepresentationHelper::isValid).filter(descriptor -> {
          return (handler == null || (handler != null && !handler.isLockedByOthers(descriptor)));
        }).collect(Collectors.toList());
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Gets all the selected representations.
    IStructuredSelection selection = getStructuredSelection();
    List<DRepresentationDescriptor> descriptors = getRenamableRepresentationDescriptors(selection);

    // Parses the selected representations and rename them.
    for (DRepresentationDescriptor descriptor : descriptors) {
      final String oldName = (descriptor.getName() != null) ? descriptor.getName() : StringUtil.EMPTY_STRING;

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
          // Set the old name here allow to have the preferred size correctly computed, even if the old name
          // was already
          // provided with the constructor.
          setOldName(oldName);

          // Force the window to compute its preferred size.
          getShell().setSize(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));
        }
      };
      if (Window.OK == dialog.open()) {
        String newName = dialog.getNewName();
        if (!oldName.equals(newName)) {
          TransactionHelper.getExecutionManager(descriptor)
              .execute(new RenameRepresentationCommand(descriptor, newName));
        }
      }
    }
  }

  // The rename representation command.
  private class RenameRepresentationCommand extends AbstractReadWriteCommand {
    // The new name to apply.
    private String newName;

    // The selected representation.
    private DRepresentationDescriptor selectedRepresentation;

    @Override
    public String getName() {
      return RenameRepresentationAction.this.getText();
    }

    /**
     * Constructs the command allowing to rename the selected representation.
     * 
     * @param representation
     *          The representation.
     * @param name
     *          The new name to apply.
     */
    public RenameRepresentationCommand(DRepresentationDescriptor representation, String name) {
      newName = name;
      selectedRepresentation = representation;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      if (!selectedRepresentation.isLoadedRepresentation()) {
        selectedRepresentation.getRepresentation();
      }
      selectedRepresentation.setName(newName);
    }
  }
}
