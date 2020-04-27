/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.PopupMenuLinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.sa.MissionBlankAction;
import org.polarsys.capella.core.explorer.activity.ui.actions.sa.MissionCapabilitiesBlankAction;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class NewMissionCapabilityBlankDiagramAdapter extends PopupMenuLinkAdapter {

	public NewMissionCapabilityBlankDiagramAdapter() {
		super();
	}

	@Override
	protected void fillPopupMenu(IMenuManager menuManager) {
		EObject rootSemanticModel = ActivityExplorerManager.INSTANCE.getRootSemanticModel();
		if(rootSemanticModel instanceof Project){
		  //Add Mission Blank Diagram action.
		  Project capellaProject = (Project) rootSemanticModel;
		  ModelElement modelElement = ModelQueryHelper.getSAMissionPkg(capellaProject);
		  menuManager.add(new MissionBlankAction(modelElement, ActivityExplorerManager.INSTANCE.getSession()));
		  
		  // Add Mission Capabilities Blank diagram action.
		  modelElement = ModelQueryHelper.getSACapabilityPkg((Project) capellaProject);
		  menuManager.add(new MissionCapabilitiesBlankAction(modelElement, ActivityExplorerManager.INSTANCE.getSession()));
		}
	}

  /**
   * @see org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.IRepresentationProvider#getRepresentationNames()
   */
  public Set<String> getRepresentationNames() {
    return new HashSet<String>(Arrays.asList(new String[] { IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME }));
  }
}
