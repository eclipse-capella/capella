/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.viewAndController;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.ISelectInvokedOperationModel;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;

public class SelectInvokedOperationFilter extends ViewerFilter {

	protected TreeAndListViewer viewer;
	protected ISelectInvokedOperationModel model;

	public SelectInvokedOperationFilter(TreeAndListViewer viewer, ISelectInvokedOperationModel model) {
		this.viewer = viewer;
		this.model = model;
	}

  @SuppressWarnings("synthetic-access")
  @Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
  	if (element instanceof AbstractCommunication) {
  		return model.getSelectableElements().contains(element);
		} else if (element instanceof CapellaElement) {
			return model.getSelectableElementContainers().contains(element);
		}
		return true;
	}
}
