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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 */
public class LCDecompositionWizard extends Wizard {

  private DecompositionModel decompositionModel;
  private LCDecompositionWizardPage lcDecompositionWizardPage;
  private LCDecompositionValidateWizardPage lcDecompositionValidateWizardPage;
  private LCDecompositionController controller;

  public LCDecompositionWizard() {
    // Default constructor
  }

  public LCDecompositionWizard(DecompositionModel decompModel) {
    setDecompositionModel(decompModel);
    lcDecompositionWizardPage = new LCDecompositionWizardPage(decompositionModel);
    lcDecompositionValidateWizardPage = new LCDecompositionValidateWizardPage(decompositionModel);

    addPage(lcDecompositionWizardPage);
    addPage(lcDecompositionValidateWizardPage);
    setNeedsProgressMonitor(true);
  }

  public void initComponents() {
    lcDecompositionWizardPage = new LCDecompositionWizardPage(getDecompositionModel());
    lcDecompositionWizardPage.setController(getController());

    lcDecompositionValidateWizardPage = new LCDecompositionValidateWizardPage(getDecompositionModel());
    lcDecompositionValidateWizardPage.setController(getController());

    addPage(lcDecompositionWizardPage);
    addPage(lcDecompositionValidateWizardPage);
    setNeedsProgressMonitor(true);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    return decompositionModel.finishDecomposition();
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#canFinish()
   */
  @Override
  public boolean canFinish() {
    boolean decompositionComplete = controller.isDecompositionComplete();
    boolean userHasDeletedSubComponent = controller.userHasDeletedSubComponent();
    return userHasDeletedSubComponent || decompositionComplete;
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
   * 
   */
  public void trigger() {
    boolean flag = controller.isDecompositionComplete();
    lcDecompositionWizardPage.setPageComplete(flag);
    lcDecompositionValidateWizardPage.setPageComplete(flag);
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
