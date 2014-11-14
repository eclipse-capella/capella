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

	public SelectInvokedOperationFilter(TreeAndListViewer viewer_p, ISelectInvokedOperationModel model_p) {
		viewer = viewer_p;
		model = model_p;
	}

  @SuppressWarnings("synthetic-access")
  @Override
	public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
  	if (element_p instanceof AbstractCommunication) {
  		return model.getSelectableElements().contains(element_p);
		} else if (element_p instanceof CapellaElement) {
			return model.getSelectableElementContainers().contains(element_p);
		}
		return true;
	}
}
