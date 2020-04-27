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
package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.utils.graph.CycleDetectionUtils;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.kitalpha.transposer.analyzer.graph.Edge;
import org.polarsys.kitalpha.transposer.analyzer.graph.Graph;
import org.polarsys.kitalpha.transposer.analyzer.graph.GraphElement;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.scheduler.api.IScheduler;
import org.polarsys.kitalpha.transposer.scheduler.api.ITransposerTask;

/**
 * 
 * Nothing more than GenericScheduler. Only the topologicalSorter is different.
 *
 */
public class ExtendedScheduler implements IScheduler {

  protected ExtendedTopologicalSorter topologicalSorter;

  public Graph model;

  /**
   * @return the model
   */
  @Override
  public Graph getModel() {
    return model;
  }

  /**
   * @return the topologicalScheduler
   */
  protected ExtendedTopologicalSorter getTopologicalSorter() {
    return topologicalSorter;
  }

  /**
   * @param model
   *          the model to set
   */
  @Override
  public void setModel(Graph model) {
    this.model = model;
  }

  /**
   * @param topologicalScheduler
   *          the topologicalScheduler to set
   */
  protected void setTopologicalSorter(ExtendedTopologicalSorter topologicalScheduler) {
    this.topologicalSorter = topologicalScheduler;
  }

  /**
   * Visited elements (complementary of notVisited).
   */
  private Set<Vertex<?>> visited;

  /**
   * Not visited elements (complementary of visited).
   */
  private Set<Vertex<?>> notVisited;

  /**
   * Collected "back" tracks that cause cycles.
   */
  private Set<Edge<?>> backTracks;

  /**
   * Set of tasks that represents the result of the scheduling graph.
   */
  private List<ITransposerTask<Vertex<?>>> scheduleResult;

  /**
   * Set of discovered tracks in the graph.
   */
  private Set<LinkedList<Edge<?>>> foundCycles;

  /**
   * Default Constructor.
   */
  public ExtendedScheduler() {
    super();
    init();
  }

  /**
   * Initialize all necessary variables.
   * 
   * @param modelp
   */
  public ExtendedScheduler(Graph modelp) {
    setModel(modelp);
    init();
  }

  /**
   * In-depth tour which starts with "peaks" of the model that are retrieved by a call to {@ link} getAllModelSummits.
   * Following this visit, we can consider that any element in {@link notVisited} is not helpful and did not need to be
   * transformed.
   */
  private void depthFirstVisitGlobal() {
    final List<GraphElement<?>> summits = getAllModelSummits();

    IDirectedGraph<GraphElement<?>> iDirectedGraph = new IDirectedGraph<GraphElement<?>>() {

      @Override
      public Iterator<GraphElement<?>> getSucessors(GraphElement<?> source) {
        if (source instanceof Vertex<?>) {

          setVisited((Vertex<?>) source);
          ArrayList<GraphElement<?>> arrayList = new ArrayList<GraphElement<?>>();
          arrayList.addAll(((Vertex<?>) source).getOutgoingEdges());

          return arrayList.iterator();
        }
        Vertex<?> target = ((Edge<?>) source).getTarget();
        Iterator<?> iterator = Collections.singletonList(target).iterator();

        return (Iterator<GraphElement<?>>) iterator;
      }

      @Override
      public Iterator<GraphElement<?>> getNodes() {
        return summits.iterator();
      }
    };

    List<List<GraphElement<?>>> cycles = CycleDetectionUtils.getCycles(iDirectedGraph);

    for (List<GraphElement<?>> cycle : cycles) {
      LinkedList<Edge<?>> edgeCycle = new LinkedList<Edge<?>>();
      for (GraphElement<?> graphElement : cycle) {
        if (graphElement instanceof Edge<?>) {
          edgeCycle.add((Edge<?>) graphElement);
        }
      }
      if (!edgeCycle.isEmpty()) {
        foundCycles.add(edgeCycle);

      }
    }

    identifyBacktracksFromCycles();
  }

  /**
   * Return all hierarchically main peaks, ie all the {@link Vertex} that are "HotSpot".
   * 
   * @return
   */
  private List<GraphElement<?>> getAllModelSummits() {
    List<GraphElement<?>> summits = new ArrayList<GraphElement<?>>();

    for (Vertex<?> vertex : model.getVertices()) {
      if (vertex.isHotSpot()) {
        summits.add(vertex);
      }
    }
    return summits;
  }

  /**
   *
   */
  @Override
  public Set<Edge<?>> getBackTracks() {
    return backTracks;
  }

  /**
   * @return the foundCycles
   */
  public Set<LinkedList<Edge<?>>> getFoundCycles() {
    return foundCycles;
  }

  /**
   *
   */
  @Override
  public Set<Vertex<?>> getNotVisited() {
    return notVisited;
  }

  /**
   *
   */
  @Override
  public List<ITransposerTask<Vertex<?>>> getScheduleResult() {
    return scheduleResult;
  }

  /**
   * @return the visited
   */
  @Override
  public Set<Vertex<?>> getVisited() {
    return visited;
  }

  /**
   * Algorithm that decides in discovered cycles which are backtracks. This algorithm, for each cycle, finds a backtrack
   * (if none of its arcs is not already in the list of backtracks). Arcs considered as critical can not be considered
   * as backtracks, we will choose another arc.
   */
  private void identifyBacktracksFromCycles() {
    // All identified cycles are scanned
    for (LinkedList<Edge<?>> currentCycle : foundCycles) {
      if (currentCycle.size() == 1) {
        // With Region and InvolvedState, a region have premises on involvedStates, and ownedStates have premise on
        // ownedRegion
        for (Edge<?> edge : currentCycle) {
          if (!backTracks.contains(edge)) {
            backTracks.add(edge);
          }
        }
      } else if (currentCycle.size() == 2) {
        for (Edge<?> edge : currentCycle) {
          if (!backTracks.contains(edge) && !edge.isCritical()) {
            backTracks.add(edge);
          }
        }
      } else {
        boolean alreadyBacktracked = false;

        // Check if one of the arcs is already in backtracks
        // If this is the case, there is nothing to do
        // Otherwise, we must determine which arc can be considered as backtrack
        for (Edge<?> edge : currentCycle) {
          if (backTracks.contains(edge)) {
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
              backTracks.add(currentEdge);
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
   * initializer
   */
  private void init() {
    visited = new HashSet<Vertex<?>>();
    notVisited = new HashSet<Vertex<?>>();
    backTracks = new HashSet<Edge<?>>();
    foundCycles = new HashSet<LinkedList<Edge<?>>>();
    scheduleResult = new LinkedList<ITransposerTask<Vertex<?>>>();
  }

  @Override
  public void dispose() {
    visited = null;
    notVisited = null;
    backTracks = null;
    foundCycles = null;
    scheduleResult = null;
  }

  /**
   * Return true is the node has already been visited (if not, it is notTransposable)
   * 
   * @param t
   *          The current {@link Vertex}
   * @return true is the {@link Vertex} has already been visited, false otherwise
   */
  private boolean isVisited(Vertex<?> t) {
    return visited.contains(t);
  }

  /**
   * Remove all graph elements from the visited list, and add all of them in the "not visited" list.
   */
  private void markAllAsNotVisited() {
    notVisited.addAll(model.getVertices());
    visited.clear();
  }

  /**
   *
   */
  @Override
  public void schedule(Comparator<Vertex<?>> comparator, IProgressMonitor monitor) {
    init();

    /* mark elements as not visited */
    markAllAsNotVisited();

    if (monitor != null) {
      monitor.beginTask("Transposer Scheduling", 3); //$NON-NLS-1$
      monitor.subTask("Cycle search"); //$NON-NLS-1$
    }

    /*
     * Launch In-depth tour: <UL> <LI>Identify the "topological" vertices of the model (hot spot) </LI> <LI>Descends
     * recursively into the model from these nodes</LI> <LI>Doing it identifies backtracks</LI>
     */
    depthFirstVisitGlobal();

    /*
     * Now, all backtracks have been discovered and are stored in BackTRacks. The topological sort is run, with a list
     * of links to ignore. Once we have an order of the elements to be addressed, backtracks are concatenated. We keep
     * the CycleWeak release that allows to captures an exception in an unfavorable case.
     */
    HashSet<Vertex<?>> vertices = new HashSet<Vertex<?>>();
    vertices.addAll(model.getVertices());
    try {
      setTopologicalSorter(new ExtendedTopologicalSorter(vertices, backTracks));
      getTopologicalSorter().sort(monitor);
      scheduleResult = getTopologicalSorter().getWork(monitor);
      getTopologicalSorter().dispose();
    } catch (TransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Mark a vertex as visited.
   * 
   * @param t
   *          Current {@link Vertex}
   */
  private void setVisited(Vertex<?> t) {
    if (!isVisited(t)) {
      visited.add(t);
      notVisited.remove(t);
    }
  }

}
