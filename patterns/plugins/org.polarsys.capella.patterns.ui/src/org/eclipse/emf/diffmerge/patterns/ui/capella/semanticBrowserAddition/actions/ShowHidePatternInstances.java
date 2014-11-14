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
package org.eclipse.emf.diffmerge.patterns.ui.capella.semanticBrowserAddition.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;

public class ShowHidePatternInstances implements IViewActionDelegate {

	protected ISemanticBrowserViewPart view;
	protected boolean active = true;
		
	@Override
	public void run(IAction action) {
    view.getModel().setShowPatterns(action.isChecked());
    Object input = view.getCurrentViewer().getInput();
    view.setInputOnViewers(input);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// nothing to do
	}

	@Override
	public void init(IViewPart view_p) {
		view = (ISemanticBrowserViewPart) view_p;
	}

}
