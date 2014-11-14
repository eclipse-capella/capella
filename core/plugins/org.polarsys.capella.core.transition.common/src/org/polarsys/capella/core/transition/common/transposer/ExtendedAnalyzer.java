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

  protected Graph _modelGraph;
  protected IRulesHandler _rulesHandler;
  private Map _graphHashMap;

  public ExtendedAnalyzer(IRulesHandler rulesHandler_p) {
    _modelGraph = GraphFactory.eINSTANCE.createGraph();
    _rulesHandler = rulesHandler_p;
    _graphHashMap = new HashMap();
  }

  public Graph getModelGraph() {
    return _modelGraph;
  }

  public Graph analyze(Collection analysisSource_p, Collection selection_p, IProgressMonitor monitor_p) throws AnalysisException {
    if (monitor_p != null) {
      monitor_p.beginTask("Transposer Analysis", analysisSource_p.size());
    }
    for (Iterator iterator = analysisSource_p.iterator(); iterator.hasNext();) {
      Object source = iterator.next();
      try {
        if (_rulesHandler.getApplicablePossibility(source) != null) {
          createVertexForType(source, monitor_p);
          createSubGraphForType(source, monitor_p);
          if (monitor_p != null) {
            monitor_p.worked(1);
          }
        }
      } catch (ComputePremisesException e) {
        throw new AnalysisException(e);
      } catch (MappingPossibilityResolutionException e) {
        throw new AnalysisException(e);
      }
    }

    if (monitor_p != null) {
      monitor_p.subTask("");
    }
    return _modelGraph;
  }

  private void createVertexForType(Object currentType_p, IProgressMonitor monitor_p) {
    if (graphHasAlreadyVertex(currentType_p)) {
      return;
    }
    if (monitor_p != null) {
      monitor_p.subTask((new StringBuilder("Creating vertex for ")).append(currentType_p.getClass().getSimpleName()).toString());
      monitor_p.worked(1);
    }
    String name = _rulesHandler.getDomainHelper().getName(currentType_p);
    if ((name == null) || "".equals(name)) {
      name = currentType_p.getClass().getName();
    }
    boolean isHotSpot = _rulesHandler.getDomainHelper().isHotSpot(currentType_p);
    Vertex newVertex = GraphFactory.eINSTANCE.createVertex();
    newVertex.setContent(currentType_p);
    newVertex.setName(name);
    newVertex.setHotSpot(isHotSpot);
    _graphHashMap.put(currentType_p, newVertex);
    _modelGraph.addVertex(newVertex);
  }

  private boolean graphHasAlreadyVertex(Object currentType_p) {
    return _graphHashMap.containsKey(currentType_p);
  }

  private void createSubGraphForType(Object currentType_p, IProgressMonitor monitor_p) throws ComputePremisesException {
    Vertex currentVertex = (Vertex) _graphHashMap.get(currentType_p);

    LinkedList<Object> toVisit = new LinkedList<Object>();
    toVisit.add(currentType_p);

    while (!toVisit.isEmpty()) {
      Object currentType = toVisit.removeFirst();
      currentVertex = (Vertex) _graphHashMap.get(currentType);

      List needed = null;
      needed = _rulesHandler.getPremises(currentType);
      if (needed == null) {
        return;
      }
      if (monitor_p != null) {
        monitor_p.subTask((new StringBuilder("Creating subgraph for ")).append(currentType.getClass().getSimpleName()).toString());
      }

      for (Iterator iterator = needed.iterator(); iterator.hasNext();) {
        IPremise premise = (IPremise) iterator.next();

        if (premise instanceof PrecedencePremise) {
          PrecedencePremise precedentePremise = (PrecedencePremise) premise;

          boolean isCriticalDependency = false;
          boolean createdVertex = false;
          Object dependingObject = precedentePremise.getFirstElement();
          String dependingObjectDescription = precedentePremise.getSecondElement();
          Vertex dependingVertex = (Vertex) _graphHashMap.get(dependingObject);
          if (dependingVertex == null) {
            createVertexForType(dependingObject, monitor_p);
            dependingVertex = (Vertex) _graphHashMap.get(dependingObject);
            createdVertex = true;
          }
          isCriticalDependency = precedentePremise.isCritical();
          Edge edge = currentVertex.getOutgoingEdgeTo(dependingVertex);
          if ((edge != null) && !edge.isCritical() && isCriticalDependency) {
            edge.setCritical(true);
          } else if (edge == null) {

            _modelGraph.addAdjacent(currentVertex, dependingVertex, dependingObjectDescription, isCriticalDependency);
          }

          if (createdVertex) {
            toVisit.addLast(dependingObject);
          }
        }

      }
    }

  }

  public void dispose() {
    _graphHashMap.clear();
    _graphHashMap = null;
    _modelGraph = null;
    _rulesHandler = null;
  }

}
