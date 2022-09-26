/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.toolkit.dialogs.OpenRepresentationDialog;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.SelectNewRepresentationAction;

public class OpenRelatedDiagramAction {

  EObject target;

  public OpenRelatedDiagramAction(EObject target) {
    this.target = DoubleClickBehaviourUtil.INSTANCE.getTargetSemanticElement(target);
  }

  public void run() {
    Collection<DRepresentationDescriptor> representations = RepresentationHelper
        .getAllRepresentationDescriptorsTargetedBy(Collections.singleton(target));
    if (!representations.isEmpty()) {
      if (representations.size() > 1) {
        Shell activeShell = Display.getDefault().getActiveShell();
        OpenRepresentationDialog dialog = new OpenRepresentationDialog(activeShell, representations);
        dialog.open();
        if (dialog.getReturnCode() == Window.OK) {
          new OpenRepresentationsAction(dialog.getSelectedDescriptor()).run();
        }
      } else {
        DRepresentationDescriptor descriptor = (DRepresentationDescriptor) representations.toArray()[0];
        new OpenRepresentationsAction(descriptor).run();
      }
    } else {
      Session currentSession = SessionManager.INSTANCE.getSession(target);
      Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
      Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
          .getAvailableRepresentationDescriptions(selectedViewpoints, target);
      if (!descriptions.isEmpty()) {
        if (descriptions.size() > 1) {
          new SelectNewRepresentationAction(descriptions, target, currentSession, Messages.OpenRelatedDiagram_Message)
              .run();
        } else {
          RepresentationDescription description = descriptions.iterator().next();
          new NewRepresentationAction(description, target, currentSession, Messages.OpenRelatedDiagram_Message).run();
        }

      }

    }
  }
}
