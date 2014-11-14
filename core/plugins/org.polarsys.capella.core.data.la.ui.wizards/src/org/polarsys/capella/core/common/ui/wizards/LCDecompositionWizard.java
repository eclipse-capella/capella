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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 */
public class LCDecompositionWizard extends Wizard {

  private DecompositionModel _decompositionModel;
  private LCDecompositionWizardPage _lcDecompositionWizardPage;
  private LCDecompositionValidateWizardPage _lcDecompositionValidateWizardPage;
  private LCDecompositionController _controller;

  public LCDecompositionWizard() {
    // Default constructor
  }

  public LCDecompositionWizard(DecompositionModel decompModel_p) {
    setDecompositionModel(decompModel_p);
    _lcDecompositionWizardPage = new LCDecompositionWizardPage(_decompositionModel);
    _lcDecompositionValidateWizardPage = new LCDecompositionValidateWizardPage(_decompositionModel);

    addPage(_lcDecompositionWizardPage);
    addPage(_lcDecompositionValidateWizardPage);
    setNeedsProgressMonitor(true);
  }

  public void initComponents() {
    _lcDecompositionWizardPage = new LCDecompositionWizardPage(getDecompositionModel());
    _lcDecompositionWizardPage.setController(getController());

    _lcDecompositionValidateWizardPage = new LCDecompositionValidateWizardPage(getDecompositionModel());
    _lcDecompositionValidateWizardPage.setController(getController());

    addPage(_lcDecompositionWizardPage);
    addPage(_lcDecompositionValidateWizardPage);
    setNeedsProgressMonitor(true);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    return _decompositionModel.finishDecomposition();
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#canFinish()
   */
  @Override
  public boolean canFinish() {
    boolean decompositionComplete = _controller.isDecompositionComplete();
    boolean userHasDeletedSubComponent = _controller.userHasDeletedSubComponent();
    return userHasDeletedSubComponent || decompositionComplete;
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
   * 
   */
  public void trigger() {
    boolean flag = _controller.isDecompositionComplete();
    _lcDecompositionWizardPage.setPageComplete(flag);
    _lcDecompositionValidateWizardPage.setPageComplete(flag);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#getDefaultPageImage()
   */
  @Override
  public Image getDefaultPageImage() {
    ImageDescriptor pngImageDescriptor = CapellaUIResourcesPlugin.getDefault().getPNGImage(LaPackage.Literals.LOGICAL_COMPONENT);
    Image image = (null != pngImageDescriptor) ? pngImageDescriptor.createImage() : super.getDefaultPageImage();
    return image;
  }
}
