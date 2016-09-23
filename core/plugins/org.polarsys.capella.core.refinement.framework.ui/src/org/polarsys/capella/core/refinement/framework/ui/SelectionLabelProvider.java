/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.framework.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;

/**
 */
public class SelectionLabelProvider extends CapellaElementLabelProvider {

	/**
	 * Constructor
	 */
	public SelectionLabelProvider() {
		super();
	}

	/**
	 * @see ILabelProvider#getImage(Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof SelectionItemNode) {
			SelectionItemNode elt = (SelectionItemNode) element;
			if (elt.getData() instanceof EObject) {
				EObject data = (EObject) elt.getData();
				return super.getImage(data);
			}
			else if (elt.getSrc() instanceof EObject) {
				EObject src = ((EObject) elt.getSrc());
				return super.getImage(src);
			}
		}

		return super.getImage(element);
	}

	/**
	 * @see ILabelProvider#getText(Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof SelectionItemNode) {
			return ((SelectionItemNode) element).getName();
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * @param element
	 * @return
	 */
	public String getExtendedText(Object element) {
		if (element instanceof SelectionItemNode) {
			return ((SelectionItemNode) element).getFullName();
		} else if (element instanceof TreeSelection) {
			return getExtendedText(((TreeSelection) element).getFirstElement());
		}
		return ""; //$NON-NLS-1$
	}
}
