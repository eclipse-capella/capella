/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Create a new Operational Interaction Blank diagram.
 */
public class NewOperationalInteractionBlankDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {


	public NewOperationalInteractionBlankDiagramAdapter() {
		super();
	}


	@Override
	public String getRepresentationName() {
		return IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
	}

	@Override
	protected EObject getModelElement(EObject rootSemanticModel) {
		return ModelQueryHelper.getRootOperationalActivity((Project) rootSemanticModel);
	}
}
