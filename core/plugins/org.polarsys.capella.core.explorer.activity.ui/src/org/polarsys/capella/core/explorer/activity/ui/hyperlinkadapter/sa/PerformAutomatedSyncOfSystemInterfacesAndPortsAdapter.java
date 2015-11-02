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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.refinement.commands.GenerateInterfaceDelegationsCommand;

/**
 * Perform an automated synchronization of System interfaces and Ports.
 */
public class PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter extends AbstractHyperlinkAdapter {
	
	
	public PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter() {
		super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
	  if(rootSemanticModel instanceof Project){
	    return ModelQueryHelper.getSystem((Project) rootSemanticModel);
	  }
	  return null;
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
		ModelElement modelElement = getModelElement(rootSemanticModel);
		if(modelElement !=null){
		  TransactionHelper.getExecutionManager((Project) rootSemanticModel).execute(new GenerateInterfaceDelegationsCommand(modelElement));
		}
	}
}
