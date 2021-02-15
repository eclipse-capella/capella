/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.graph;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInternalLinksGraph.InternalLinkNode;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph.InvolvementNode;

public class PhysicalPathInternalLinksGraph
    extends Graph<PhysicalPath, PhysicalPort, PhysicalPathInternalLink, InternalLinkNode, InternalLinkEdge> {

  public class InternalLinkEdge extends GraphEdge<PhysicalPathInternalLink, InternalLinkNode> {
    public InternalLinkEdge(PhysicalPathInternalLink semantic) {
      super(semantic);
    }
  }

  public class InternalLinkNode extends GraphNode<PhysicalPort, InternalLinkEdge> {
    public InternalLinkNode(PhysicalPort semantic) {
      super(semantic);
    }
  }

  public PhysicalPathInternalLinksGraph(PhysicalPathInvolvementGraph involvementGraph) {
    super(involvementGraph.getSemantic());
    for (InvolvementEdge edge : involvementGraph.getEdges().values()) {
      if (involvementGraph.isInvolvingPhysicalLink(edge)) {
        PhysicalLink pl = involvementGraph.getInvolvedPhysicalLink(edge);
        InternalLinkNode firstPortNode = getOrCreateNode(pl.getSourcePhysicalPort());
        InternalLinkNode secondPortNode = getOrCreateNode(pl.getTargetPhysicalPort());
        // Set up internal link edge for the first port
        for (PhysicalPort connectedPort : getConnectedPorts(firstPortNode, edge)) {
          InternalLinkNode connectedPortNode = getOrCreateNode(connectedPort);
          if (!hasInternalLink(firstPortNode.getSemantic(), connectedPort)) {
            InternalLinkEdge newEdge = getOrCreateEdge(
                new PhysicalPathInternalLink(firstPortNode.getSemantic(), connectedPort));
            newEdge.setSource(firstPortNode);
            newEdge.setTarget(connectedPortNode);
          }
        }
        // Set up internal link edge for the second port
        for (PhysicalPort connectedPort : getConnectedPorts(secondPortNode, edge)) {
          InternalLinkNode connectedPortNode = getOrCreateNode(connectedPort);
          if (!hasInternalLink(secondPortNode.getSemantic(), connectedPort)) {
            InternalLinkEdge newEdge = getOrCreateEdge(
                new PhysicalPathInternalLink(secondPortNode.getSemantic(), connectedPort));
            newEdge.setSource(secondPortNode);
            newEdge.setTarget(connectedPortNode);
          }
        }
      }
    }
  }
  
  /**
   * 
   * @param aPort
   * @param edge
   * @return all ports connected via the PhysicalPathInternalLinksGraph to the given port of the involvement edge
   */
  public Set<PhysicalPort> getConnectedPorts(InternalLinkNode portNode, InvolvementEdge edge) {
    Optional<InvolvementNode> containerNodeOpt = edge.getConnectedNodes().stream().filter(
        node -> node.getSemantic().getInvolvedComponent().getContainedPhysicalPorts().contains(portNode.getSemantic()))
        .findFirst();
    if (containerNodeOpt.isPresent()) {
      InvolvementNode containerNode = containerNodeOpt.get();
      return containerNode.getConnectedEdges().stream().filter(otherEdge -> otherEdge != edge)
          .map(e -> PhysicalPathInvolvementGraph.getInvolvedPhysicalPort(e, containerNode)).filter(Objects::nonNull)
          .collect(Collectors.toSet());
    }
    return Collections.emptySet();
  }

  public boolean hasInternalLink(EObject source, EObject target) {
    return edges.keySet().stream().filter(PhysicalPathInternalLink.class::isInstance)
        .map(PhysicalPathInternalLink.class::cast)
        .anyMatch(key -> (key.getSource() == source && key.getTarget() == target)
            || (key.getTarget() == source && key.getSource() == target));
  }

  @Override
  public InternalLinkNode createNode(PhysicalPort semantic) {
    return new InternalLinkNode(semantic);
  }

  @Override
  public InternalLinkEdge createEdge(PhysicalPathInternalLink semantic) {
    return new InternalLinkEdge(semantic);
  }
}