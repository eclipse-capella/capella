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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * New Interface scenario for LA.
 */
public class NewScenarioDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {
	/**
	 * Constructor.
	 * @param capellaproject
	 * @param session
	 */
	public NewScenarioDiagramAdapter() {
		super();
	}

	@Override
	public String getRepresentationName() {
		return IDiagramNameConstants.INTERFACE_SCENARIO_DIAGRAM_NAME;
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
		LogicalArchitecture logicalArchitecture = ModelQueryHelper.getLogicalArchitecture((Project) rootSemanticModel);
		return ModelCreationHelper.selectCapabilityAndCreateScenario((Project) rootSemanticModel, logicalArchitecture, ScenarioKind.INTERFACE);
	}
}
