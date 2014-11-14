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
package org.polarsys.capella.core.common.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionGeneralViewer;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;

/**
 */
public class LCDecompositionValidateWizardPage extends WizardPage {
  private DecompositionModel _decompositionModel;
  private DecompositionGeneralViewer _decompositionGeneralViewer;
  private LCDecompositionController _controller;

  /**
   * @return the decompositionGeneralViewer
   */
  public DecompositionGeneralViewer getDecompositionGeneralViewer() {
    return _decompositionGeneralViewer;
  }

  /**
   * @param decompositionGeneralViewer_p the decompositionGeneralViewer to set
   */
  public void setDecompositionGeneralViewer(DecompositionGeneralViewer decompositionGeneralViewer_p) {
    _decompositionGeneralViewer = decompositionGeneralViewer_p;
  }

  public LCDecompositionValidateWizardPage(DecompositionModel decompositionModel_p) {
    this("Capella Logical Component Decomposition"); //$NON-NLS-1$
    _decompositionModel = decompositionModel_p;
    setPageComplete(false);
  }

  /**
   * @param pageName_p
   */
  protected LCDecompositionValidateWizardPage(String pageName_p) {
    super(pageName_p);
    setTitle(pageName_p);
    setMessage("This editor displays the Capella Logical Component Decomposition - Synthesis Check"); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    _decompositionGeneralViewer = new DecompositionGeneralViewer(parent_p, _decompositionModel, false);
    _decompositionGeneralViewer.setSourceTreeContentProvider(true);
    setControl(_decompositionGeneralViewer.getControl());
  }

  /**
   * @return the controller
   */
  public LCDecompositionController getController() {
    return _controller;
  }

  /**
   * @param controller_p the controller to set
   */
  public void setController(LCDecompositionController controller_p) {
    _controller = controller_p;
  }

}
