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

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.ui.actions.CapabilityTransitionAction;

/**
 * Perform an automated transition of Capabilities from source architecture to target architecture
 */
public class PerformAutomatedCapabilitiesTransitionAdapter extends AbstractHyperlinkAdapter {

	protected BlockArchitectureExt.Type blockType;

	/**
	 * Constructor.
	 * @param capellaproject
	 * @param sourceArchitecture
	 * @param session
	 */
	public PerformAutomatedCapabilitiesTransitionAdapter() {
	  super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
		EObject rootSemanticModel = ActivityExplorerManager.INSTANCE.getRootSemanticModel();
		if(rootSemanticModel instanceof Project){
		  BlockArchitecture sourceArchitecture = ModelQueryHelper.getSystemAnalysis((Project) rootSemanticModel);
		  blockType = BlockArchitectureExt.getBlockArchitectureType(sourceArchitecture);
		}
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
	  ModelElement modelElement = getModelElement(rootSemanticModel);
	  if(modelElement != null){
	    CapabilityTransitionAction action = new CapabilityTransitionAction();
	    action.selectionChanged(TransitionAction.DEFAULT_ACTION, new StructuredSelection(modelElement));
	    action.run(TransitionAction.DEFAULT_ACTION);
	  }
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
	  if(blockType != null){
	    return BlockArchitectureExt.getBlockArchitecture(blockType, (Project) rootSemanticModel);
	  }
	  return null;
	}
}
