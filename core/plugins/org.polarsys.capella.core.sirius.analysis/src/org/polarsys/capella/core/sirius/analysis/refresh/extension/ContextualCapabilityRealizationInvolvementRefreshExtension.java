/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualCapabilityRealizationInvolvementRefreshExtension extends AbstractCacheAwareRefreshExtension {

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
    DDiagramElement mainCapabilityGraphicalNode = DiagramServices.getDiagramServices().getDiagramElement(diagram,
        capabilityRealizationMapping, mainCapability);

    // If the graphical node is not already on the diagram, we create it
    if (null == mainCapabilityGraphicalNode) {

      mainCapabilityGraphicalNode = getNodeMappingHelper(mainCapability)
          .createNode(capabilityRealizationMapping, mainCapability, mainCapability, diagram);
      diagram.getOwnedDiagramElements().add(mainCapabilityGraphicalNode);
    }

    // We display elements related to the contextual capability only if the diagram is synchronized
    NodeMapping componentNodeMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCRI_COMPONENT);
    EdgeMapping capabilityRealizationInvolvementMapping = null;
    if (diagram.isSynchronized()) {

      EList<CapabilityRealizationInvolvement> involvements = mainCapability.getOwnedCapabilityRealizationInvolvements();
      if (!involvements.isEmpty()) {
        capabilityRealizationInvolvementMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.CCRI_CAPABILITY_REALIZATION_INVOLVEMENT);
        DDiagramElement graphicalNode = null;
        // For each Component linked to the main CapabilityRealization node with an
        // Involvement link, we will create its graphical representation as well as the
        // graphical representation of the link
        for (CapabilityRealizationInvolvement inv : involvements) {
          final InvolvedElement involvedElement = inv.getInvolved();
          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, involvedElement)) {
            graphicalNode = getNodeMappingHelper(involvedElement).createNode(componentNodeMapping,
                involvedElement, mainCapability, diagram);
            diagram.getOwnedDiagramElements().add(graphicalNode);
          }
          if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, inv)) {
            // If the target of the Involvement is not present on the diagram, we create it
            if (null == graphicalNode) {
              graphicalNode = DiagramServices.getDiagramServices().getDiagramElement(diagram, componentNodeMapping,
                  involvedElement);
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
