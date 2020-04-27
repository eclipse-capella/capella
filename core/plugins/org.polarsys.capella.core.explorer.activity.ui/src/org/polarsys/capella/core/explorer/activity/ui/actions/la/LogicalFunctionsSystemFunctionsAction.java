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
package org.polarsys.capella.core.explorer.activity.ui.actions.la;

import org.eclipse.amalgam.explorer.activity.ui.IImageKeys;
import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.AbstractCapellaAction;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;


public class LogicalFunctionsSystemFunctionsAction extends AbstractCapellaAction {
	/**
	 * Constructor.
	 * @param modelElement
	 * @param session
	 */
	public LogicalFunctionsSystemFunctionsAction(ModelElement modelElement, Session session) {
		super(Messages.LogicalFunctionsSystemFunctionsAction_Title, IImageKeys.IMAGE_DESCRIPTOR_NEW_TABLE, modelElement, session);
	}

	@Override
	protected void doRun(ModelElement modelElement, Session session) {
		new AbstractNewDiagramHyperlinkAdapter(modelElement) {

			@Override
			public String getRepresentationName() {
				return IDiagramNameConstants.LOGICAL_FUNCTIONS_SYSTEM_FUNCTIONS_DIAGRAM_NAME;
			}

			@Override
			protected ModelElement getModelElement(EObject rootSemanticModel) {
				return ModelQueryHelper.getLogicalArchitecture((Project) rootSemanticModel);
			}
		}.linkActivated(null);
	}
}
