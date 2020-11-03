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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.kitalpha.transposer.analyzer.api.IAnalyzer;
import org.polarsys.kitalpha.transposer.analyzer.exceptions.AnalysisException;
import org.polarsys.kitalpha.transposer.analyzer.graph.Edge;
import org.polarsys.kitalpha.transposer.analyzer.graph.Graph;
import org.polarsys.kitalpha.transposer.analyzer.graph.GraphFactory;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.business.premises.PrecedencePremise;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.ComputePremisesException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ExtendedAnalyzer implements IAnalyzer {

  protected Graph modelGraph;
  protected IRulesHandler rulesHandler;
  private Map graphHashMap;

  public ExtendedAnalyzer(IRulesHandler rulesHandler) {
    this.modelGraph = GraphFactory.eINSTANCE.createGraph();
    this.rulesHandler = rulesHandler;
    this.graphHashMap = new HashMap();
  }

  public Graph getModelGraph() {
    return modelGraph;
  }

  public Graph analyze(Collection analysisSource, Collection selection, IProgressMonitor monitor) throws AnalysisException {
    if (monitor != null) {
      monitor.beginTask("Transposer Analysis", analysisSource.size());
    }
    for (Iterator iterator = analysisSource.iterator(); iterator.hasNext();) {
      Object source = iterator.next();
      try {
        if (rulesHandler.getApplicablePossibility(source) != null) {
          createVertexForType(source, monitor);
          createSubGraphForType(source, monitor);
          if (monitor != null) {
            monitor.worked(1);
          }
        }
      } catch (ComputePremisesException e) {
        throw new AnalysisException(e);
      } catch (MappingPossibilityResolutionException e) {
        throw new AnalysisException(e);
      }
    }

    if (monitor != null) {
      monitor.subTask("");
    }
    return modelGraph;
  }

  private void createVertexForType(Object currentType, IProgressMonitor monitor) {
    if (graphHasAlreadyVertex(currentType)) {
      return;
    }
    if (monitor != null) {
      monitor.subTask((new StringBuilder("Creating vertex for ")).append(currentType.getClass().getSimpleName()).toString());
      monitor.worked(1);
    }
    String name = rulesHandler.getDomainHelper().getName(currentType);
    if ((name == null) || "".equals(name)) {
      name = currentType.getClass().getName();
    }
    boolean isHotSpot = rulesHandler.getDomainHelper().isHotSpot(currentType);
    Vertex newVertex = GraphFactory.eINSTANCE.createVertex();
    newVertex.setContent(currentType);
    newVertex.setName(name);
    newVertex.setHotSpot(isHotSpot);
    graphHashMap.put(currentType, newVertex);
    modelGraph.addVertex(newVertex);
  }

  private boolean graphHasAlreadyVertex(Object currentType) {
    return graphHashMap.containsKey(currentType);
  }

  private void createSubGraphForType(Object object1, IProgressMonitor iProgressMonitor1) throws ComputePremisesException {
    Vertex currentVertex = (Vertex) graphHashMap.get(object1);

    LinkedList<Object> toVisit = new LinkedList<Object>();
    toVisit.add(object1);

    while (!toVisit.isEmpty()) {
      Object currentType = toVisit.removeFirst();
      currentVertex = (Vertex) graphHashMap.get(currentType);

      List needed = null;
      needed = rulesHandler.getPremises(currentType);
      if (needed == null) {
        return;
      }
      if (iProgressMonitor1 != null) {
        iProgressMonitor1.subTask((new StringBuilder("Creating subgraph for ")).append(currentType.getClass().getSimpleName()).toString());
      }

      for (Iterator iterator = needed.iterator(); iterator.hasNext();) {
        IPremise premise = (IPremise) iterator.next();

        if (premise instanceof PrecedencePremise) {
          PrecedencePremise precedentePremise = (PrecedencePremise) premise;

          boolean isCriticalDependency = false;
          boolean createdVertex = false;
          Object dependingObject = precedentePremise.getFirstElement();
          String dependingObjectDescription = precedentePremise.getSecondElement();
          Vertex dependingVertex = (Vertex) graphHashMap.get(dependingObject);
          if (dependingVertex == null) {
            createVertexForType(dependingObject, iProgressMonitor1);
            dependingVertex = (Vertex) graphHashMap.get(dependingObject);
            createdVertex = true;
          }
          isCriticalDependency = precedentePremise.isCritical();
          Edge edge = currentVertex.getOutgoingEdgeTo(dependingVertex);
          if ((edge != null) && !edge.isCritical() && isCriticalDependency) {
            edge.setCritical(true);
          } else if (edge == null) {

            modelGraph.addAdjacent(currentVertex, dependingVertex, dependingObjectDescription, isCriticalDependency);
          }

          if (createdVertex) {
            toVisit.addLast(dependingObject);
          }
        }

      }
    }

  }

  public void dispose() {
    graphHashMap.clear();
    graphHashMap = null;
    modelGraph = null;
    rulesHandler = null;
  }

}
