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
package org.polarsys.capella.core.transition.system.ui.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;

public class ProjectSelectionDialog extends ElementTreeSelectionDialog implements IResourceChangeListener {

  private Button newButton;
  private Label messageLabel;

  public ProjectSelectionDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
    super(parent, labelProvider, contentProvider);

    setTitle("Target Capella model selection");
    setMessage("Select the target Capella model for the export:");

    setHelpAvailable(false);

    setInput(ResourcesPlugin.getWorkspace().getRoot());
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this);

    addFilter(new ViewerFilter() {
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof IResource) {
          return getCapellaProjectFile((IResource) element) != null;
        }
        return false;
      }
    });

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    Composite parentc = (Composite) super.createDialogArea(parent);
    newButton = new Button(parentc, SWT.PUSH);
    newButton.setText("Create new Project");

    newButton.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent event) {
        createNewProject();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        // Nothing to do
      }
    });

    return parentc;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean close() {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    newButton.dispose();

    return super.close();
  }

  @Override
  protected Label createMessageArea(Composite composite) {
    messageLabel = super.createMessageArea(composite);
    return messageLabel;
  }

  protected void createNewProject() {
    // Instantiates and initializes the wizard
    NewProjectWizard wizard = new NewProjectWizard();
    wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);

    // Instantiates the wizard container with the wizard and opens it
    WizardDialog dialog = new WizardDialog(getShell(), wizard);
    dialog.create();
    dialog.open();
  }

  @Override
  @SuppressWarnings("unchecked")
  protected void computeResult() {
    List<String> result = new ArrayList<String>();

    IStructuredSelection selection = (IStructuredSelection) getTreeViewer().getSelection();
    Iterator<Object> it = selection.iterator();
    while (it.hasNext()) {
      Object tmp = it.next();
      if (tmp instanceof IResource) {
        result.add(getCapellaProjectFile((IResource) tmp));
      }
    }
    setResult(result);
  }

  public String getCapellaProjectFile(IResource resource) {
    if (!resource.exists()) {
      return null;
    }

    if (resource instanceof IProject) {
      if (((IProject) resource).isOpen()) {
        IProject project = (IProject) resource;

        IFile file = project.getFile(
            project.getName() + ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
        if (file.exists()) {
          return getCapellaProjectFile(file);
        }

        try {
          // retrieve first valid project
          for (IResource member : project.members()) {
            if (CapellaResourceHelper.isCapellaResource(member, true)) {
              return getCapellaProjectFile(member);
            }
          }
        } catch (CoreException exception_p) {
          // nothing
        }

      }
      return null;
    }

    if (CapellaResourceHelper.isCapellaResource(resource, true)) {
      return resource.getFullPath().toString();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resourceChanged(IResourceChangeEvent event) {

    // Enable the tree and the label
    getTreeViewer().getTree().setEnabled(true);
    messageLabel.setEnabled(true);

    // Set the isEmpty field to false
    try {
      ReflectUtil.setInvisibleFieldValue(this, "fIsEmpty", false);
    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }

    refreshDialog(event);
  }

  protected void refreshDialog(IResourceChangeEvent event) {
    // Refresh the viewer
    getTreeViewer().refresh();

    // Select the newly created project
    for (IResourceDelta delta : event.getDelta().getAffectedChildren()) {
      IResource resource = delta.getResource();
      getTreeViewer().setSelection(new StructuredSelection(resource));
      getButton(OK).forceFocus();
    }

    // Update
    updateOKStatus();
  }
}
