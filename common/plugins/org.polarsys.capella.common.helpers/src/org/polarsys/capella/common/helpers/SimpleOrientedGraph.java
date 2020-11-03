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

package org.polarsys.capella.common.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

/**
 * a class which represents a simple oriented graph, with no specified edge
 */
public class SimpleOrientedGraph<T> {
  
  protected Map<T,List<T>> graph; // keys : nodes of the graph, values : next nodes
  
  public SimpleOrientedGraph (){
    graph = new HashMap<T, List<T>>();
  }
  
  public SimpleOrientedGraph (Map<T,List<T>> graph){
    this.graph = graph;
  }
  
  public boolean isAConnectedGraph(){
    if (!graph.isEmpty()){
      T first = graph.keySet().iterator().next();
      Set<T> coloredNodes = new HashSet<T>();
      Stack<T> currentStack = new Stack<T>();
      currentStack.push(first);
      coloredNodes.add(first);
      while (!currentStack.isEmpty()){
        T currentNode = currentStack.pop();
        for (T aNeighbour : getNotOrientedNeighbours(currentNode)){
          if (!coloredNodes.contains(aNeighbour)){
            coloredNodes.add(aNeighbour);
            currentStack.push(aNeighbour);
          }
        }
      }
      for (T aNode : graph.keySet()){
        if (!coloredNodes.contains(aNode)){
          return false;
        }
      }
    }
    return true;
  }
  
  public Set<T> getNotOrientedNeighbours(T node){
    Set<T> returnedList = new HashSet<T>();
    if (graph.containsKey(node)){
      returnedList.addAll(graph.get(node));
    }
    for (Entry<T, List<T>> me : graph.entrySet()){
      if (me.getValue().contains(node)){
        returnedList.add(me.getKey());
      }
    }
    return returnedList;
  }
  
  public void addNode (T node, T neighbour){
    if (graph.containsKey(node)){
      graph.get(node).add(neighbour);
    }
    else {
      List<T> newList = new ArrayList<T>();
      newList.add(neighbour);
      graph.put(node, newList);
    }
  }
  
  public boolean isEmpty (){
    return graph.isEmpty();
  }
  
  public Set<T> getSourceNodes(){
    Set<T> returnedSet = new HashSet<T>(graph.keySet());
    for (Entry<T, List<T>> me : graph.entrySet()){
      returnedSet.removeAll(me.getValue());
    }
    return returnedSet;
  }
  
  /**
   * detect a cycle in the graph
   */
  public boolean containsACycle(T aNode, Set<T> visitedNodes) {
    Set<T> nodes = new HashSet<T>(visitedNodes);
    nodes.add(aNode);
    if (graph.containsKey(aNode)){
      for (T aNext : graph.get(aNode)){
        if (nodes.contains(aNext)){
          return true;
        }
        if (containsACycle(aNext, nodes)){
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isValid(){
    if (!isAConnectedGraph()){
      return false;
    }
    Set<T> sourceNodes = getSourceNodes();
    if (sourceNodes.isEmpty()){
      return false;
    }
    for (T aSourceNode : sourceNodes){
      if (containsACycle(aSourceNode, new HashSet<T>())){
        return false;
      }
    }
    return true;
  }

  /**
   * @return the graph
   */
  public Map<T, List<T>> getGraph() {
    return graph;
  }

}
