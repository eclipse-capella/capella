/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.kitalpha.transposer.analyzer.graph.Edge;
import org.polarsys.kitalpha.transposer.analyzer.graph.Graph;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.scheduler.api.IScheduler;
import org.polarsys.kitalpha.transposer.scheduler.api.ITransposerTask;

/**
 * 
 * Nothing more than GenericScheduler. 
 * Only the topologicalSorter is different.
 *
 */
public class ExtendedScheduler implements IScheduler {

  protected ExtendedTopologicalSorter _TopologicalSorter;

  public Graph _model;

  /**
   * @return the model
   */
  public Graph getModel() {
    return _model;
  }

  /**
   * @return the topologicalScheduler
   */
  protected ExtendedTopologicalSorter getTopologicalSorter() {
    return _TopologicalSorter;
  }

  /**
   * @param model the model to set
   */
  public void setModel(Graph model) {
    _model = model;
  }

  /**
   * @param topologicalScheduler the topologicalScheduler to set
   */
  protected void setTopologicalSorter(ExtendedTopologicalSorter topologicalScheduler) {
    _TopologicalSorter = topologicalScheduler;
  }

  /**
   * Visited elements (complementary of notVisited).
   */
  private Set<Vertex<?>> _visited;

  /**
   * Not visited elements (complementary of visited).
   */
  private Set<Vertex<?>> _notVisited;

  /**
   * Collected "back" tracks that cause cycles.
   */
  private Set<Edge<?>> _backTracks;

  /**
   * Set of tasks that represents the result of the scheduling graph.
   */
  private List<ITransposerTask<Vertex<?>>> _scheduleResult;

  /**
   * Path that we are currently browsing
   */
  private List<Edge<?>> _browsedPath;

  /**
   * Set of discovered tracks in the graph.
   */
  private Set<LinkedList<Edge<?>>> _foundCycles;

  private IProgressMonitor _monitor = null;

  private int _monitorSize;

  /**
   * Default Constructor.
   */
  public ExtendedScheduler() {
    super();
    init();
  }

  /**
   * Initialize all necessary variables.
   * @param model
   */
  public ExtendedScheduler(Graph model) {
    setModel(model);
    init();
  }

  /**
   * In-depth tour which starts with "peaks" of the model that are retrieved by a call to {@ link} getAllModelSummits. Following this visit, we can consider 
   * that any element in {@link notVisited} is not helpful and did not need to be transformed.
   */
  private void depthFirstVisitGlobal() {
    List<Vertex<?>> summits = getAllModelSummits();

    for (Vertex<?> currentSummit : summits) {
      depthFirstVisitLocalWithCycleSearch(currentSummit);
    }

    identifyBacktracksFromCycles();
  }

  /**
   * In-depth tour of the graph with cycle search, from a given "peak".
   * @param currentVertex the starting peak.
   */
  private void depthFirstVisitLocalWithCycleSearch(Vertex<?> currentVertex) {

    if (_monitor != null) {
      _monitor.subTask("Visiting vertex " + currentVertex.getName()); //$NON-NLS-1$
      if (_monitorSize != 0) {
        _monitor.worked(1 / _monitorSize);
      }
    }

    List<Edge<?>> currentVertexEdges = currentVertex.getOutgoingEdges();
    for (Edge<?> currentEdge : currentVertexEdges) {
      if (!_browsedPath.contains(currentEdge)) {
        Vertex<?> nextVertex = currentEdge.getTarget();

        // if we find a cycle
        int index = indexOfVertexInPath(_browsedPath, nextVertex);
        if ((index > -1) || (nextVertex == currentVertex)) {
          LinkedList<Edge<?>> currentCycle = new LinkedList<Edge<?>>();
          if (index > -1) {
            for (int i = index; i < _browsedPath.size(); i++) {
              currentCycle.add(_browsedPath.get(i));
            }
          }
          currentCycle.add(currentEdge);

          // we store it
          if (isNewCycle(currentCycle)) {
            _foundCycles.add(currentCycle);
          }

        } else if (!isVisited(currentVertex)) {
          // otherwise we continue
          _browsedPath.add(currentEdge);
          depthFirstVisitLocalWithCycleSearch(nextVertex);
        }
      }
    }

    setVisited(currentVertex);
    // and once you have finished visiting a node (and all its descendants, we return back to 1)
    if (_browsedPath.size() > 0) {
      _browsedPath.remove(_browsedPath.size() - 1);
    }
  }

  /**
   * @param currentCycle
   * @return
   */
  private boolean isNewCycle(LinkedList<Edge<?>> currentCycle) {
    for (LinkedList<Edge<?>> alreadyKnownCycle : _foundCycles) {
      // check cycle length && composition
      if ((alreadyKnownCycle.size() == currentCycle.size()) && alreadyKnownCycle.containsAll(currentCycle)) {

        return false;
      }
    }
    return true;
  }

  /**
   * Return all hierarchically main peaks, ie all the {@link Vertex} that are "HotSpot".
   * @return
   */
  private List<Vertex<?>> getAllModelSummits() {
    List<Vertex<?>> summits = new ArrayList<Vertex<?>>();

    for (Vertex<?> vertex : _model.getVertices()) {
      if (vertex.isHotSpot()) {
        summits.add(vertex);
      }
    }
    return summits;
  }

  /**
   *
   */
  public Set<Edge<?>> getBackTracks() {
    return _backTracks;
  }

  /**
   * @return the _foundCycles
   */
  public Set<LinkedList<Edge<?>>> getFoundCycles() {
    return _foundCycles;
  }

  /**
   *
   */
  public Set<Vertex<?>> getNotVisited() {
    return _notVisited;
  }

  /**
   *
   */
  public List<ITransposerTask<Vertex<?>>> getScheduleResult() {
    return _scheduleResult;
  }

  /**
   * @return the visited
   */
  public Set<Vertex<?>> getVisited() {
    return _visited;
  }

  /**
   * Algorithm that decides in discovered cycles which are backtracks. This algorithm, for each cycle, finds a backtrack (if none of its arcs is not already in 
   * the list of backtracks). Arcs considered as critical can not be considered as backtracks, we will choose another arc.
   */
  private void identifyBacktracksFromCycles() {
    // All identified cycles are scanned
    for (LinkedList<Edge<?>> currentCycle : _foundCycles) {
      if (currentCycle.size() == 1) {
        //With Region and InvolvedState, a region have premises on involvedStates, and ownedStates have premise on ownedRegion
        for (Edge<?> edge : currentCycle) {
          if (!_backTracks.contains(edge)) {
            _backTracks.add(edge);
          }
        }
      } else if (currentCycle.size() == 2) {
        for (Edge<?> edge : currentCycle) {
          if (!_backTracks.contains(edge) && !edge.isCritical()) {
            _backTracks.add(edge);
          }
        }
      } else {
        boolean alreadyBacktracked = false;

        // Check if one of the arcs is already in backtracks 
        // If this is the case, there is nothing to do
        // Otherwise, we must determine which arc can be considered as backtrack
        for (Edge<?> edge : currentCycle) {
          if (_backTracks.contains(edge)) {
            alreadyBacktracked = true;
            break;
          }
        }

        // if none of the arcs is in backtracks
        if (!alreadyBacktracked) {
          boolean done = false;
          Iterator<Edge<?>> iterator = currentCycle.iterator();

          // take the first non-critical
          // mark it as backtrack
          while (iterator.hasNext() && (!done)) {
            Edge<?> currentEdge = iterator.next();
            if (!currentEdge.isCritical()) {
              _backTracks.add(currentEdge);
              done = true;
            }
          }

          if (!done) {
            System.out.println("Unbreakable cycle " + currentCycle.size() + " elements)"); //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
      }
    }
  }

  /**
   * Return the index of a "peak" in a path
   * @param path the path in which the "peak" is searched
   * @param vertex the searched "peak"
   * @return the index of a "peak" in the path (or -1 if not found)
   */
  private int indexOfVertexInPath(List<Edge<?>> path, Vertex<?> vertex) {
    int i = 0;
    for (Edge<?> edge : path) {
      if ((edge.getSource() == vertex)) {
        return i;
      }
      i++;
    }
    return -1;
  }

  /**
   * initializer
   */
  private void init() {
    _visited = new HashSet<Vertex<?>>();
    _notVisited = new HashSet<Vertex<?>>();
    _backTracks = new HashSet<Edge<?>>();
    _foundCycles = new HashSet<LinkedList<Edge<?>>>();
    _browsedPath = new ArrayList<Edge<?>>();
    _scheduleResult = new LinkedList<ITransposerTask<Vertex<?>>>();
  }

  public void dispose() {
    _visited = null;
    _notVisited = null;
    _backTracks = null;
    _foundCycles = null;
    _browsedPath = null;
    _scheduleResult = null;
    _monitor = null;
  }

  /**
   * Return true is the node has already been visited (if not, it is notTransposable)
   * @param t The current {@link Vertex}
   * @return true is the {@link Vertex} has already been visited, false otherwise
   */
  private boolean isVisited(Vertex<?> t) {
    return _visited.contains(t);
  }

  /**
   * Remove all graph elements from the visited list, and add all of them in the "not visited" list.
   */
  private void markAllAsNotVisited() {
    _notVisited.addAll(_model.getVertices());
    _visited.clear();
  }

  /**
   *
   */
  public void schedule(Comparator<Vertex<?>> comparator, IProgressMonitor monitor) {
    init();

    /* mark elements as not visited */
    markAllAsNotVisited();

    if (monitor != null) {
      _monitor = monitor;
      _monitorSize = _notVisited.size();
      monitor.beginTask("Transposer Scheduling", 3); //$NON-NLS-1$
      monitor.subTask("Cycle search"); //$NON-NLS-1$
    }

    /*
     * Launch In-depth tour:
     * <UL>
     * <LI>Identify the "topological" vertices of the model (hot spot) </LI>
     * <LI>Descends recursively into the model from these nodes</LI>
     * <LI>Doing it identifies backtracks</LI>
     */
    depthFirstVisitGlobal();

    /*
     * Now, all backtracks have been discovered and are stored in BackTRacks. The topological sort is run, with a list of links to ignore. 
     * Once we have an order of the elements to be addressed, backtracks are concatenated. We keep the CycleWeak release that allows to 
     * captures an exception in an unfavorable case.
     */
    try {
      setTopologicalSorter(new ExtendedTopologicalSorter(_visited, _backTracks));
      getTopologicalSorter().sort(monitor);
      _scheduleResult = getTopologicalSorter().getWork(monitor);
      getTopologicalSorter().dispose();
    } catch (TransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Mark a vertex as visited.
   * @param t Current {@link Vertex}
   */
  private void setVisited(Vertex<?> t) {
    if (!isVisited(t)) {
      _visited.add(t);
      _notVisited.remove(t);
    }
  }

}
