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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Create a new Blank Functional Dataflow diagram.
 */
public class NewBlankFunctionalDataflowDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {
	/**
	 * Constructor.
	 * @param rootSemanticModel
	 * @param session
	 */
	public NewBlankFunctionalDataflowDiagramAdapter() {
		super();
	}

	@Override
	public String getRepresentationName() {
		return IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
		return ModelQueryHelper.getRootPhysicalFunction((Project) rootSemanticModel);
	}
}
