/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.NewScenarioRepresentationAction;

public abstract class AbstractNewScenarioDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {
  @Override
  public boolean createDiagram(EObject rootObj, Session session) {
    if (rootObj == null && session == null) {
      return false;
    }
    AbstractCapability capa = (AbstractCapability) rootObj;
    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
    for (Viewpoint vp : selectedViewpoints) {
      for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
        if (representationDescription instanceof SequenceDiagramDescription
            && representationDescription.getName().equals(getRepresentationName())) {
          NewRepresentationAction action = initNewScenarioRepresentationAction(session, capa,
              representationDescription);
          RecordingCommand cmd = new RecordingCommand(TransactionUtil.getEditingDomain(rootObj)) {
            @Override
            protected void doExecute() {
              action.run();
            }
          };
          TransactionUtil.getEditingDomain(rootObj).getCommandStack().execute(cmd);
          return true;
        }
      }
    }
    return false;
  }

  protected NewRepresentationAction initNewScenarioRepresentationAction(Session session, AbstractCapability capa,
      RepresentationDescription representationDescription) {
    return new NewScenarioRepresentationAction(representationDescription, capa, session, useDefaultName(), true);
  }
}
