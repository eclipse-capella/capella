/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.semantic.browser.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

public class RefreshAction extends Action {

  protected ISemanticBrowserViewPart semanticBrowserViewPart;

  public RefreshAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    this(semanticBrowserViewPart, IAction.AS_PUSH_BUTTON);
  }
  
  public RefreshAction(ISemanticBrowserViewPart semanticBrowserViewPart, int type) {
    super(null, type);
    this.semanticBrowserViewPart = semanticBrowserViewPart;
    setImageDescriptor(CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_REFRESH));
  }

  @Override
  public void run() {
    doRun();
  }

  protected void doRun() {
    // Get the current selection.
    IWorkbenchPage activePage = semanticBrowserViewPart.getSite().getPage();
    // Get the active part to get something selectable.
    IWorkbenchPart activePart = activePage.getActivePart();
    IWorkbenchPart part = null;
    ISelection selection = null;
    if ((null != activePart) && (semanticBrowserViewPart != activePart)) {
      // Handle selection at view creation time.
      ISelectionProvider selectionProvider = activePart.getSite().getSelectionProvider();
      if (null != selectionProvider) {
        selection = selectionProvider.getSelection();
        if (isSomethingSelectable(selection)) {
          part = activePart;
        }
      }
    } else {
      IViewPart capellaExplorer = activePage.findView(CapellaCommonNavigator.ID);
      if (null != capellaExplorer) {
        // Capella explorer is displayed.
        selection = getSelection(capellaExplorer);
        if (isSomethingSelectable(selection)) {
          part = capellaExplorer;
        }
      }
      if (null == part) {
        // Try to get a selection from active editor.
        IEditorPart activeEditor = activePage.getActiveEditor();
        if (null != activeEditor) {
          selection = getSelection(activeEditor);
          if (isSomethingSelectable(selection)) {
            part = activeEditor;
          }
        }
      }
    }
    if (null != part) {
      // Something to select.
      ((SemanticBrowserView) semanticBrowserViewPart).getSelectionListener().selectionChanged(part, selection);
    }
  }

  private ISelection getSelection(IWorkbenchPart part) {
    return part.getSite().getSelectionProvider().getSelection();
  }

  private boolean isSomethingSelectable(ISelection selection) {
    return (null != selection) && !selection.isEmpty();
  }
}
