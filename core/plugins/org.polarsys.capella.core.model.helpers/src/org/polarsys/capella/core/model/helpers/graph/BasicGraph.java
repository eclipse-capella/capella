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

import org.polarsys.capella.core.model.helpers.graph.BasicGraph.Edge;
import org.polarsys.capella.core.model.helpers.graph.BasicGraph.Node;

/**
 * A simple graph with Nodes and Edges.
 * 
 * @see BasicGraph<String> allows to create nodes and edges identifiable by a String
 */
public class BasicGraph<T> extends Graph<T, T, T, Node<T>, Edge<T>> {

  public static class Edge<T> extends GraphEdge<T, Node<T>> {
    public Edge(T semantic) {
      super(semantic);
    }
  }

  public static class Node<T> extends GraphNode<T, Edge<T>> {
    public Node(T semantic) {
      super(semantic);
    }
  }

  public BasicGraph(T semantic) {
    super(semantic);
  }

  @Override
  public Node<T> createNode(T semantic) {
    return new Node<>(semantic);
  }

  @Override
  public Edge<T> createEdge(T semantic) {
    return new Edge<>(semantic);
  }
}
