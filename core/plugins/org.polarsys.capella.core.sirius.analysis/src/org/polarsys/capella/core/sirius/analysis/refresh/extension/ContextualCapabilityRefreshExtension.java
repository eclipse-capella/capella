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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.model.helpers.ComponentExt;
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

    List<Component> actors = new LinkedList<>();
    Set<AbstractCapability> capabilities = new HashSet<>();
    List<Mission> missions = new LinkedList<>();

    final NodeMapping actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CC_COMPONENT_MAPPING_NAME);
    final NodeMapping capaNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CC_CAPABILITY_MAPPING_NAME);
    final NodeMapping missionNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CC_MISSION_MAPPING_NAME);

    final Capability capa = (Capability) ((DSemanticDecorator) diagram).getTarget();

    for (CapabilityInvolvement inv : capa.getOwnedCapabilityInvolvements()) {
      SystemComponent systemComponent = inv.getSystemComponent();
      if (ComponentExt.isActor(systemComponent)) {
        actors.add(systemComponent);
        for (GeneralizableElement elt : systemComponent.getSuper()) {
          if (ComponentExt.isActor(elt)) {
            actors.add((Component) elt);
          }
        }
      }
    }

    capabilities.add(capa);
    capabilities.addAll(capa.getSuper());
    capabilities.addAll(capa.getSub());
    capabilities.addAll(capa.getExtendedAbstractCapabilities());
    capabilities.addAll(capa.getIncludedAbstractCapabilities());

    missions.addAll(CapellaServices.getService().getAllPurposeMission(capa));

    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(capa);
    NodeMappingHelper nodeMappingHelper = new NodeMappingHelper(interpreter);

    // node creation
    for (final Component actor : actors) {

      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, actor)) {
        final DDiagramElement container = nodeMappingHelper.createNode(actorNodeMapping, actor, capa,
            diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final AbstractCapability cap : capabilities) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, cap)) {
        final DDiagramElement container = nodeMappingHelper.createNode(capaNodeMapping, cap, capa,
            diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }

    for (final Mission mission : missions) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, mission)) {
        final DDiagramElement container = nodeMappingHelper.createNode(missionNodeMapping, mission,
            capa, diagram);
        diagram.getOwnedDiagramElements().add(container);
      }
    }
  }
}
