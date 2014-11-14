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
package org.polarsys.capella.core.af.integration.listener;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.kitalpha.emde.extension.ModelExtensionListener;

/**
 * 
 */
public class SemanticBrowserUpdater implements ModelExtensionListener {

	public void modelEnabled(String nsURI) {
		refresh();
	}

	public void modelDisabled(String nsURI) {
		refresh();
	}

	private void refresh() {
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

	private void refresh(TreeViewer viewer) {
		Object[] visibleExpandedElements = viewer.getVisibleExpandedElements();
		viewer.refresh();
		viewer.setExpandedElements(visibleExpandedElements);
	}
}
