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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.epbs;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.refinement.commands.ScenarioRefinementCommand;

/**
 * Execute the Logical Scenario Refinement on the Scenarios of the Capability.
 */
public class ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter extends AbstractHyperlinkAdapter {
	/**
	 * Constructor.
	 */
	public ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter() {
		super((Project) ActivityExplorerManager.INSTANCE.getRootSemanticModel());
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
		TransactionHelper.getExecutionManager(rootSemanticModel).execute(new ScenarioRefinementCommand(getModelElement(rootSemanticModel), new NullProgressMonitor()));
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
		return ModelQueryHelper.getPACapabilityRealizationPkg((Project) rootSemanticModel);
	}
}
