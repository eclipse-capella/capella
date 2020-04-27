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

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionGeneralViewer;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class LCDecompositionWizardPage extends WizardPage {

  private DecompositionModel decompositionModel;
  private DecompositionGeneralViewer decompositionGeneralViewer;
  private LCDecompositionController controller;

  public LCDecompositionWizardPage(DecompositionModel decompositionModel) {
    this("Capella Logical Component Decomposition"); //$NON-NLS-1$
    this.decompositionModel = decompositionModel;
  }

  /**
   * @param pageName
   */
  public LCDecompositionWizardPage(String pageName) {
    super(pageName);
    setTitle(pageName);
    setMessage("This editor displays the Capella Logical Component Decomposition"); //$NON-NLS-1$
    setPageComplete(false);

  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    decompositionGeneralViewer =
        new DecompositionGeneralViewer(parent, decompositionModel, true, getSynchronizationModeSelected(decompositionModel), false);
    decompositionGeneralViewer.setSourceTreeContentProvider(false);
    decompositionGeneralViewer.addTreeTipListeners();
    decompositionGeneralViewer.setDialogPage(this);

    setControl(decompositionGeneralViewer.getControl());
  }

  /**
   * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
   */
  @Override
  public IWizardPage getNextPage() {
    LCDecompositionValidateWizardPage page = (LCDecompositionValidateWizardPage) super.getNextPage();
    decompositionModel.refreshStatusForSynthesisCheck();
    page.getDecompositionGeneralViewer().refreshItems(null);
    page.getDecompositionGeneralViewer().addTreeSelectionListener();
    return page;
  }

  /**
   * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
   */
  @Override
  public boolean canFlipToNextPage() {
    boolean flag = controller.canFlipToNextPage();
    return flag;
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

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return decompositionModel;
  }

  /**
   * @param decompositionModel the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel) {
    this.decompositionModel = decompositionModel;
  }

  /**
   * @param decompositionModel
   * @return
   */
  private boolean getSynchronizationModeSelected(DecompositionModel decompositionModel) {
    boolean result = true;
    if (decompositionModel != null) {
      DecompositionComponent component = decompositionModel.getSourceComponent();
      if (component != null) {
        Object value = component.getValue();
        if (value != null && value instanceof ModelElement) {
          ModelElement sourceElement = (ModelElement) value;
          result = TriStateBoolean.True.equals(CapellaProjectHelper.isSingletonComponentsDriven(sourceElement));
        }
      }
    }
    return result;
  }
}
