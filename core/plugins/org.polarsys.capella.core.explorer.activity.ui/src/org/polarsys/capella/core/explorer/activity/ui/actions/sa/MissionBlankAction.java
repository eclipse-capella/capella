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
package org.polarsys.capella.core.explorer.activity.ui.actions.sa;

import org.eclipse.amalgam.explorer.activity.ui.IImageKeys;
import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.AbstractCapellaAction;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;


public class MissionBlankAction extends AbstractCapellaAction {
	/**
	 * Constructor.
	 * @param modelElement
	 * @param session
	 */
	public MissionBlankAction(ModelElement modelElement, Session session) {
		super(Messages.MissionBlankAction_Title, IImageKeys.IMAGE_DESCRIPTOR_NEW_DIAGRAM, modelElement, session);
	}

	@Override
	protected void doRun(ModelElement modelElement, Session session) {
		new AbstractNewDiagramHyperlinkAdapter(modelElement) {

			@Override
			public String getRepresentationName() {
				return IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME;
			}

			@Override
			protected ModelElement getModelElement(EObject rootSemanticModel) {
				return ModelQueryHelper.getSAMissionPkg((Project) rootSemanticModel);
			}
		}.linkActivated(null);
	}
}
