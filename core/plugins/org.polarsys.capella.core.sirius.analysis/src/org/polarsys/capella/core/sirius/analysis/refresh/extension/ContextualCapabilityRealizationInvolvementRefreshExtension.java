/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualCapabilityRealizationInvolvementRefreshExtension extends AbstractRefreshExtension
    implements IRefreshExtension {

  @Override
  public void beforeRefresh(DDiagram diagram) {

    if (((DSemanticDecorator) diagram).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }

    // Get all graphical node mappings of the diagram so that we can create
    // graphical elements with them
    final NodeMapping actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCRI_ACTOR);
    final NodeMapping componentNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCRI_COMPONENT);
    final NodeMapping capabilityRealizationMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCRI_CAPABILITY_REALIZATION);
    final EdgeMapping actorCapabilityRealizationInvolvementMapping = DiagramServices.getDiagramServices()
        .getEdgeMapping(diagram, IMappingNameConstants.CCRI_ACTOR_CAPABILITY_REALIZATION_INVOLVEMENT);
    final EdgeMapping systemComponentCapabilityRealizationInvolvementMapping = DiagramServices.getDiagramServices()
        .getEdgeMapping(diagram, IMappingNameConstants.CCRI_SYSTEM_COMPONENT_CAPABILITY_REALIZATION_INVOLVEMENT);

    // Get the main CapabilityRealization semantic object and its corresponding
    // graphical object
    final CapabilityRealization mainCapability = (CapabilityRealization) ((DSemanticDecorator) diagram).getTarget();
    DDiagramElement mainCapabilityGraphicalNode = DiagramServices.getDiagramServices()
        .getDiagramElements(diagram, capabilityRealizationMapping, mainCapability);

    // If the graphical node is not already on the diagram, we create it
    if (null == mainCapabilityGraphicalNode) {

      mainCapabilityGraphicalNode = capabilityRealizationMapping.createNode(mainCapability, mainCapability, diagram);

      diagram.getOwnedDiagramElements().add(mainCapabilityGraphicalNode);
    }

    // For each Actor linked to the main CapabilityRealization node with an
    // Involvement link, we will create its graphical representation as well as the
    // graphical representation of the link
    DDiagramElement graphicalNode = null;
    for (ActorCapabilityRealizationInvolvement inv : mainCapability.getInvolvedActors()) {

      final AbstractActor currentActor = (AbstractActor) inv.getInvolved();
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, currentActor)) {
        graphicalNode = actorNodeMapping.createNode(currentActor, mainCapability, diagram);

        diagram.getOwnedDiagramElements().add(graphicalNode);
      }

      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, inv)) {

        // If the target of the Involvement is not present on the diagram, we create it
        if (null == graphicalNode) {

          graphicalNode = DiagramServices.getDiagramServices().getDiagramElements(diagram, actorNodeMapping,
              currentActor);
        }

        final DDiagramElement graphicalEdge = DiagramServices.getDiagramServices().createEdge(
            actorCapabilityRealizationInvolvementMapping, (EdgeTarget) mainCapabilityGraphicalNode,
            (EdgeTarget) graphicalNode, inv);

        diagram.getOwnedDiagramElements().add(graphicalEdge);
      }
    }

    // For each Component linked to the main CapabilityRealization node with an
    // Involvement link, we will create its graphical representation as well as the
    // graphical representation of the link
    graphicalNode = null;
    for (SystemComponentCapabilityRealizationInvolvement inv : mainCapability.getInvolvedSystemComponents()) {

      final SystemComponent currentComponent = (SystemComponent) inv.getInvolved();
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, currentComponent)) {
        graphicalNode = componentNodeMapping.createNode(currentComponent, mainCapability, diagram);

        diagram.getOwnedDiagramElements().add(graphicalNode);
      }

      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, inv)) {

        // If the target of the Involvement is not present on the diagram, we create it
        if (null == graphicalNode) {

          graphicalNode = DiagramServices.getDiagramServices().getDiagramElements(diagram, componentNodeMapping,
              currentComponent);
        }

        final DDiagramElement graphicalEdge = DiagramServices.getDiagramServices().createEdge(
            systemComponentCapabilityRealizationInvolvementMapping, (EdgeTarget) mainCapabilityGraphicalNode,
            (EdgeTarget) graphicalNode, inv);

        diagram.getOwnedDiagramElements().add(graphicalEdge);
      }
    }
  }

  @Override
  public void postRefresh(DDiagram dDiagram) {
    // nothing here
  }

}
