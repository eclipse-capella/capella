/*******************************************************************************
 *  Copyright (c) 2007, 2020 LCELB
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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Base class to implement action located in the context menu of a tree viewer
 * based on the {@link CommonNavigator}.
 * 
 */
public abstract class AbstractNavigatorAction extends Action {
	/**
	 * Selection provider.
	 */
	private ISelectionProvider provider;

	/**
	 * Shell.
	 */
	private Shell shell;

	/**
	 * Constructor..
	 * 
	 * @param selectionProvider
	 */
	protected AbstractNavigatorAction(ISelectionProvider selectionProvider) {
		this.provider = selectionProvider;
	}

	/**
	 * Constructor.
	 * 
	 * @param shell
	 * @param selectionProvider
	 */
	protected AbstractNavigatorAction(Shell shell, ISelectionProvider selectionProvider) {
		this(selectionProvider);
		this.shell = shell;
	}

	/**
	 * Get the current selected object
	 * 
	 * @param objectType
	 *            the object type that the selection must be an instance of
	 */
	protected Object getSelection(Class<?> objectType) {
		Object selectedObject = null;
		// Get selected objects if any.
		IStructuredSelection selectedObjects = (IStructuredSelection) provider.getSelection();
		// If not empty, get the first selected object.
		if (!selectedObjects.isEmpty()) {
			Object firstElement = selectedObjects.getFirstElement();
			if (selectedObjects.size() == 1 && objectType.isInstance(firstElement)) {
				selectedObject = firstElement;
			}
		}
		return selectedObject;
	}

	/**
	 * Get the shell
	 * 
	 * @return the shell or null if not set with the constructor.
	 */
	protected Shell getShell() {
		return shell;
	}

	/**
	 * Returns whether this action is compatible with current selection.
	 * 
	 * @return
	 */
	public boolean isSelectionCompatible() {
		boolean isEnabled = false;
		// Check selected element has a type that matches the expected one.
		Object element = getSelection(getModelElementClass());
		if (null != element) {
			setSelectedElement(element);
			isEnabled = true;
		}
		return isEnabled;
	}

	/**
	 * Get the model element class for which this action is enabled for.
	 * 
	 * @return
	 */
	protected abstract Class<?> getModelElementClass();

	/**
	 * Set given object as the selected object.
	 * 
	 * @param object
	 */
	protected abstract void setSelectedElement(Object object);

	/**
	 * Get the shared images.
	 * 
	 * @return
	 */
	protected ISharedImages getSharedImages() {
		return PlatformUI.getWorkbench().getSharedImages();
	}

	/**
	 * Get the selection provider.
	 * 
	 * @return the provider
	 */
	protected ISelectionProvider getSelectionProvider() {
		return provider;
	}
}
