/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Thales - Contributor
 *     
 *     
 *  @see {@link org.eclipse.ui.internal.navigator.resources.actions.ResourceMgmtActionProvider}
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.CloseResourceAction;
import org.eclipse.ui.actions.CloseUnrelatedProjectsAction;
import org.eclipse.ui.actions.OpenResourceAction;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CapellaEditActionGroup;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.RefreshAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * @see {@link org.eclipse.ui.internal.navigator.resources.actions.ResourceMgmtActionProvider}
 *         Open action is private which forces us to duplicate the code.<br>
 *         Build action is removed (not necessary in Capella context).
 */
public class ProjectActionProvider extends CommonActionProvider {

  private OpenResourceAction openProjectAction;

  private CloseResourceAction closeProjectAction;

  private CloseUnrelatedProjectsAction closeUnrelatedProjectsAction;

  private RefreshAction refreshAction;

  private Shell shell;

  private CapellaEditActionGroup editActionGroup;

  @Override
  public void fillActionBars(IActionBars actionBars) {
    actionBars.setGlobalActionHandler(ActionFactory.REFRESH.getId(), refreshAction);
    actionBars.setGlobalActionHandler(IDEActionFactory.OPEN_PROJECT.getId(), openProjectAction);
    if (openProjectAction.isEnabled()) {
      actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openProjectAction);
    }
    actionBars.setGlobalActionHandler(IDEActionFactory.CLOSE_PROJECT.getId(), closeProjectAction);
    actionBars.setGlobalActionHandler(IDEActionFactory.CLOSE_UNRELATED_PROJECTS.getId(), closeUnrelatedProjectsAction);
    updateActionBars();
    // Capella Action Group.
    editActionGroup.fillActionBars(actionBars);
  }

  /**
   * Adds the build, open project, close project and refresh resource actions to the context menu.
   * <p>
   * The following conditions apply: build-only projects selected, auto build disabled, at least one builder present open project-only projects selected, at
   * least one closed project close project-only projects selected, at least one open project refresh-no closed project selected
   * </p>
   * <p>
   * Both the open project and close project action may be on the menu at the same time.
   * </p>
   * <p>
   * No disabled action should be on the context menu.
   * </p>
   * @param menu context menu to add actions to
   */
  @SuppressWarnings("rawtypes")
  @Override
  public void fillContextMenu(IMenuManager menu) {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    boolean isProjectSelection = true;
    boolean hasOpenProjects = false;
    boolean hasClosedProjects = false;
    Iterator resources = selection.iterator();

    while (resources.hasNext()) {
      Object next = resources.next();
      IProject project = null;

      if (next instanceof IProject) {
        project = (IProject) next;
      } else if (next instanceof IAdaptable) {
        project = ((IAdaptable) next).getAdapter(IProject.class);
      }

      if (project == null) {
        isProjectSelection = false;
        continue;
      }
      if (project.isOpen()) {
        hasOpenProjects = true;
      } else {
        hasClosedProjects = true;
      }
    }
    if (!hasClosedProjects) {
      refreshAction.selectionChanged(selection);
      menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, refreshAction);
    }
    if (isProjectSelection) {
      if (hasClosedProjects) {
        openProjectAction.selectionChanged(selection);
        menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, openProjectAction);
      }
      if (hasOpenProjects) {
        closeProjectAction.selectionChanged(selection);
        menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, closeProjectAction);
        closeUnrelatedProjectsAction.selectionChanged(selection);
        menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, closeUnrelatedProjectsAction);
      }
    }
    // Capella Action Group.
    editActionGroup.fillContextMenu(menu);
  }

  /**
   * Returns the image descriptor with the given relative path.
   */
  protected ImageDescriptor getImageDescriptor(String relativePath) {
    return IDEWorkbenchPlugin.getIDEImageDescriptor(relativePath);
  }

  /**
   * @see org.eclipse.ui.internal.navigator.resources.actions.ResourceMgmtActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);
    shell = site.getViewSite().getShell();
    makeActions();
    editActionGroup = new CapellaEditActionGroup(shell);
  }

  protected void makeActions() {
    openProjectAction = new OpenResourceAction(shell);

    closeProjectAction = new CloseResourceAction(shell) {
      /**
       * @see org.eclipse.ui.actions.CloseResourceAction#run()
       */
      @Override
      public void run() {
        SessionHelper.closeUiSessions(getSelectedResources());
        // Close the project now.
        super.run();
      }
    };

    closeUnrelatedProjectsAction = new CloseUnrelatedProjectsAction(shell);

    refreshAction = new RefreshAction(shell);

    refreshAction.setDisabledImageDescriptor(getImageDescriptor("dlcl16/refresh_nav.gif"));//$NON-NLS-1$
    refreshAction.setImageDescriptor(getImageDescriptor("elcl16/refresh_nav.gif"));//$NON-NLS-1$       
    refreshAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#setContext(org.eclipse.ui.actions.ActionContext)
   */
  @Override
  public void setContext(ActionContext context) {
    super.setContext(context);
    editActionGroup.setContext(context);
  }

  @Override
  public void updateActionBars() {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    refreshAction.selectionChanged(selection);
    openProjectAction.selectionChanged(selection);
    closeUnrelatedProjectsAction.selectionChanged(selection);
    closeProjectAction.selectionChanged(selection);
    // Capella Action Group.
    editActionGroup.updateActionBars();
  }
}
