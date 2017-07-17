/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.af.integration.ui.listener;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.polarsys.kitalpha.emde.extension.ModelExtensionOverallListener;

/**
 * 
 */
public class CapellaExplorerUpdater implements ModelExtensionOverallListener {

    @Override
    public void modelDisabled(Object ctx, String nsURI) {
        refresh();
    }

    @Override
    public void modelEnabled(Object ctx, String nsURI) {
    	refresh();
    }
    
    private void refresh() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				doRefresh();
			}
		});
    }
	private void doRefresh() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow == null)
			return;
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		if (activePage == null)
			return;
		CommonNavigator view = (CommonNavigator) activePage.findView("capella.project.explorer");
		if (view != null && view.getCommonViewer() != null)
			view.getCommonViewer().refresh();
	}
}
