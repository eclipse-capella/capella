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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * An implementation of Tarjan's algorithm to find the strongly connected components of a graph. This is similar (but not 100% the same) to finding cycles of a
 * graph. http://en.wikipedia.org/wiki/Strongly_connected_components http://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
 */
public class SCCSearch<T> {

  /**
   * Properties assigned to each node
   */
  private final class Properties {
    public Properties(int index, int lowlink) {
      this.index = index;
      this.lowlink = lowlink;
    }

    int index; // index at which the corresponding element was discovered
    int lowlink; // minimal index for some other node reachable from this one
  }

  /*
   * The graph on which I operate
   */
  private IDirectedGraph<T> graph;

  /*
   * Current index
   */
  private int index;

  /*
   * Stores properties of nodes that have already been visited
   */
  private Map<T, Properties> propertyMap;

  /*
   * The currently inspected strongly connected component on a stack
   */
  private Stack<T> stack;

  /*
   * Stores the result
   */
  private List<List<T>> result;

  /**
   * Find the strongly connected components of a graph.
   * @param contents An iterator over the graph's nodes.
   * @param advisor Provides informations about the graphs edges.
   * @param strict whether elementary (size 1) sccs should be added to the result
   * @return A list of strongly connected components of the graph. Never null.
   */
  public List<List<T>> findSCC(IDirectedGraph<T> graph, boolean strict) {

    result = new ArrayList<List<T>>();
    this.graph = graph;
    index = 0;
    propertyMap = new HashMap<T, Properties>();
    stack = new Stack<T>();
    result = new ArrayList<List<T>>();

    // make sure to hit all nodes
    Iterator<T> nodes = graph.getNodes();
    while (nodes.hasNext()) {
      T current = nodes.next();
      if (propertyMap.get(current) == null) {
        strongconnect(current, strict);
      }
    }

    return result;
  }

  private void strongconnect(T current, boolean strict) {
    Properties properties = new Properties(index, index);
    propertyMap.put(current, properties);
    index++;
    stack.push(current);
    Iterator<T> successors = graph.getSucessors(current);
    while (successors.hasNext()) {
      T successor = successors.next();
      if (propertyMap.get(successor) == null) {
        // have not seen successor before
        strongconnect(successor, strict);
        properties.lowlink = Math.min(properties.lowlink, propertyMap.get(successor).lowlink);
      } else if (stack.contains(successor)) {
        // seen successor in the currently handled scc
        properties.lowlink = Math.min(properties.lowlink, propertyMap.get(successor).lowlink);
      }
    }

    if (properties.lowlink == properties.index) {
      List<T> scc = new ArrayList<T>();
      T t;
      do {
        t = stack.pop();
        scc.add(t);
      } while (t != current);
      if (scc.size() == 1) {
        if (strict) {
          result.add(scc);
        }
      } else {
        result.add(scc);
      }
    }
  }

}
