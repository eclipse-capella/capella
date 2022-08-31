/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ui.toolkit.dialogs.OpenRepresentationDialog;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.SelectNewRepresentationAction;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

public class OpenAction extends BaseSelectionListenerAction {

	private final String NO_EXISTING_REPRESENTATION_MESSAGE = "No existing representation found, would you like to create one ?\n";

	public OpenAction() {
		super("Open properties or related diagrams if enabled");
	}

	@Override
	public void run() {
		IStructuredSelection selection = getStructuredSelection();
		Object element = selection.getFirstElement();

		if (CapellaResourceHelper.isSemanticElement(element)) {
			EObject elementAsEObject = (EObject) element;
			if(!DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(elementAsEObject)) {
				CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) element);
			} else {   
				Collection<DRepresentationDescriptor>  representations = DoubleClickBehaviourUtil.INSTANCE.getRepresentationsDescriptors(elementAsEObject);
				if (!representations.isEmpty()) {
					if(representations.size() > 1 ) {
						Shell activeShell = Display.getDefault().getActiveShell();
						OpenRepresentationDialog dialog = new OpenRepresentationDialog(activeShell, representations) ;					
						dialog.open();
						if (dialog.getReturnCode() == Window.OK) {
							new OpenRepresentationsAction(dialog.getSelectedDescriptor()).run();
						}
					} else {
						DRepresentationDescriptor descriptor = (DRepresentationDescriptor) representations.toArray()[0];
						new OpenRepresentationsAction(descriptor).run();
					}
				} else {
					Session currentSession = SessionManager.INSTANCE.getSession(elementAsEObject);
					Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
					Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, elementAsEObject);
					if (!descriptions.isEmpty()) {
						if (descriptions.size() > 1) {
							new SelectNewRepresentationAction(descriptions, elementAsEObject, currentSession, NO_EXISTING_REPRESENTATION_MESSAGE).run();
						} else {
							RepresentationDescription description = descriptions.iterator().next();						
							new NewRepresentationAction(description, elementAsEObject, currentSession, NO_EXISTING_REPRESENTATION_MESSAGE).run();
						}

					}

				}
			}
		}
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		boolean result = false;

		if (!selection.isEmpty()) {
			result = (CapellaResourceHelper.isSemanticElements(selection.toList())) ? true : false;
		}

		return result;
	}  

}
