/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.Iterator;
import java.util.List;

/**
 * Cycle detection util class on top of SCCSearch algorithm classes.
 * Notice that you can also use SimpleOrientedGraph class if
 * you are sure that you can construct a static graph without entering in a cycle while constructing it.
 * What is nice of first way is that you define how to get
 * the successors and the cycle is computed while processing this.
 * You can also get SimpleOrientedGraph static construction way if you use BasicDirectGraph
 * implementation of IDirectedGraph.
 */
public class CycleDetectionUtils {

  /**
   * Just check if we found at least one cycle. Use getCycles instead if you are going to process the cycles after the detection, for example to create a
   * message.
   * @param <T>
   * @param graph
   * @return true if at least one cycle found
   */
  public static <T> boolean containsCycles(IDirectedGraph<T> graph) {
    // Get cycles
    List<List<T>> list = getCycles(graph);
    // Check if the algorithm found at least one
    if ((list != null) && !list.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Get cycles of the graph
   * @param <T>
   * @param graph
   * @return the list of cycles found
   */
  public static <T> List<List<T>> getCycles(IDirectedGraph<T> graph) {
    // We have two parts here because of SCCSearch algorithm "strict" boolean parameter
    // For this algorithm a node has always a cycle with itself even if it does not have a link/sucessor to itself.
    // If we set strict to false then we don't add this type of cycles but we don't compute the direct link/succesor to itself that normally we want to count it
    // as cycle.

    List<List<T>> list = new ArrayList<List<T>>();
    // First part. Direct references to each node with itself
    Iterator<T> nodes = graph.getNodes();
    while (nodes.hasNext()) {
      T node = nodes.next();
      Iterator<T> succesors = graph.getSucessors(node);
      while (succesors.hasNext()) {
        if (node.equals(succesors.next())) {
          List<T> directReferenceCycle = new ArrayList<T>();
          directReferenceCycle.add(node);
          list.add(directReferenceCycle);
        }
      }
    }

    // Second part. Now we compute the SCCSearch
    SCCSearch<T> sccSearch = new SCCSearch<T>();
    List<List<T>> sccSearchResult = sccSearch.findSCC(graph, false);
    list.addAll(sccSearchResult);

    return list;
  }

}
