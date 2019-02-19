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
package org.polarsys.capella.core.model.helpers.graph;

import java.util.Collection;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementNode;

public class InvolvementGraph extends
    Graph<FunctionalChain, FunctionalChainInvolvementFunction, FunctionalChainInvolvementLink, InvolvementNode, InvolvementEdge> {

  public class InvolvementEdge extends GraphEdge<FunctionalChainInvolvementLink, InvolvementNode> {
    public InvolvementEdge(FunctionalChainInvolvementLink semantic) {
      super(semantic);
    }
  }

  public class InvolvementNode extends GraphNode<FunctionalChainInvolvementFunction, InvolvementEdge> {
    public InvolvementNode(FunctionalChainInvolvementFunction semantic) {
      super(semantic);
    }
  }

  public InvolvementGraph(FunctionalChain chain) {
    super(chain);

    for (FunctionalChainInvolvement inv : FunctionalChainExt.getFlatInvolvements(chain)) {
      if (inv instanceof FunctionalChainInvolvementFunction) {
        getOrCreateNode((FunctionalChainInvolvementFunction) inv);

      } else if (inv instanceof FunctionalChainInvolvementLink) {
        FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) inv;
        InvolvementEdge info = getOrCreateEdge(link);
        info.setSource(getOrCreateNode(link.getSource()));
        info.setTarget(getOrCreateNode(link.getTarget()));
      }
    }

    // When there is a link between two same functions and involving it function
    // merge source and target, and remove link
    Collection<InvolvementEdge> edges = getEdges().values().stream().filter(this::isInvolvingFunction)
        .collect(Collectors.toList());
    for (InvolvementEdge edge : edges) {
      mergeNodes(edge.getSource(), edge.getTarget(), edge.getSource().getSemantic());
      removeEdge(edge);
    }
  }

  public boolean isInvolvingFunction(InvolvementNode node) {
    FunctionalChainInvolvementFunction function = node.getSemantic();
    if (function.getInvolved() instanceof AbstractFunction) {
      return true;
    }
    return false;
  }

  public boolean isInvolvingFunction(InvolvementEdge edge) {
    FunctionalChainInvolvementLink link = edge.getSemantic();
    if (link.getInvolved() instanceof AbstractFunction) {
      return true;
    }
    return false;
  }

  public boolean isInvolvingFunctionalExchange(InvolvementEdge edge) {
    FunctionalChainInvolvementLink link = edge.getSemantic();
    if (link.getInvolved() instanceof FunctionalExchange) {
      return true;
    }
    return false;
  }

  public boolean isStartingFunction(InvolvementNode node) {
    return node.getIncomingEdges().isEmpty();
  }

  public boolean isEndingFunction(InvolvementNode node) {
    return node.getOutgoingEdges().isEmpty();
  }

  public AbstractFunction getInvolvedFunction(InvolvementNode node) {
    return (AbstractFunction) node.getSemantic().getInvolved();
  }

  public FunctionalExchange getInvolvedFunctionalExchange(InvolvementEdge edge) {
    return (FunctionalExchange) edge.getSemantic().getInvolved();
  }

  @Override
  public InvolvementNode createNode(FunctionalChainInvolvementFunction semantic) {
    return new InvolvementNode(semantic);
  }

  @Override
  public InvolvementEdge createEdge(FunctionalChainInvolvementLink semantic) {
    return new InvolvementEdge(semantic);
  }

}