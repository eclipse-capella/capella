/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementNode;

public class InvolvementGraph
    extends Graph<FunctionalChain, SequenceLinkEnd, ReferenceHierarchyContext, InvolvementNode, InvolvementEdge> {

  public class InvolvementEdge extends GraphEdge<ReferenceHierarchyContext, InvolvementNode> {
    public InvolvementEdge(ReferenceHierarchyContext semantic) {
      super(semantic);
    }
  }

  public class InvolvementNode extends GraphNode<SequenceLinkEnd, InvolvementEdge> {
    public InvolvementNode(SequenceLinkEnd semantic) {
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

    // add control nodes
    for (ControlNode controlNode : FunctionalChainExt.getFlatControlNodes(chain)) {
      getOrCreateNode(controlNode);
    }

    // add sequence links
    for (SequenceLink sequenceLink : FunctionalChainExt.getFlatSequenceLinks(chain)) {
      InvolvementEdge info = getOrCreateEdge(sequenceLink);
      info.setSource(getOrCreateNode(sequenceLink.getSource()));
      info.setTarget(getOrCreateNode(sequenceLink.getTarget()));
    }
  }

  public boolean isInvolvingFunction(InvolvementNode node) {

    SequenceLinkEnd semantic = node.getSemantic();
    return semantic instanceof FunctionalChainInvolvementFunction
        && ((FunctionalChainInvolvementFunction) semantic).getInvolved() instanceof AbstractFunction;
  }

  public boolean isInvolvingFunction(InvolvementEdge edge) {

    ReferenceHierarchyContext semantic = edge.getSemantic();
    return semantic instanceof FunctionalChainInvolvementLink
        && ((FunctionalChainInvolvementLink) semantic).getInvolved() instanceof AbstractFunction;
  }

  public boolean isControlNode(InvolvementNode node) {
    return node.getSemantic() instanceof ControlNode;
  }

  public boolean isInvolvingFunctionalExchange(InvolvementEdge edge) {

    ReferenceHierarchyContext semantic = edge.getSemantic();
    return semantic instanceof FunctionalChainInvolvementLink
        && ((FunctionalChainInvolvementLink) semantic).getInvolved() instanceof FunctionalExchange;
  }

  public boolean isSequenceLink(InvolvementEdge edge) {
    return edge.getSemantic() instanceof SequenceLink;
  }

  /**
   * A node is considered a starting function if either he has no incoming edges or if all incoming edges reference
   * Sequence Links.
   * 
   * @param node
   *          the involvement node.
   * @return true if a starting function, false otherwise.
   */
  public boolean isStartingFunction(InvolvementNode node) {
    return node.getIncomingEdges().stream().allMatch(this::isSequenceLink);
  }

  /**
   * A node is considered a ending function if either he has no outgoing edges or if all outgoing edges reference
   * Sequence Links.
   * 
   * @param node
   *          the involvement node.
   * @return true if a starting function, false otherwise.
   */
  public boolean isEndingFunction(InvolvementNode node) {
    return node.getOutgoingEdges().stream().allMatch(this::isSequenceLink);
  }

  public ControlNode getControlNode(InvolvementNode node) {
    return (ControlNode) node.getSemantic();
  }

  public AbstractFunction getInvolvedFunction(InvolvementNode node) {

    SequenceLinkEnd semantic = node.getSemantic();

    if (semantic instanceof FunctionalChainInvolvementFunction) {
      FunctionalChainInvolvementFunction function = (FunctionalChainInvolvementFunction) semantic;
      return (AbstractFunction) function.getInvolved();
    }

    return null;
  }

  public FunctionalExchange getInvolvedFunctionalExchange(InvolvementEdge edge) {

    ReferenceHierarchyContext semantic = edge.getSemantic();
    if (semantic instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) semantic;
      return (FunctionalExchange) link.getInvolved();
    }

    return null;
  }

  public SequenceLink getSequenceLink(InvolvementEdge edge) {
    return (SequenceLink) edge.getSemantic();
  }

  @Override
  public InvolvementNode createNode(SequenceLinkEnd semantic) {
    return new InvolvementNode(semantic);
  }

  @Override
  public InvolvementEdge createEdge(ReferenceHierarchyContext semantic) {
    return new InvolvementEdge(semantic);
  }

}