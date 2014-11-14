/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;

/**
 * 
 *
 */
public class OpenRepresentationActionProvider extends CommonActionProvider {

	/**
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager menu_p) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof EObject) {
			EObject firstSelectedEObject = (EObject) firstElement;
			Session currentSession = SessionManager.INSTANCE.getSession(firstSelectedEObject);
			if (null != currentSession) {
				Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
				Collection<RepresentationDescription> descriptions =
						DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, firstSelectedEObject);

				if (!descriptions.isEmpty()) {
					// Creates the "New Diagram / Table" menu.
					MenuManager openDiagramMenu = new MenuManager(Messages.OpenRepresentationActionProvider_OpenRepresentationAction_Title, "capella.project.diagrams.menu"); //$NON-NLS-1$

					for (RepresentationDescription description : descriptions) {
						// Computes the "Open Diagram / Table..." menu content according to the current selection.
						if(DialectManager.INSTANCE.getRepresentations(description, currentSession)!= null){
							Collection<DRepresentation> rep = DialectManager.INSTANCE.getRepresentations(description, currentSession);
							Collection<DRepresentation> ownedRep = DialectManager.INSTANCE.getRepresentations(firstSelectedEObject, currentSession);
							rep.retainAll(ownedRep);
							for(DRepresentation drep : rep){
								OpenRepresentationsAction ora = new OpenRepresentationsAction(description, drep);
								openDiagramMenu.add(ora);
							}
						}
					}

					//create scenarios from capabilities
					if (firstSelectedEObject instanceof AbstractCapability) {
						for (Viewpoint vp : selectedViewpoints) {
							for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
								if (representationDescription instanceof SequenceDiagramDescription) {						
									SequenceDiagramDescription sdd = (SequenceDiagramDescription) representationDescription;
									if(DialectManager.INSTANCE.getRepresentations(sdd, currentSession)!= null){										
										Collection<DRepresentation> rep = DialectManager.INSTANCE.getRepresentations(sdd, currentSession);
										for(DRepresentation drep : rep){
											OpenRepresentationsAction ora = new OpenRepresentationsAction(sdd, drep);
											openDiagramMenu.add(ora);
										}
									}
								}
							} 
						}

					}
					// Attaches the "Open Diagram / Table..." menu to group.new of the parent commonViewer contextual menu.
					if (openDiagramMenu.getSize() > 0) {
						menu_p.appendToGroup(ICommonMenuConstants.GROUP_NEW, openDiagramMenu);
					}
				}
			}
		}
	}
}
