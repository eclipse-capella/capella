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

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.ui.actions.ActorTransitionAction;

/**
 * Perform an automated transition of Logical Actors.
 */
public class PerformAutomatedTransitionOfLogicalActorsAdapter extends AbstractHyperlinkAdapter {
	/**
	 * Constructor.
	 * @param rootSemanticModel
	 * @param session
	 */
	public PerformAutomatedTransitionOfLogicalActorsAdapter() {
		super((Project) ActivityExplorerManager.INSTANCE.getRootSemanticModel());
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
		ActorTransitionAction action = new ActorTransitionAction();
		action.selectionChanged(TransitionAction.DEFAULT_ACTION, new StructuredSelection(getModelElement(rootSemanticModel)));
		action.run(TransitionAction.DEFAULT_ACTION);
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
		return ModelQueryHelper.getLogicalActorPkg((Project) rootSemanticModel);
	}
}
