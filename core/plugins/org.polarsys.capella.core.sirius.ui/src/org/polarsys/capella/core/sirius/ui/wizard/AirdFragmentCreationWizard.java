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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * Wizard to create a new aird fragment.
 */
public class AirdFragmentCreationWizard extends Wizard {
  /**
   * The wizard page to create a new diagram file.
   */
  private AirdFileCreationWizardPage _diagramModelFilePage;
  /**
   * Uri for the new fragment.
   */
  private URI _uri;

  /**
   * Constructor.
   */
  public AirdFragmentCreationWizard(URI defaultUri_p) {
    super();
    _uri = defaultUri_p;
    setWindowTitle(Messages.CreateAirdResourceWizard_Title);
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    _diagramModelFilePage =
        new AirdFileCreationWizardPage(AirdFileCreationWizardPage.class.getSimpleName(), new StructuredSelection(getFile(_uri)),
            CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION);
    addPage(_diagramModelFilePage);
  }

  /**
   * Get file from specified URI.
   * @param uri_p
   * @return
   */
  protected IFile getFile(URI uri_p) {
    IPath path = new Path(URI.decode(uri_p.path())).removeFirstSegments(1);
    return FileHelper.getPlatformFile(path.toString());
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    _uri = _diagramModelFilePage.getURI();
    return true;
  }

  /**
   * Get the URI of created AIRD fragment.
   * @return
   */
  public URI getResult() {
    return _uri;
  }
}
