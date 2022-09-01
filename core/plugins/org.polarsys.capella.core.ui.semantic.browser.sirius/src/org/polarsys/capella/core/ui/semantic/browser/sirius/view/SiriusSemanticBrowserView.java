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
package org.polarsys.capella.core.ui.semantic.browser.sirius.view;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.dialogs.OpenRepresentationDialog;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.SelectNewRepresentationAction;
import org.polarsys.capella.core.ui.semantic.browser.sirius.actions.DiagramOpenAction;
import org.polarsys.capella.core.ui.semantic.browser.sirius.helpers.SiriusSelectionHelper;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Browser Semantic View. Load by extension point.
 */
public class SiriusSemanticBrowserView extends SemanticBrowserView {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part, ISelection selection) {
		return SiriusSelectionHelper.handleSelection(part, selection);
	}

	private final String NO_EXISTING_REPRESENTATION_MESSAGE = "No existing representation found, would you like to create one ?\n";

	/**
	 * @see org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView#handleDoubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	@Override
	protected void handleDoubleClick(DoubleClickEvent event) {
		boolean callSuper = true;
		//Get selection of currently selected viewer
		//Right now this is the only way to get the selection from Referenced or Referencing viewer
		IStructuredSelection selection = (IStructuredSelection) getSite().getSelectionProvider().getSelection();

		if (!selection.isEmpty()) {
			//If CTRL is pressed on double-click on a single element, it shall be put as the current element
			if( (selection.size() == 1 && !isCtrlKeyPressed()) || (selection.size() > 1)) {
				for(Object selectedElement : selection.toList()) {
					if (selectedElement instanceof EObjectWrapper) {
						selectedElement = ((EObjectWrapper) selectedElement).getElement();
					}
					if (selectedElement instanceof DRepresentationDescriptor) {
						DiagramOpenAction action = new DiagramOpenAction();
						// Open related diagram editor.
						action.init(this);
						action.selectionChanged(null, new StructuredSelection(selectedElement));
						action.run(null);
						// if it is DRepresentation; then open the representation and return immediately.
						// Do not run into super.handleDoubleClick in order to avoid opening the wizard properties
						callSuper = false;
					} else {	

						if (selectedElement instanceof EObject) {						
							EObject selectedElementAsEObject = (EObject) selectedElement;
							if( DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(selectedElementAsEObject)) {
								Collection<DRepresentationDescriptor>  representations = DoubleClickBehaviourUtil.INSTANCE.getRepresentationsDescriptors(selectedElementAsEObject);
								if (!representations.isEmpty()) {
									if (representations.size() > 1 ) {
										Shell activeShell = Display.getDefault().getActiveShell();
										OpenRepresentationDialog dialog = new OpenRepresentationDialog(activeShell, representations) ;					
										dialog.open();
										if (dialog.getReturnCode() == Window.OK) {
											new OpenRepresentationsAction(dialog.getSelectedDescriptor()).run();
										}
									} else {
										DRepresentationDescriptor element1 = (DRepresentationDescriptor) representations.toArray()[0];
										new OpenRepresentationsAction(element1).run();
									}
								} else {
									Session currentSession = SessionManager.INSTANCE.getSession(selectedElementAsEObject);
									Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
									Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, selectedElementAsEObject);
									if (!descriptions.isEmpty()) {
										if (descriptions.size() > 1) {
											new SelectNewRepresentationAction(descriptions, selectedElementAsEObject, currentSession, NO_EXISTING_REPRESENTATION_MESSAGE).run();
										} else {
											RepresentationDescription description = descriptions.iterator().next();						
											new NewRepresentationAction(description, selectedElementAsEObject, currentSession,NO_EXISTING_REPRESENTATION_MESSAGE).run();
										}

									}

								}
								callSuper = false;
							}
						}
					}
				}
			}
		}
		if(callSuper) {
			super.handleDoubleClick(event);      
		}
	}
}
