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
package org.polarsys.capella.core.preferences.transferer;

import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 */
public class PreferenceProjectSelectionDialog extends CheckedTreeSelectionDialog {

  private WizardPreferencesTransfererExportPage parentWizard;
  
  private final String SUFFIX_EPF_FILE_NAME = "_preferences.epf"; //$NON-NLS-1$

  /**
   * @param wizardPreferencesTransfererExportPage
   * @param parent
   * @param labelProvider
   * @param contentProvider
   */
  public PreferenceProjectSelectionDialog(WizardPreferencesTransfererExportPage wizardPreferencesTransfererExportPage,
      Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
    super(parent, labelProvider, contentProvider);
    this.parentWizard = wizardPreferencesTransfererExportPage;
  }

  @Override
  protected void computeResult() {
    super.computeResult();
    setResult(Arrays.asList(getTreeViewer().getCheckedElements()));
    IProject project = (IProject) this.getFirstResult();
    IFile file = null;
    try {
      if (project != null) {
        IResource[] members = project.members();
        for (IResource iResource : members) {
          if (iResource instanceof IFolder) {
            IFolder settingFolder = (IFolder) iResource;
            settingFolder.getLocation().append(project.getName() + SUFFIX_EPF_FILE_NAME);
            file = settingFolder.getFile(project.getName() + SUFFIX_EPF_FILE_NAME);
          }
        }
      }
    } catch (CoreException exception) {
      exception.printStackTrace();
    }

    if (parentWizard != null) {
      StringBuffer location = new StringBuffer();
      location.append(project.getLocation().toOSString());
      location.append("/" + project.getName() + SUFFIX_EPF_FILE_NAME);
      this.parentWizard.addDestinationItem(location.toString());

    }

    this.close();
  }

  @Override
  public void addFilter(ViewerFilter filter) {
    super.addFilter(new ViewerFilter() {

      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element) {
        try {
          return (element instanceof IProject)
              && (((IProject) element).getNature(CapellaResourceHelper.CAPELLA_CONFIGURATION_PROJECT_NATURE) != null);
        } catch (CoreException exception) {
          exception.printStackTrace();
        }
        return false;
      }
    });
  }

}
