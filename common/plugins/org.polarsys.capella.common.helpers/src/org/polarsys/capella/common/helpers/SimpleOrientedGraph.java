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
  
  public SimpleOrientedGraph (Map<T,List<T>> graph_p){
    graph = graph_p;
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
  
  public Set<T> getNotOrientedNeighbours(T node_p){
    Set<T> returnedList = new HashSet<T>();
    if (graph.containsKey(node_p)){
      returnedList.addAll(graph.get(node_p));
    }
    for (Entry<T, List<T>> me : graph.entrySet()){
      if (me.getValue().contains(node_p)){
        returnedList.add(me.getKey());
      }
    }
    return returnedList;
  }
  
  public void addNode (T node_p, T neighbour_p){
    if (graph.containsKey(node_p)){
      graph.get(node_p).add(neighbour_p);
    }
    else {
      List<T> newList = new ArrayList<T>();
      newList.add(neighbour_p);
      graph.put(node_p, newList);
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
  public boolean containsACycle(T aNode_p, Set<T> visitedNodes_p){
    Set<T> visitedNodes = new HashSet<T>(visitedNodes_p);
    visitedNodes.add(aNode_p);
    if (graph.containsKey(aNode_p)){
      for (T aNext : graph.get(aNode_p)){
        if (visitedNodes.contains(aNext)){
          return true;
        }
        if (containsACycle(aNext, visitedNodes)){
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
