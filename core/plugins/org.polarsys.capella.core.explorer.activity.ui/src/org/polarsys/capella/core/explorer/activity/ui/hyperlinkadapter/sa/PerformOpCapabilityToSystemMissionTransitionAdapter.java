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

import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelSelectionHelper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.ui.actions.OC2SMTransitionAction;

/**
 * Perform an automated transition of Operational Capability to a System Mission.
 */
public class PerformOpCapabilityToSystemMissionTransitionAdapter extends AbstractCapellaHyperlinkAdapter {

	public PerformOpCapabilityToSystemMissionTransitionAdapter() {
		super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
	}

	@Override
	protected ModelElement getModelElement(EObject rootSemanticModel) {
	  if(rootSemanticModel instanceof Project){
	    return ModelQueryHelper.getOperationalAnalysis((Project) rootSemanticModel);
	  }
	  return null;
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
	  if(rootSemanticModel instanceof Project){
	    List<OperationalCapability> operationalCapabilities = ModelSelectionHelper.selectOperationalCapabilities((Project) rootSemanticModel);
	    OC2SMTransitionAction action = new OC2SMTransitionAction();
	    action.selectionChanged(TransitionAction.DEFAULT_ACTION, new StructuredSelection(operationalCapabilities));
	    action.run(TransitionAction.DEFAULT_ACTION);
	  }
	}
}
