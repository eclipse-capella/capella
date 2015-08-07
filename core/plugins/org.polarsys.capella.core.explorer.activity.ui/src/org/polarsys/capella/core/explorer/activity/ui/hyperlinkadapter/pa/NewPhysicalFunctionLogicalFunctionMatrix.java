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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.PopupMenuLinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.jface.action.IMenuManager;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.pa.PhysicalFunctionsLogicalFunctionsAction;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

public class NewPhysicalFunctionLogicalFunctionMatrix extends PopupMenuLinkAdapter {

	public NewPhysicalFunctionLogicalFunctionMatrix() {
		super();
	}

	@Override
	protected void fillPopupMenu(IMenuManager menuManager) {
		Project capellaProject = (Project)ActivityExplorerManager.INSTANCE.getRootSemanticModel();
		ModelElement modelElement = ModelQueryHelper.getPhysicalArchitecture(capellaProject);
		menuManager.add(new PhysicalFunctionsLogicalFunctionsAction(modelElement, ActivityExplorerManager.INSTANCE.getSession()));
	}

}
