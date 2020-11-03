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
package org.polarsys.capella.core.af.integration.ui.listener;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.kitalpha.emde.extension.ModelExtensionOverallListener;

/**
 * 
 */
public class SemanticBrowserUpdater implements ModelExtensionOverallListener {

	public void modelEnabled(Object ctx, String nsURI) {
		refresh();
	}

	public void modelDisabled(Object ctx, String nsURI) {
		refresh();
	}

	private void refresh() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				if (activePage == null)
					return;
				ISemanticBrowserViewPart view = (ISemanticBrowserViewPart) activePage.findView("org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserID");
				if (view != null) {
					refresh(view.getCurrentViewer());
					refresh(view.getReferencedViewer());
					refresh(view.getReferencingViewer());
				}
			}
		});
	}

	private void refresh(TreeViewer viewer) {
		Object[] visibleExpandedElements = viewer.getVisibleExpandedElements();
		viewer.refresh();
		viewer.setExpandedElements(visibleExpandedElements);
	}
}
