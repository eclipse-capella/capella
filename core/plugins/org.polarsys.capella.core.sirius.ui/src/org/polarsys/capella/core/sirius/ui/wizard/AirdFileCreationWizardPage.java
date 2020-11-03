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
package org.polarsys.capella.core.sirius.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * Wizard to create a new Diagram file.
 */
public class AirdFileCreationWizardPage extends WizardNewFileCreationPage {
  /**
   * File extension.
   */
  private final String _fileExtension;
  /**
   * 
   */
  private IFile _selectedFile;

  /**
   * Constructor.
   * @param pageName_p the page name.
   * @param selection_p the selection
   * @param fileExtension_p the file extension
   */
  public AirdFileCreationWizardPage(String pageName_p, IStructuredSelection selection_p, String fileExtension_p) {
    super(pageName_p, selection_p);
    setTitle(Messages.SiriusCreationWizardPage_Title);
    setDescription(Messages.SiriusCreationWizardPage_Description);
    _fileExtension = fileExtension_p;
    if (selection_p.getFirstElement() instanceof IFile) {
      _selectedFile = (IFile) selection_p.getFirstElement();
    }
  }

  /**
   * Get the file extension. Override to create files with this extension.
   * @return the file extension
   */
  protected String getExtension() {
    return _fileExtension;
  }

  /**
   * Get the URI.
   * @return the URI
   */
  public URI getURI() {
    return URI.createPlatformResourceURI(getFilePath().toString(), true);
  }

  /**
   * Get the file path.
   * @return the file path
   */
  protected IPath getFilePath() {
    IPath path = getContainerFullPath();
    if (path == null) {
      path = new Path(ICommonConstants.EMPTY_STRING);
    }
    String fileName = getFileName();
    if (fileName != null) {
      path = path.append(fileName);
    }
    return path;
  }

  /**
   * Get the default file name.
   * @return the default file name
   */
  public String getDefaultFileName() {
    if (_selectedFile != null && _selectedFile.getFullPath().removeFileExtension().lastSegment() != null) {
      String name = _selectedFile.getFullPath().removeFileExtension().lastSegment();
      return name;
    }
    return "newDiagrams"; //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(final Composite parent_p) {
    super.createControl(parent_p);
    setFileName(getDefaultFileName() + ICommonConstants.POINT_CHARACTER + getExtension());
    setPageComplete(validatePage());
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validatePage()
   */
  @Override
  protected boolean validatePage() {
    boolean result = true;
    if (!super.validatePage()) {
      return false;
    }
    String extension = getExtension();
    if (extension != null && !getFilePath().toString().endsWith(ICommonConstants.POINT_CHARACTER + extension)) {
      setErrorMessage("The file extension is wrong."); //$NON-NLS-1$
      result = false;
    }
    return result;
  }
}
