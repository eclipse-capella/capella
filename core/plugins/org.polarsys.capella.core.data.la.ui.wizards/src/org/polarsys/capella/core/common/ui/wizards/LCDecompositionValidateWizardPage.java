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
package org.polarsys.capella.core.common.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionGeneralViewer;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;

/**
 */
public class LCDecompositionValidateWizardPage extends WizardPage {
  private DecompositionModel decompositionModel;
  private DecompositionGeneralViewer decompositionGeneralViewer;
  private LCDecompositionController controller;

  /**
   * @return the decompositionGeneralViewer
   */
  public DecompositionGeneralViewer getDecompositionGeneralViewer() {
    return decompositionGeneralViewer;
  }

  /**
   * @param decompositionGeneralViewer the decompositionGeneralViewer to set
   */
  public void setDecompositionGeneralViewer(DecompositionGeneralViewer decompositionGeneralViewer) {
    this.decompositionGeneralViewer = decompositionGeneralViewer;
  }

  public LCDecompositionValidateWizardPage(DecompositionModel decompositionModel) {
    this("Capella Logical Component Decomposition"); //$NON-NLS-1$
    this.decompositionModel = decompositionModel;
    setPageComplete(false);
  }

  /**
   * @param pageName
   */
  protected LCDecompositionValidateWizardPage(String pageName) {
    super(pageName);
    setTitle(pageName);
    setMessage("This editor displays the Capella Logical Component Decomposition - Synthesis Check"); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    decompositionGeneralViewer = new DecompositionGeneralViewer(parent, decompositionModel, false);
    decompositionGeneralViewer.setSourceTreeContentProvider(true);
    setControl(decompositionGeneralViewer.getControl());
  }

  /**
   * @return the controller
   */
  public LCDecompositionController getController() {
    return controller;
  }

  /**
   * @param controller the controller to set
   */
  public void setController(LCDecompositionController controller) {
    this.controller = controller;
  }

}
