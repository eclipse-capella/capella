/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.patterns.ui.semanticBrowserAddition.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;


/**
 * An action delegate that allows hiding/showing related pattern instances in the Semantic Browser view.
 */
public class ShowHidePatternInstances implements IViewActionDelegate {

	/** The Semantic Browser view */
  protected ISemanticBrowserViewPart view;

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
    view.getModel().setShowPatterns(action.isChecked());
    Object input = view.getCurrentViewer().getInput();
    view.setInputOnViewers(input);
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// nothing to do
	}

	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(@SuppressWarnings("hiding") IViewPart view) {
		this.view = (ISemanticBrowserViewPart) view;
	}

}
