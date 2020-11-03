/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public Iterator<T> getSucessors(T source) {
    Iterator<T> successors;
    List<T> successorList = edges.get(source);
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
   * @param source
   * @param successor
   */
  public void addEdge(T source, T successor) {
    List<T> sucessors = edges.get(source);
    if (sucessors == null) {
      sucessors = new ArrayList<T>();
      edges.put(source, sucessors);
    }
    sucessors.add(successor);

    // store successors as map keys for getNodes()
    edges.put(successor, edges.get(successor));
  }

  /**
   * Add a node to the graph.
   * @param node
   */
  public void addNode(T node) {
    edges.put(node, edges.get(node));
  }

  /**
   * {@inheritDoc}
   */
  public Iterator<T> getNodes() {
    return edges.keySet().iterator();
  }

}
