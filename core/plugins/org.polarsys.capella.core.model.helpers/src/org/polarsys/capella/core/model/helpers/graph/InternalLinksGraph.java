/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph.InternalLinkNode;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementEdge;

public class InternalLinksGraph
    extends Graph<FunctionalChain, FunctionPort, InternalLink, InternalLinkNode, InternalLinkEdge> {

  public class InternalLinkEdge extends GraphEdge<InternalLink, InternalLinkNode> {

    public InternalLinkEdge(InternalLink semantic) {
      super(semantic);
    }

  }

  public class InternalLinkNode extends GraphNode<FunctionPort, InternalLinkEdge> {

    public InternalLinkNode(FunctionPort semantic) {
      super(semantic);
    }
  }

  public InternalLinksGraph(InvolvementGraph involvementGraph) {
    super(involvementGraph.getSemantic());

    for (InvolvementEdge edge : involvementGraph.getEdges().values()) {
      if (involvementGraph.isInvolvingFunctionalExchange(edge)) {
        FunctionalExchange exchange = involvementGraph.getInvolvedFunctionalExchange(edge);
        InternalLinkNode sourceCurrent = getOrCreateNode((FunctionPort) exchange.getSource());

        // Create internal links between two functional exchanges
        for (InvolvementEdge e : edge.getSource().getIncomingEdges()) {
          if (involvementGraph.isInvolvingFunctionalExchange(e)) {
            FunctionalExchange previousExchange = involvementGraph.getInvolvedFunctionalExchange(e);
            InternalLinkNode targetPrevious = getOrCreateNode((FunctionPort) previousExchange.getTarget());
            InternalLinkEdge newEdge = getOrCreateEdge(
                new InternalLink(targetPrevious.getSemantic(), sourceCurrent.getSemantic()));
            newEdge.setSource(targetPrevious);
            newEdge.setTarget(sourceCurrent);
          }
        }
      }
    }

  }

  public boolean hasInternalLink(EObject source, EObject target) {
    return edges.keySet().stream().filter(InternalLink.class::isInstance).map(InternalLink.class::cast)
        .anyMatch(key -> key.getSource() == source && key.getTarget() == target);
  }

  @Override
  public InternalLinkNode createNode(FunctionPort semantic) {
    return new InternalLinkNode(semantic);
  }

  @Override
  public InternalLinkEdge createEdge(InternalLink semantic) {
    return new InternalLinkEdge(semantic);
  }

}