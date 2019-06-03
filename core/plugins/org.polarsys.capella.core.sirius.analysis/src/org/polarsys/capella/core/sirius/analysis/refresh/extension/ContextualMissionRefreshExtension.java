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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
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
    List<Actor> actors = new LinkedList<>();
    List<Capability> capabilities = new LinkedList<>();
    List<Mission> missions = new LinkedList<>();

    final NodeMapping actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_COMPONENT_MAPPING_NAME);
    final NodeMapping capaNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_CAPABILITY_MAPPING_NAME);
    final NodeMapping missionNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CM_MISSION_MAPPING_NAME);

    final Mission currentMission = (Mission) ((DSemanticDecorator) diagram).getTarget();

    for (ActorMissionInvolvement inv : currentMission.getInvolvedActors()) {
      actors.add(inv.getActor());
      for (GeneralizableElement elt : inv.getActor().getSuper()) {
        if (elt instanceof Actor) {
          actors.add((Actor) elt);
        }
      }
    }
    missions.add(currentMission);
    capabilities.addAll(currentMission.getExploitedCapabilities());

    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(currentMission);
    NodeMappingHelper nodeMappingHelper = new NodeMappingHelper(interpreter);

    // node creation

    for (final Actor actor : actors) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, actor)) {
        final DDiagramElement container = nodeMappingHelper.createNode((INodeMappingExt) actorNodeMapping, actor,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Capability cap : capabilities) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, cap)) {
        final DDiagramElement container = nodeMappingHelper.createNode((INodeMappingExt) capaNodeMapping, cap,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Mission mission : missions) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, mission)) {
        final DDiagramElement container = nodeMappingHelper.createNode((INodeMappingExt) missionNodeMapping, mission,
            currentMission, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }
  }
}
