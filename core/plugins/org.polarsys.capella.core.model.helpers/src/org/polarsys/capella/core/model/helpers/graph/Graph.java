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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A 'simple' graph with nodes and edges Graph, node, and edges are identifiable by a 'semantic element' (GS, NS, ES)
 * which serve as unique identifier of the node/edge.
 */
public abstract class Graph<GS, NS, ES, N extends GraphNode<NS, E>, E extends GraphEdge<ES, N>> {

  protected GS semantic;

  protected HashMap<NS, N> nodes = new HashMap<>();

  protected HashMap<ES, E> edges = new HashMap<>();

  public Graph(GS semantic) {
    this.semantic = semantic;
  }

  public GS getSemantic() {
    return semantic;
  }

  public Map<ES, E> getEdges() {
    return edges;
  }

  public Map<NS, N> getNodes() {
    return nodes;
  }

  public boolean hasNode(NS semantic) {
    return nodes.containsKey(semantic);
  }

  public N getOrCreateNode(NS semantic) {
    return nodes.computeIfAbsent(semantic, this::createNode);
  }

  public boolean hasEdge(ES semantic) {
    return edges.containsKey(semantic);
  }

  public E getOrCreateEdge(ES semantic) {
    return edges.computeIfAbsent(semantic, this::createEdge);
  }

  public abstract N createNode(NS semantic);

  public abstract E createEdge(ES semantic);

  /**
   * This method merges node and node2. Only a new node with given semantic remains on the graph
   */
  public N mergeNodes(N node, N node2, NS newSemantic) {
    N result = createNode(newSemantic);

    for (E e : new ArrayList<>(node.getIncomingEdges())) {
      e.setTarget(result);
    }
    for (E e : new ArrayList<>(node.getOutgoingEdges())) {
      e.setSource(result);
    }
    for (E e : new ArrayList<>(node2.getIncomingEdges())) {
      e.setTarget(result);
    }
    for (E e : new ArrayList<>(node2.getOutgoingEdges())) {
      e.setSource(result);
    }

    nodes.remove(node.semantic);
    nodes.remove(node2.semantic);
    nodes.put(newSemantic, result);
    return result;
  }

  /**
   * This method remove an edge. Nodes previously referencing the edge will no longer reference it.
   */
  public void removeEdge(E edge) {
    edge.setSource(null);
    edge.setTarget(null);
    edges.remove(edge.semantic, edge);
  }

  public String toString() {
    String n = nodes.values().stream().map(GraphNode::toString).collect(Collectors.joining("\n  "));
    String e = edges.values().stream().map(GraphEdge::toString).collect(Collectors.joining("\n  "));
    return "g(\n  " + n + "\n  " + e + "\n)";
  }

}