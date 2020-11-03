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
package org.polarsys.capella.core.af.integration.services;

import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Service;
import org.polarsys.kitalpha.ad.viewpoint.integration.services.ServiceImplementation;
import org.polarsys.kitalpha.ad.viewpoint.utils.ModelAccessor;

/**
 * 
 */
public class ValidateAllServiceImplementation implements ServiceImplementation {

	public void run(Service action, ModelAccessor properties, Object[] selection) {
		// ensure that the descriptor are loaded
		// ModelValidationService.getInstance().loadXmlConstraintDeclarations();
		//
		// CapellaValidateAction validationAction = new CapellaValidateAction();
		// CommonNavigator packageExplorer = (CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("capella.project.explorer");
		// CommonViewer commonViewer = packageExplorer.getCommonViewer();
		//
		// validationAction.setActiveWorkbenchPart(packageExplorer);
		// validationAction.selectionChanged(new SelectionChangedEvent(commonViewer, new StructuredSelection(selection)));
		// validationAction.run();
	}

  public boolean canRun(Service action, ModelAccessor properties, Object[] selection) {
    return true;
  }
}
