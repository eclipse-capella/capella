/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.framework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;

/**
 */
public class SelectionWizard extends Wizard {

  private String _nameLabel;
  private boolean _isMultipleSelection = false;
  private boolean _autoSelectChild = false;
  private boolean _showNameTextField = false;
  private SelectionItemNode _root = null;
  private String _pageTitle;
  private String _pageDescription;
  private SelectionPage _selectionPage = null;
  private boolean _selectAllByDefault = false;
  private boolean _isAmbiguityResolutionPage = false;
  private String _nameValue = null;
  private List<SelectionItemNode> _finalSelection = null;
  private List<IValidator> _validators = null;

  /**
   * Constructor
   * 
   * @param root
   * @param wizardTitle
   * @param pageTitle
   * @param pageDescription
   * @param isMultipleSelection
   * @param autoSelectChild
   * @param showNameTextField
   * @param nameLabel
   */
  public SelectionWizard(SelectionItemNode root, String wizardTitle, String pageTitle, String pageDescription,
      boolean isMultipleSelection, boolean autoSelectChild, boolean showNameTextField, String nameLabel,
      boolean isAmbiguityResolutionPage) {
    super();
    _root = root;
    _nameLabel = nameLabel;
    _isMultipleSelection = isMultipleSelection;
    _autoSelectChild = autoSelectChild;
    _pageTitle = pageTitle;
    _pageDescription = pageDescription;
    _showNameTextField = showNameTextField;
    _isAmbiguityResolutionPage = isAmbiguityResolutionPage;
    setWindowTitle(wizardTitle);
  }

  /**
   * Constructor
   * 
   * @param root
   * @param wizardTitle
   * @param pageTitle
   * @param pageDescription
   * @param isMultipleSelection
   * @param autoSelectChild
   */
  public SelectionWizard(SelectionItemNode root, String wizardTitle, String pageTitle, String pageDescription,
      boolean isMultipleSelection, boolean autoSelectChild, boolean isAmbiguityResolutionPage) {
    this(root, wizardTitle, pageTitle, pageDescription, isMultipleSelection, autoSelectChild, false,
        "", isAmbiguityResolutionPage); //$NON-NLS-1$
  }

  /**
   * Constructor
   * 
   * @param root
   * @param wizardTitle
   * @param pageTitle
   * @param pageDescription
   * @param isMultipleSelection
   */
  public SelectionWizard(SelectionItemNode root, String wizardTitle, String pageTitle, String pageDescription,
      boolean isMultipleSelection, boolean isAmbiguityResolutionPage) {
    this(root, wizardTitle, pageTitle, pageDescription, isMultipleSelection, false, isAmbiguityResolutionPage);
  }

  /**
   * Constructor
   * 
   * @param root
   * @param wizardTitle
   * @param pageTitle
   * @param pageDescription
   */
  public SelectionWizard(SelectionItemNode root, String wizardTitle, String pageTitle, String pageDescription) {
    this(root, wizardTitle, pageTitle, pageDescription, false, false);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#getDefaultPageImage()
   * @return Image
   */
  @Override
  public Image getDefaultPageImage() {
    Image image = RefinementUIPlugin.getDefault().getImage(InteractionPackage.Literals.SCENARIO.getName());
    if (null == image) {
      image = super.getDefaultPageImage();
    }
    return image;
  }

  /**
   * @param select
   */
  public void selectAllByDefault(boolean select) {
    _selectAllByDefault = select;
  }

  /**
   * 
   */
  @Override
  public void addPages() {
    _selectionPage = new SelectionPage(_root, _pageTitle, _pageDescription, _isMultipleSelection, _autoSelectChild,
        _selectAllByDefault, _showNameTextField, _nameLabel, _isAmbiguityResolutionPage);
    addPage(_selectionPage);
  }

  /**
   * @return boolean
   */
  @Override
  public boolean performFinish() {
    _finalSelection = _selectionPage.getSelection();
    _nameValue = _selectionPage.getNameValue();
    return true;
  }

  /**
   * Gets the selected element
   * 
   * @return the selected element
   */
  public SelectionItemNode getSelection() {
    if ((!_isMultipleSelection) && (_finalSelection.size() == 1)) {
      return _finalSelection.get(0);
    }
    return null;
  }

  /**
   * Gets the selected elements
   * 
   * @return the list of selected elements
   */
  public List<SelectionItemNode> getSelectionList() {
    return _finalSelection;
  }

  /**
   * Gets the name value
   * 
   * @return the value of the name text field
   */
  public String getNameValue() {
    return _nameValue;
  }

  /**
   * Opens the wizard dialog
   * 
   * @return the dialog returned value
   */
  public int open() {
    SelectionWizardRunnable selectionWizardRunnable = new SelectionWizardRunnable(this);
    PlatformUI.getWorkbench().getDisplay().syncExec(selectionWizardRunnable);
    return selectionWizardRunnable.getRes();
  }

  /**
   * @return validators
   */
  public List<IValidator> getValidators() {
    if (_validators == null) {
      _validators = new ArrayList<IValidator>();
    }
    return _validators;
  }

  /**
   * @param validator
   */
  public void addValidator(IValidator validator) {
    if (_validators == null) {
      _validators = new ArrayList<IValidator>();
    }
    _validators.add(validator);
  }

}

class SelectionWizardRunnable implements Runnable {
  private int res;

  public int getRes() {
    return res;
  }

  private SelectionWizard selectionWizard;

  public SelectionWizardRunnable(SelectionWizard selectionWizard) {
    this.selectionWizard = selectionWizard;
  }

  public void run() {
    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    CapellaWizardDialog dlg = new CapellaWizardDialog(shell, selectionWizard);
    dlg.setShellStyle((dlg.getShellStyle() | SWT.ON_TOP) - SWT.MAX);
    res = dlg.open();
  }
}
