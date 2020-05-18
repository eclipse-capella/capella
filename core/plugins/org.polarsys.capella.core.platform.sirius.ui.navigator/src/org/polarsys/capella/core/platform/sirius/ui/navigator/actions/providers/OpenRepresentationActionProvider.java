/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
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
  public void fillContextMenu(IMenuManager menu) {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    Object firstElement = selection.getFirstElement();
    if (firstElement instanceof EObject) {
      EObject firstSelectedEObject = (EObject) firstElement;
      Session currentSession = SessionManager.INSTANCE.getSession(firstSelectedEObject);
      if (null != currentSession) {
        Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
        Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
            .getAvailableRepresentationDescriptions(selectedViewpoints, firstSelectedEObject);

        if (!descriptions.isEmpty()) {
          // Creates the "New Diagram / Table" menu.
          MenuManager openDiagramMenu = new MenuManager(
              Messages.OpenRepresentationActionProvider_OpenRepresentationAction_Title, "capella.project.diagrams.menu"); //$NON-NLS-1$

          for (RepresentationDescription description : descriptions) {
            // Computes the "Open Diagram / Table..." menu content according to the current selection.
            Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE
                .getRepresentationDescriptors(description, currentSession);
            if (descriptors != null) {
              Collection<DRepresentationDescriptor> ownedRep = DialectManager.INSTANCE.getRepresentationDescriptors(firstSelectedEObject,
                  currentSession);
              descriptors.retainAll(ownedRep);
              for (DRepresentationDescriptor descriptor : descriptors) {
                OpenRepresentationsAction ora = new OpenRepresentationsAction(descriptor);
                openDiagramMenu.add(ora);
              }
            }
          }

          // create scenarios from capabilities
          if (firstSelectedEObject instanceof AbstractCapability) {
            for (Viewpoint vp : selectedViewpoints) {
              for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
                if (representationDescription instanceof SequenceDiagramDescription) {
                  SequenceDiagramDescription sdd = (SequenceDiagramDescription) representationDescription;
                  Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE
                      .getRepresentationDescriptors(sdd, currentSession);
                  if (descriptors != null) {
                    // Select only scenarios belonging to the Capability
                    Collection<DRepresentationDescriptor> ownedDescriptors = new ArrayList<>();
                    EList<Scenario> lstScenarios = ((AbstractCapability) firstSelectedEObject).getOwnedScenarios();
                    for (Scenario scenario : lstScenarios) {
                      Collection<DRepresentationDescriptor> repDescScenario = DialectManager.INSTANCE
                          .getRepresentationDescriptors(scenario,
                          currentSession);
                      ownedDescriptors.addAll(repDescScenario);
                    }

                    descriptors.retainAll(ownedDescriptors);

                    for (DRepresentationDescriptor descriptor : descriptors) {
                      OpenRepresentationsAction ora = new OpenRepresentationsAction(descriptor);
                      openDiagramMenu.add(ora);
                    }
                  }
                }
              }
            }

          }
          // Attaches the "Open Diagram / Table..." menu to group.new of the parent commonViewer contextual menu.
          if (openDiagramMenu.getSize() > 0) {
            menu.appendToGroup(ICommonMenuConstants.GROUP_NEW, openDiagramMenu);
          }
        }
      }
    }
  }
}
