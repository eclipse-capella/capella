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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualCapabilityRefreshExtension extends AbstractCacheAwareRefreshExtension {

  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    
    if (((DSemanticDecorator) diagram).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }

    List<Actor> actors = new LinkedList<>();
    Set<AbstractCapability> capabilities = new HashSet<>();
    List<Mission> missions = new LinkedList<>();

    final NodeMapping actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CC_COMPONENT_MAPPING_NAME);
    final NodeMapping capaNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CC_CAPABILITY_MAPPING_NAME);
    final NodeMapping missionNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CC_MISSION_MAPPING_NAME);

    final Capability capa = (Capability) ((DSemanticDecorator) diagram).getTarget();

    for (ActorCapabilityInvolvement inv : capa.getInvolvedActors()) {
      actors.add(inv.getActor());
      for (GeneralizableElement elt : inv.getActor().getSuper()) {
        if (elt instanceof Actor) {
          actors.add((Actor) elt);
        }
      }
    }

    capabilities.add(capa);
    capabilities.addAll(capa.getSuper());
    capabilities.addAll(capa.getSub());
    capabilities.addAll(capa.getExtendedAbstractCapabilities());
    capabilities.addAll(capa.getIncludedAbstractCapabilities());

    missions.addAll(CapellaServices.getService().getAllPurposeMission(capa));

    // node creation
    for (final Actor actor : actors) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, actor)) {
        final DDiagramElement container = actorNodeMapping.createNode(actor, capa, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final AbstractCapability cap : capabilities) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, cap)) {
        final DDiagramElement container = capaNodeMapping.createNode(cap, capa, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Mission mission : missions) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, mission)) {
        final DDiagramElement container = missionNodeMapping.createNode(mission, capa, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }
  }
}
