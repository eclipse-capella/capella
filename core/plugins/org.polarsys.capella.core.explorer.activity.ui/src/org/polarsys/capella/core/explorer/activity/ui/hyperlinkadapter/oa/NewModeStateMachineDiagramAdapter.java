/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Describe the States and Modes of the system with a new State Machine
 */
public class NewModeStateMachineDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {

	public NewModeStateMachineDiagramAdapter() {
		super();
	}

	@Override
	public String getRepresentationName() {
		return IDiagramNameConstants.MODE_STATE_DIAGRAM_NAME;
	}

	@Override
	protected EObject getModelElement(EObject rootSemanticModel) {
		return ModelCreationHelper.selectOperationalEntityAndCreateStateMachineRegion((Project)rootSemanticModel);
	}

}
