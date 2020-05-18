/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.menu.dynamic;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 */
public abstract class AbstractActionProvider extends CommonActionProvider {
  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);

    ICommonViewerSite commonViewerSite = site.getViewSite();
    if (commonViewerSite instanceof ICommonViewerWorkbenchSite) {
      ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) commonViewerSite;
      IWorkbenchPage page = workbenchSite.getPage();
      ISelectionProvider selectionProvider = workbenchSite.getSelectionProvider();
      Shell shell = workbenchSite.getShell();
      initActions(shell, page, selectionProvider);
    }
  }

  /**
   * Initialization actions
   * @param shell
   * @param page
   * @param selectionProvider
   */
  protected abstract void initActions(Shell shell, IWorkbenchPage page, ISelectionProvider selectionProvider);
}
