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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

public class MoveRepresentationMenuManager extends MenuManager implements ISelectionChangedListener {

  public MoveRepresentationMenuManager() {
    super(Messages.RepresentationActionProvider_MovediagramSubMenu_Title, "MoveDiagrams.Menu.ID");
  }

  public void initializeSubMenus(ISelection selection) {
    // Remove existing menu items in order to make sure action contained list are freed at each selection time.
    this.removeAll();

    List<?> selectionList = ((IStructuredSelection) selection).toList();
    Collection<DRepresentationDescriptor> descriptors = RepresentationHelper.getSelectedDescriptors(selectionList);

    if (!descriptors.isEmpty()) {
      Session session = null;

      // Retrieve given sessions. If they belongs to the same session, we continue
      List<Session> sessions = descriptors.stream().map(DRepresentationDescriptor::getTarget)
          .map(SessionManager.INSTANCE::getSession).distinct().filter(Objects::nonNull).collect(Collectors.toList());

      // If all selected elements were EObjects, retrieve editing domain if elements are from the same session
      if (sessions.size() == 1) {
        session = sessions.get(0);
      }

      ArrayList<Resource> availableTargetResources = (session == null) ? new ArrayList<>()
          : new ArrayList<>(((DAnalysisSession) session).getAllSessionResources());

      // Retrieve resources of descriptors
      Collection<Resource> representationResources = descriptors.stream().map(DRepresentationDescriptor::eResource)
          .collect(Collectors.toSet());

      // target resources are those of the session but not the selected representations.
      // TODO: the case when 2 selected representations belong to 2 different resources
      availableTargetResources.removeAll(representationResources);

      if (!availableTargetResources.isEmpty()) {
        for (final Resource availableTargetResource : availableTargetResources) {

          Collection<DAnalysis> availableDAnalysys = EcoreUtil.getObjectsByType(availableTargetResource.getContents(),
              ViewpointPackage.eINSTANCE.getDAnalysis());

          for (DAnalysis dAnalysis : availableDAnalysys) {
            IAction action = new MoveRepresentationAction((DAnalysisSession) session, descriptors, dAnalysis);
            this.add(action);
          }

        }
      }
    }
  }

  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    try {
      ISelection selection = event.getSelection();
      initializeSubMenus(selection);
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Do nothing.
      } else {
        throw e;
      }
    }
  }
}
