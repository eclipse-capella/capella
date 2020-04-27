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
package org.polarsys.capella.core.commands.preferences.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.progress.IProgressService;

/**
 * A property page for viewing and modifying the set of projects referenced by a given project.
 */
public class ProjectReferencesPreferencePage extends PropertyPage {

  /*
   * 
   */
  IProject project;

  /*
   * 
   */
  boolean modified = false;

  /*
   * 
   */
  CheckboxTableViewer listViewer;

  /*
   * @see PreferencePage#createContents
   */
  @Override
  protected Control createContents(Composite parent) {

    Composite composite = new Composite(parent, SWT.NONE);

    initialize();

    createDescriptionLabel(composite);

    listViewer = CheckboxTableViewer.newCheckList(composite, SWT.TOP | SWT.BORDER);

    if (!project.isOpen()) {
      listViewer.getControl().setEnabled(false);
    }

    listViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
    listViewer.setContentProvider(getContentProvider(project));
    listViewer.setComparator(new ViewerComparator());
    listViewer.setInput(project.getWorkspace());
    try {
      listViewer.setCheckedElements(project.getDescription().getReferencedProjects());
    } catch (CoreException e) {
      // don't initial-check anything
    }

    // check for initial modification to avoid work if no changes are made
    listViewer.addCheckStateListener(new ICheckStateListener() {
      @Override
      public void checkStateChanged(CheckStateChangedEvent event) {
        if (event.getChecked()) {
          listViewer.setCheckedElements(new Object[] { event.getElement() });
        }
        modified = true;
      }
    });

    listViewer.addFilter(new ProjectSeltctionFilter());

    applyDialogFont(composite);

    GridLayoutFactory.fillDefaults().generateLayout(composite);

    return composite;
  }

  /**
   * Returns a content provider for the list dialog. It will return all projects in the workspace except the given project, plus any projects referenced by the
   * given project which do no exist in the workspace.
   * @param prj the project to provide content for
   * @return the content provider that shows the project content
   */
  protected IStructuredContentProvider getContentProvider(final IProject prj) {
    return new CapellaProjectPropertyContentProvider() {
      @Override
      public Object[] getChildren(Object o) {
        if (!(o instanceof IWorkspace)) {
          return new Object[0];
        }

        // Collect all the projects in the workspace except the given project
        IProject[] projects = ((IWorkspace) o).getRoot().getProjects();
        List<IProject> referenced = new ArrayList<IProject>(projects.length);
        boolean found = false;
        for (IProject project2 : projects) {
          if (!found && project2.equals(prj)) {
            found = true;
            continue;
          }
          referenced.add(project2);
        }

        // Add any referenced that do not exist in the workspace currently
        try {
          projects = prj.getDescription().getReferencedProjects();
          for (int i = 0; i < projects.length; i++) {
            if (!referenced.contains(projects[i])) {
              referenced.add(projects[i]);
            }
          }
        } catch (CoreException e) {
          // Ignore core exceptions
        }

        return referenced.toArray();
      }

    };
  }

  /**
   * Handle the exception thrown when saving.
   * @param e the exception
   */
  protected void handle(InvocationTargetException e) {
    IStatus error;
    Throwable target = e.getTargetException();
    if (target instanceof CoreException) {
      error = ((CoreException) target).getStatus();
    } else {
      String msg = target.getMessage();
      if (msg == null) {
        msg = "Internal error"; //$NON-NLS-1$
      }
      error = new Status(IStatus.ERROR, "org.eclipse.ui.ide", 1, msg, target); //$NON-NLS-1$
    }
    ErrorDialog.openError(getControl().getShell(), null, null, error);
  }

  /**
   * Initializes a ProjectReferencePage.
   */
  private void initialize() {
    project = (IProject) getElement().getAdapter(IResource.class);
    noDefaultAndApplyButton();
    setDescription(NLS
        .bind(
            "Capella Projects may refer to other Capella Configuration projects in the workspace.\nUse this page to specify what other Capella Configuration Projects are referenced by the project.\n\n&Project references for ''{0}'':", //$NON-NLS-1$
            project.getName()));
  }

  /**
   * @see PreferencePage#performOk
   */
  @Override
  public boolean performOk() {
    if (!modified) {
      return true;
    }
    Object[] checked = listViewer.getCheckedElements();
    final IProject[] refs = new IProject[checked.length];
    System.arraycopy(checked, 0, refs, 0, checked.length);
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException {

        try {
          IProjectDescription description = project.getDescription();
          description.setReferencedProjects(refs);
          project.setDescription(description, monitor);
        } catch (CoreException e) {
          throw new InvocationTargetException(e);
        }
      }
    };
    IProgressService service = PlatformUI.getWorkbench().getProgressService();
    try {
      service.run(false, false, runnable);
    } catch (InterruptedException e) {
      // Ignore interrupted exceptions
    } catch (InvocationTargetException e) {
      handle(e);
      return false;
    }
    return true;
  }
}
