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
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 */
@Deprecated
public class SelectInSemanticBrowserAction extends Action {

  /** */
  private TreeSelection selection;

  /**
   * 
   */
  public SelectInSemanticBrowserAction(ISelection selection) {
    super();
    this.selection = (TreeSelection) selection;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      // Get the Semantic Browser.
      SemanticBrowserView smView = (SemanticBrowserView) activePage.findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
      if (null == smView) {
        // Show it if not found.
        smView = (SemanticBrowserView) activePage.showView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
      }
      activePage.activate(smView);
      smView.setInput(selection.getFirstElement());
    } catch (PartInitException exception) {
      // Catch exception silently,
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    return CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Show in Semantic Browser"; //$NON-NLS-1$
  }
}
