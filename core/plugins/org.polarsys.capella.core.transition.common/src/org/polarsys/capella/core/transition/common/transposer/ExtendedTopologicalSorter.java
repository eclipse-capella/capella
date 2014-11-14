/*******************************************************************************
 * Copyright (c) 2014 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S - initial API and implementation
 *    Thales - modification performed is into : lookForOtherBacktracks
 * 			if (edgesToBreak.isEmpty()) is fix to if (!edgesToBreak.isEmpty())
 * 
 *  
 * @see {@link org.polarsys.kitalpha.transposer.scheduler.scheduler.impl.GenericTopologicalSorter}
 * 
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.kitalpha.transposer.analyzer.graph.Edge;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.scheduler.api.ITransposerTask;
import org.polarsys.kitalpha.transposer.scheduler.scheduler.impl.GenericTopologicalSorter;

/**
 * Do a topological sort on a set of elements (a model), which we know the links
 * that cause cycles
 */
public class ExtendedTopologicalSorter {

  protected Set<Edge<?>> _backtracks;
  protected Set<Vertex<?>> _model;
  protected LinkedHashSet<Vertex<?>> _sortedModel;

  /**
   * @return the backtracks
   */
  public Set<Edge<?>> getBacktracks() {
    return _backtracks;
  }

  /**
   * @return the model
   */
  public Set<Vertex<?>> getModel() {
    return _model;
  }

  /**
   * @return the sortedModel
   */
  public LinkedHashSet<Vertex<?>> getSortedModel() {
    return _sortedModel;
  }

  /**
   * 
   */
  public void dispose() {
    _backtracks.clear();
    _model.clear();
    _sortedModel.clear();

    _backtracks = null;
    _model = null;
    _sortedModel = null;

  }

  /**
   * @param toSort_p
   * @param backtracks_p
   */
  public ExtendedTopologicalSorter(Set<Vertex<?>> toSort_p, Set<Edge<?>> backtracks_p) {
    _model = toSort_p;
    _backtracks = backtracks_p;
  }

  /**
   * Check if a {@link Vertex} is independant according to a list of other {@link Vertex}. 
   * If all the elements on which it depends (if any) are out of this list, we consider 
   * that it is independent of the list.
   * @param current the {@link Vertex} which we want to know if it is independent
   * @param toSort list of {@link Vertex} against which we want to know if the given {@link Vertex} is independent
   * @return true if independant, false otherwise.
   */
  private boolean isIndependantInTypeSet(Vertex<?> current, Set<Vertex<?>> toSort) {
    boolean independant = true;
    Iterator<Edge<?>> iterator = current.getOutgoingEdges().iterator();

    while (independant && iterator.hasNext()) {
      Edge<?> currentedge = iterator.next();

      if (_backtracks.contains(currentedge)) {
        independant = true;
      } else {
        Vertex<?> currentType = currentedge.getTarget();
        independant = (independant && !toSort.contains(currentType));
      }
    }

    return independant;
  }

  /**
   * Determines the individual elements within a set of elements
   * @param monitor_p
   * @param toSort all of the elements in which one seeks the independent
   * @return all independent elements
   */
  private Set<Vertex<?>> findIndependantsInTypeSet(Set<Vertex<?>> toSort, IProgressMonitor monitor_p) {
    Set<Vertex<?>> independants = new HashSet<Vertex<?>>();

    for (Vertex<?> currentType : toSort) {
      if (isIndependantInTypeSet(currentType, toSort)) {
        independants.add(currentType);
        if (monitor_p != null) {
          monitor_p.worked(1 / _model.size());
        }
      }
    }

    if (independants.isEmpty()) {
      lookForOtherBacktracks(toSort);
      independants = findIndependantsInTypeSet(toSort, monitor_p);
    }
    return independants;
  }

  /**
   * @param toSort_p
   */
  private void lookForOtherBacktracks(Set<Vertex<?>> toSort_p) {
    // try to break non detected cycles : list non backtracked (non critical) edges, outputs of vertices to sort.
    List<Edge<?>> nonBacktrackedEdges = new ArrayList<Edge<?>>();
    // if possible, only break edges from vertices with multiples output.
    List<Edge<?>> edgesToBreak = new ArrayList<Edge<?>>();
    for (Vertex<?> v : toSort_p) {
      List<Edge<?>> breakables = new ArrayList<Edge<?>>();
      for (Edge<?> e : v.getOutgoingEdges()) {
        if (toSort_p.contains(e.getTarget()) && !e.isCritical() && !_backtracks.contains(e)) {
          breakables.add(e);
        }
      }

      if (breakables.size() > 1) {
        edgesToBreak.addAll(breakables);
      } else {
        nonBacktrackedEdges.addAll(breakables);
      }
    }

    if (nonBacktrackedEdges.isEmpty() && edgesToBreak.isEmpty()) {
      //stackOverflow! 
    }

    if (!edgesToBreak.isEmpty()) {
      _backtracks.addAll(edgesToBreak);
    } else {
      _backtracks.addAll(nonBacktrackedEdges);
    }
  }

  /**
   *
   */
  public LinkedHashSet<Vertex<?>> sort(IProgressMonitor monitor_p) {
    if (monitor_p != null) {
      monitor_p.subTask("Topological sort"); //$NON-NLS-1$ 
    }
    _sortedModel = topologicalSort(new LinkedHashSet<Vertex<?>>(), _model, monitor_p);

    return getSortedModel();
  }

  /**
   * Method that makes the sorting of the elements (terminal recursive => the first parameter is an accumulator, it must be initialized with an empty set)
   * @param sorted accumulator (contains the processed element, in order)
   * @param toSort set of not processed elements
   * @return set of processed elements (at the end of the recursive calls)
   */
  private LinkedHashSet<Vertex<?>> topologicalSort(LinkedHashSet<Vertex<?>> sorted, Set<Vertex<?>> toSort, IProgressMonitor monitor_p) {
    Set<Vertex<?>> independants;

    while (!toSort.isEmpty()) {
      independants = findIndependantsInTypeSet(toSort, monitor_p);
      if (independants.isEmpty()) {
        throw new TransitionException(new Status(IStatus.ERROR, "Transposer cycle exception", "Transposer cycle exception"));
      }

      toSort.removeAll(independants);
      sorted.addAll(independants);
    }

    return sorted;
  }

  public List<ITransposerTask<Vertex<?>>> getWork(IProgressMonitor monitor_p) {
    GenericTopologicalSorter sorter = new GenericTopologicalSorter(getModel(), getBacktracks()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public LinkedHashSet<Vertex<?>> getSortedModel() {
        return ExtendedTopologicalSorter.this.getSortedModel();
      }

    };
    return sorter.getWork(monitor_p);
  }
}
