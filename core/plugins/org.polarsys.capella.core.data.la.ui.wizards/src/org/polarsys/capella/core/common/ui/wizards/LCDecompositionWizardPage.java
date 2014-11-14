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

  private DecompositionModel _decompositionModel;
  private DecompositionGeneralViewer _decompositionGeneralViewer;
  private LCDecompositionController _controller;

  public LCDecompositionWizardPage(DecompositionModel decompositionModel_p) {
    this("Capella Logical Component Decomposition"); //$NON-NLS-1$
    _decompositionModel = decompositionModel_p;
  }

  /**
   * @param pageName_p
   */
  public LCDecompositionWizardPage(String pageName_p) {
    super(pageName_p);
    setTitle(pageName_p);
    setMessage("This editor displays the Capella Logical Component Decomposition"); //$NON-NLS-1$
    setPageComplete(false);

  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    _decompositionGeneralViewer =
        new DecompositionGeneralViewer(parent_p, _decompositionModel, true, getSynchronizationModeSelected(_decompositionModel), false);
    _decompositionGeneralViewer.setSourceTreeContentProvider(false);
    _decompositionGeneralViewer.addTreeTipListeners();
    _decompositionGeneralViewer.setDialogPage(this);

    setControl(_decompositionGeneralViewer.getControl());
  }

  /**
   * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
   */
  @Override
  public IWizardPage getNextPage() {
    LCDecompositionValidateWizardPage page = (LCDecompositionValidateWizardPage) super.getNextPage();
    _decompositionModel.refreshStatusForSynthesisCheck();
    page.getDecompositionGeneralViewer().refreshItems(null);
    page.getDecompositionGeneralViewer().addTreeSelectionListener();
    return page;
  }

  /**
   * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
   */
  @Override
  public boolean canFlipToNextPage() {
    boolean flag = _controller.canFlipToNextPage();
    return flag;
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

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return _decompositionModel;
  }

  /**
   * @param decompositionModel_p the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel_p) {
    _decompositionModel = decompositionModel_p;
  }

  /**
   * @param decompositionModel_p
   * @return
   */
  private boolean getSynchronizationModeSelected(DecompositionModel decompositionModel_p) {
    boolean result = true;
    if (decompositionModel_p != null) {
      DecompositionComponent component = decompositionModel_p.getSourceComponent();
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
