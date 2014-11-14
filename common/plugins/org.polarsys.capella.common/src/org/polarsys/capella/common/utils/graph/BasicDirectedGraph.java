/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.utils.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple implementation of DirectedGraph
 */
public class BasicDirectedGraph<T> implements IDirectedGraph<T> {

  private Map<T, List<T>> edges = new HashMap<T, List<T>>();

  /**
   * {@inheritDoc}
   */
  public Iterator<T> getSucessors(T source_p) {
    Iterator<T> successors;
    List<T> successorList = edges.get(source_p);
    if (successorList == null) {
      successorList = Collections.emptyList();
      successors = successorList.iterator();
    } else {
      successors = successorList.iterator();
    }
    return successors;
  }

  /**
   * Add a new edge to the graph. Adding an edge automatically adds the source and target nodes to the graph. You don't need to explicitly call addNode in this
   * case.
   * @param source_p
   * @param successor_p
   */
  public void addEdge(T source_p, T successor_p) {
    List<T> sucessors = edges.get(source_p);
    if (sucessors == null) {
      sucessors = new ArrayList<T>();
      edges.put(source_p, sucessors);
    }
    sucessors.add(successor_p);

    // store successors as map keys for getNodes()
    edges.put(successor_p, edges.get(successor_p));
  }

  /**
   * Add a node to the graph.
   * @param node_p
   */
  public void addNode(T node_p) {
    edges.put(node_p, edges.get(node_p));
  }

  /**
   * {@inheritDoc}
   */
  public Iterator<T> getNodes() {
    return edges.keySet().iterator();
  }

}
