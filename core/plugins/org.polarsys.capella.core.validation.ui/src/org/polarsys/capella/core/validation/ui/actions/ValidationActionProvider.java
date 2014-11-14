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
package org.polarsys.capella.core.validation.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaValidateAction;
import org.polarsys.capella.core.validation.ui.CapellaValidationUIActivator;

/**
 */
public class ValidationActionProvider extends CommonActionProvider {

  /*
   * 
   */
  private ValidateAction _defaultValidationAction;
  /*
   * 
   */
  private List<ValidateAction> _userValidationActions;
  /*
   * 
   */
  private ICommonViewerSite commonViewSite;
  /*
   * 
   */
  private ImageDescriptor imageDescriptor;

  /**
   * Constructs the contribution actions provider.
   */
  public ValidationActionProvider() {
    _userValidationActions = new ArrayList<ValidateAction>();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();

    if (null != _defaultValidationAction) {
      selectionProvider.removeSelectionChangedListener(_defaultValidationAction);
      _defaultValidationAction = null;
    }

    for (ValidateAction action : _userValidationActions) {
      selectionProvider.removeSelectionChangedListener(action);
    }
    _userValidationActions.clear();

    super.dispose();
  }

  @Override
  protected boolean filterAction(org.eclipse.jface.action.IAction action) {
    return false;

  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);

    commonViewSite = site_p.getViewSite();
    if (!(commonViewSite instanceof ICommonViewerWorkbenchSite)) {
      return;
    }

  }

  /**
   * @param isRootAction
   * @param file_p
   * @param selectionProvider_p
   * @param imageDescriptor_p
   * @return
   */
  protected ValidateAction createValidationAction(boolean isRootAction, IFile file_p, ISelectionProvider selectionProvider_p, ImageDescriptor imageDescriptor_p) {
    ValidateAction validationAction = new EPFValidationAction(isRootAction, file_p);

    validationAction.setImageDescriptor(imageDescriptor_p);

    validationAction.setActiveWorkbenchPart(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());
    selectionProvider_p.addSelectionChangedListener(validationAction);
    // Set the current selection otherwise at first run, selection is lost.
    ISelection selection = selectionProvider_p.getSelection();
    if (!selection.isEmpty()) {
      validationAction.selectionChanged(new SelectionChangedEvent(selectionProvider_p, selection));
    }

    return validationAction;
  }

  /**
   * @return
   */
  public ValidateAction createDefaultValidation() {

    imageDescriptor = CapellaValidationUIActivator.getDefault().getImageDescriptor(CapellaValidationUIActivator.IMG_ENABLED_VALIDATE);

    ISelectionProvider selectionProvider = commonViewSite.getSelectionProvider();
    CapellaValidateAction validationAction = new CapellaValidateAction();

    validationAction.setImageDescriptor(imageDescriptor);

    validationAction.setText("Validate Model"); //$NON-NLS-1$

    validationAction.setActiveWorkbenchPart(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());
    selectionProvider.addSelectionChangedListener(validationAction);
    // Set the current selection otherwise at first run, selection is lost.
    ISelection selection = selectionProvider.getSelection();
    if (!selection.isEmpty()) {
      validationAction.selectionChanged(new SelectionChangedEvent(selectionProvider, selection));
    }

    return validationAction;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    initActions();
    if (_userValidationActions.size() > 0) {
      IMenuManager menuManager = new MenuManager("Validate Model", imageDescriptor, "ID"); //$NON-NLS-1$ //$NON-NLS-2$
      menu_p.insertBefore("group.validation", menuManager); //$NON-NLS-1$
      menuManager.add(_defaultValidationAction);
      menuManager.add(new Separator());
      for (ValidateAction action : _userValidationActions) {
        menuManager.add(action);
      }
    } else {
      imageDescriptor = CapellaValidationUIActivator.getDefault().getImageDescriptor(CapellaValidationUIActivator.IMG_ENABLED_VALIDATE);
      _userValidationActions = new ArrayList<ValidateAction>();
      menu_p.prependToGroup("group.validation", _defaultValidationAction); //$NON-NLS-1$

    }
  }

  /**
   * 
   */
  private void initActions() {
    ISelectionProvider selectionProvider = commonViewSite.getSelectionProvider();
    imageDescriptor = CapellaValidationUIActivator.getDefault().getImageDescriptor(CapellaValidationUIActivator.IMG_ENABLED_VALIDATE);
    _userValidationActions = new ArrayList<ValidateAction>();
    _defaultValidationAction = createDefaultValidation();// ValidationAction(false, null, selectionProvider, imageDescriptor);
    for (IFile file : PreferencesHelper.retrieveUserDefinedPreferenceFiles(selectionProvider, EPFValidationAction.EPF_EXTNAME)) {
      _userValidationActions.add(createValidationAction(false, file, selectionProvider, imageDescriptor));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    // Do nothing.
  }

}
