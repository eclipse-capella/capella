/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualMissionRefreshExtension extends AbstractCacheAwareRefreshExtension {

  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);

    if (((DSemanticDecorator) diagram).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }
    List<Component> actors = new LinkedList<>();
    List<Capability> capabilities = new LinkedList<>();
    List<Mission> missions = new LinkedList<>();

    final NodeMapping actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_COMPONENT_MAPPING_NAME);
    final NodeMapping capaNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_CAPABILITY_MAPPING_NAME);
    final NodeMapping missionNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_MISSION_MAPPING_NAME);

    final Mission currentMission = (Mission) ((DSemanticDecorator) diagram).getTarget();

    for (Involvement inv : currentMission.getInvolvedInvolvements()) {
      InvolvedElement involved = inv.getInvolved();
      if (ComponentExt.isActor(involved)) {
        Component actor = (Component) involved;
        actors.add(actor);
        for (GeneralizableElement elt : actor.getSuper()) {
          if (ComponentExt.isActor(elt)) {
            actors.add((Component) elt);
          }
        }
      }
    }
    missions.add(currentMission);
    capabilities.addAll(currentMission.getExploitedCapabilities());

    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(currentMission);
    NodeMappingHelper nodeMappingHelper = new NodeMappingHelper(interpreter);

    // node creation

    for (final Component actor : actors) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, actor)) {
        final DDiagramElement container = nodeMappingHelper.createNode(actorNodeMapping, actor,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Capability cap : capabilities) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, cap)) {
        final DDiagramElement container = nodeMappingHelper.createNode(capaNodeMapping, cap,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Mission mission : missions) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, mission)) {
        final DDiagramElement container = nodeMappingHelper.createNode(missionNodeMapping, mission,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }
  }
}
