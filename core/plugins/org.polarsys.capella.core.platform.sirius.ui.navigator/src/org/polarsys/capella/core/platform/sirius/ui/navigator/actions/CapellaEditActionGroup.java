/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.internal.navigator.resources.actions.EditActionGroup;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 */
public class CapellaEditActionGroup extends EditActionGroup {
  private Shell shell;
  private DeleteResourceAction deleteAction;

  /**
   * Constructor.
   * @param shell_p
   */
  public CapellaEditActionGroup(Shell shell) {
    super(shell);
    this.shell = shell;
    makeLocalActions();
  }

  /**
   * @see org.eclipse.ui.internal.navigator.resources.actions.EditActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    super.fillActionBars(actionBars);
    // Get the handler set by the super method.
    IAction deleteActionHandler = actionBars.getGlobalActionHandler(ActionFactory.DELETE.getId());
    // Update the local delete action.
    deleteAction.setEnabled(deleteActionHandler.isEnabled());
    // Set the local delete action as the active global action handler.
    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
  }

  /**
   * @see org.eclipse.ui.internal.navigator.resources.actions.EditActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    super.fillContextMenu(menu);
    menu.remove(deleteAction.getId());
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, deleteAction);
  }

  /**
   * Make local actions. <br>
   * We can't override the {@link #makeActions()} due to a call in the parent constructor and this local implementation does not have the shell yet.
   */
  protected void makeLocalActions() {
    
    IShellProvider shellProvider = new IShellProvider() {
      @Override
      public Shell getShell() {
        return shell;
      }
    };
    
    deleteAction = new DeleteResourceAction(shellProvider) {
      /**
       * @see org.eclipse.ui.actions.DeleteResourceAction#run()
       */
      @Override
      public void run() {
        List<?> selectedResources = getSelectedResources();
        IResource[] selectedResourcesArray = selectedResources.toArray(new IResource[selectedResources.size()]);
        boolean containsOnlyProjects = containsOnlyProjects(selectedResourcesArray);
        if (containsOnlyProjects) {
          SessionHelper.closeUiSessions(selectedResources);
        }
        List<IProject> hostingProjects = containsCapellaOrAirdResources(selectedResourcesArray);
        if (null != hostingProjects) {
          SessionHelper.closeUiSessions(hostingProjects);
        }
        // Delete the resource now.
        super.run();
      }

      /**
       * Returns whether the selection contains Capella resources or diagram resources.<br>
       * If resulting list is not <code>null</code>, that means yes, that contains Capella resources or diagrams ones.
       * @param resources the selected resources
       * @return a not <code>null</code> list means yes, and the resulting list contains related parent projects. <code>null</code> means no session to close
       *         (if required).
       */
      protected List<IProject> containsCapellaOrAirdResources(IResource[] selectedResourcesArray) {
        HashSet<IProject> hostingProjects = null;
        // Loop over resources.
        for (IResource currentResource : selectedResourcesArray) {
          String fileExtension = currentResource.getFileExtension();
          // Must be a file with the appropriate extension.
          if ((IResource.FILE == currentResource.getType())
              && (CapellaResourceHelper.isCapellaResource(currentResource) || CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(fileExtension) || CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION
                  .equals(fileExtension))) {
            if (null == hostingProjects) {
              // Lazy creation pattern.
              hostingProjects = new HashSet<IProject>(1);
            }
            // Add its parent project in resulting collection.
            hostingProjects.add(currentResource.getProject());
          }
        }
        return (null != hostingProjects) ? new ArrayList<IProject>(hostingProjects) : null;
      }

      /**
       * Returns whether the selection contains only projects.
       * @param resources the selected resources
       * @return <code>true</code> if the resources contains only projects, and <code>false</code> otherwise
       */
      protected boolean containsOnlyProjects(IResource[] resources) {
        int types = getSelectedResourceTypes(resources);
        // note that the selection may contain multiple types of resource
        return types == IResource.PROJECT;
      }

      /**
       * Returns a bit-mask containing the types of resources in the selection.
       * @param resources the selected resources
       */
      protected int getSelectedResourceTypes(IResource[] resources) {
        int types = 0;
        for (int i = 0; i < resources.length; i++) {
          types |= resources[i].getType();
        }
        return types;
      }
    };
    ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
    deleteAction.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    deleteAction.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    deleteAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_DELETE);
  }

  /**
   * @see org.eclipse.ui.internal.navigator.resources.actions.EditActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    super.updateActionBars();
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    deleteAction.selectionChanged(selection);
  }
}
