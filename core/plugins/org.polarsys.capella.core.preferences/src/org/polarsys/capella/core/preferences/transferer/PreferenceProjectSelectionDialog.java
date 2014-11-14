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

/**
 */
public class PreferenceProjectSelectionDialog extends CheckedTreeSelectionDialog {

  public static final String CONFUGRATION_PROJECT_NATURE_ID = "org.polarsys.capella.core.preferences.project.nature.configNature"; //$NON-NLS-1$
  private WizardPreferencesTransfererExportPage parentWizard;
  private final String SUFFUX_EPF_FILE_NAME = "_preferences.epf"; //$NON-NLS-1$

  /**
   * @param wizardPreferencesTransfererExportPage_p
   * @param parent_p
   * @param labelProvider_p
   * @param contentProvider_p
   */
  public PreferenceProjectSelectionDialog(WizardPreferencesTransfererExportPage wizardPreferencesTransfererExportPage_p, Shell parent_p,
      ILabelProvider labelProvider_p, ITreeContentProvider contentProvider_p) {
    super(parent_p, labelProvider_p, contentProvider_p);
    this.parentWizard = wizardPreferencesTransfererExportPage_p;
  }

  @Override
  protected void computeResult() {
    super.computeResult();
    setResult(Arrays.asList(getTreeViewer().getCheckedElements()));
    IProject project = (IProject) this.getFirstResult();
    IFile file = null;
    try {
      IResource[] members = project.members();
      for (IResource iResource : members) {
        if (iResource instanceof IFolder) {
          IFolder settingFolder = (IFolder) iResource;
          settingFolder.getLocation().append(project.getName() + SUFFUX_EPF_FILE_NAME);
          file = settingFolder.getFile(project.getName() + SUFFUX_EPF_FILE_NAME);
        }
      }
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      StringBuilder loggerMessage = new StringBuilder("PreferenceProjectSelectionDialog.computeResult(..) _ "); //$NON-NLS-1$
    }

    if (parentWizard != null) {
      StringBuffer location = new StringBuffer();
      location.append(project.getLocation().toOSString());
      location.append("\\" + project.getName() + SUFFUX_EPF_FILE_NAME);
      this.parentWizard.addDestinationItem(location.toString());

    }

    this.close();
  }

  @Override
  public void addFilter(ViewerFilter filter_p) {
    super.addFilter(new ViewerFilter() {

      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        try {
          return (element_p instanceof IProject) && (((IProject) element_p).getNature(CONFUGRATION_PROJECT_NATURE_ID) != null);
        } catch (CoreException exception) {
          StringBuilder loggerMessage = new StringBuilder(".select(..) _ "); //$NON-NLS-1$
        }
        return false;
      }
    });
  }

}
