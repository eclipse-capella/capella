/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.menu.dynamic;

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
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);

    ICommonViewerSite commonViewerSite = site_p.getViewSite();
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
   * @param shell_p
   * @param page_p
   * @param selectionProvider_p
   */
  protected abstract void initActions(Shell shell_p, IWorkbenchPage page_p, ISelectionProvider selectionProvider_p);
}
