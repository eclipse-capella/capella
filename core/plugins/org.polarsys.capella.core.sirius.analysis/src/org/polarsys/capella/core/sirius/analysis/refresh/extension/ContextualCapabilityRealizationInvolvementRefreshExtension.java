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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualCapabilityRealizationInvolvementRefreshExtension extends RefreshExtension {

  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);

    if (((DSemanticDecorator) diagram).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }

    final NodeMapping capabilityRealizationMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCRI_CAPABILITY_REALIZATION);

    // Get the main CapabilityRealization semantic object and its corresponding
    // graphical object
    final CapabilityRealization mainCapability = (CapabilityRealization) ((DSemanticDecorator) diagram).getTarget();
    DDiagramElement mainCapabilityGraphicalNode = DiagramServices.getDiagramServices().getDiagramElements(diagram,
        capabilityRealizationMapping, mainCapability);

    // If the graphical node is not already on the diagram, we create it
    if (null == mainCapabilityGraphicalNode) {

      mainCapabilityGraphicalNode = getNodeMappingHelper(mainCapability)
          .createNode((INodeMappingExt) capabilityRealizationMapping, mainCapability, mainCapability, diagram);
      diagram.getOwnedDiagramElements().add(mainCapabilityGraphicalNode);
    }

    // We display elements related to the contextual capability only if the diagram is synchronized
    NodeMapping actorNodeMapping = null;
    NodeMapping componentNodeMapping = null;
    EdgeMapping capabilityRealizationInvolvementMapping = null;
    if (diagram.isSynchronized()) {

      EList<ActorCapabilityRealizationInvolvement> actorInvolvements = mainCapability.getInvolvedActors();
      EList<SystemComponentCapabilityRealizationInvolvement> componentInvolvements = mainCapability
          .getInvolvedSystemComponents();

      // Get all graphical node mappings of the diagram so that we can create
      // graphical elements with them only if there are any present
      if (!actorInvolvements.isEmpty()) {
        actorNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CCRI_ACTOR);
        capabilityRealizationInvolvementMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CCRI_CAPABILITY_REALIZATION_INVOLVEMENT);
      }

      if (!componentInvolvements.isEmpty()) {
        componentNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CCRI_COMPONENT);

        // We may have no actor involvements and as such we need the involvement mapping now
        if (null == capabilityRealizationInvolvementMapping) {
          capabilityRealizationInvolvementMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
              IMappingNameConstants.CCRI_CAPABILITY_REALIZATION_INVOLVEMENT);
        }
      }

      DDiagramElement graphicalNode = null;
      if (null != actorNodeMapping) {

        // For each Actor linked to the main CapabilityRealization node with an
        // Involvement link, we will create its graphical representation as well as the
        // graphical representation of the link
        for (ActorCapabilityRealizationInvolvement inv : actorInvolvements) {

          final AbstractActor currentActor = (AbstractActor) inv.getInvolved();
          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, currentActor)) {
            graphicalNode = getNodeMappingHelper(currentActor).createNode((INodeMappingExt) actorNodeMapping,
                currentActor, mainCapability, diagram);
            diagram.getOwnedDiagramElements().add(graphicalNode);
          }

          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, inv)) {

            // If the target of the Involvement is not present on the diagram, we create it
            if (null == graphicalNode) {

              graphicalNode = DiagramServices.getDiagramServices().getDiagramElements(diagram, actorNodeMapping,
                  currentActor);
            }

            final DDiagramElement graphicalEdge = DiagramServices.getDiagramServices().createEdge(
                capabilityRealizationInvolvementMapping, (EdgeTarget) mainCapabilityGraphicalNode,
                (EdgeTarget) graphicalNode, inv);

            diagram.getOwnedDiagramElements().add(graphicalEdge);
          }
        }
      }

      if (null != componentNodeMapping) {

        // For each Component linked to the main CapabilityRealization node with an
        // Involvement link, we will create its graphical representation as well as the
        // graphical representation of the link
        graphicalNode = null;
        for (SystemComponentCapabilityRealizationInvolvement inv : componentInvolvements) {

          final SystemComponent currentComponent = (SystemComponent) inv.getInvolved();
          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, currentComponent)) {
            graphicalNode = getNodeMappingHelper(currentComponent).createNode((INodeMappingExt) componentNodeMapping,
                currentComponent, mainCapability, diagram);

            diagram.getOwnedDiagramElements().add(graphicalNode);
          }

          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, inv)) {

            // If the target of the Involvement is not present on the diagram, we create it
            if (null == graphicalNode) {

              graphicalNode = DiagramServices.getDiagramServices().getDiagramElements(diagram, componentNodeMapping,
                  currentComponent);
            }

            final DDiagramElement graphicalEdge = DiagramServices.getDiagramServices().createEdge(
                capabilityRealizationInvolvementMapping, (EdgeTarget) mainCapabilityGraphicalNode,
                (EdgeTarget) graphicalNode, inv);

            diagram.getOwnedDiagramElements().add(graphicalEdge);
          }
        }
      }
    }
  }

  private NodeMappingHelper getNodeMappingHelper(EObject element) {
    return new NodeMappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element));
  }

}
