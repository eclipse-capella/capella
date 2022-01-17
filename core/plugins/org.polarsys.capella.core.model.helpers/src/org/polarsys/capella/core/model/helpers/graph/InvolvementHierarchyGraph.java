/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;

/**
 * An Involvement graph data structure that combines semantic and hierarchy context information to accurately model the
 * relationships between the different involvements in a functional chain.
 *
 * Using only the semantic information would raise false positive for cycles between involvements.
 */
public class InvolvementHierarchyGraph {

  private static final Logger LOGGER = Logger.getLogger(InvolvementHierarchyGraph.class);

  protected FunctionalChain rootFunctionalChain;
  protected Map<VertexKey, Vertex> vertices;

  public InvolvementHierarchyGraph(FunctionalChain rootFunctionalChain) {
    this.rootFunctionalChain = rootFunctionalChain;
    this.vertices = new HashMap<>();

    constructGraph();
  }

  protected void constructGraph() {
    LinkedList<FunctionalChainReference> referenceHierarchy = new LinkedList<>();
    EList<FunctionalChainInvolvement> childrenFunctionalChainInvolvements = rootFunctionalChain
        .getOwnedFunctionalChainInvolvements();

    for (FunctionalChainInvolvement childInvolvement : childrenFunctionalChainInvolvements) {
      extractAllVertices(childInvolvement, referenceHierarchy);
    }

    for (FunctionalChainInvolvement childInvolvement : childrenFunctionalChainInvolvements) {
      extractAllEdges(childInvolvement, referenceHierarchy);
    }
  }

  public Map<VertexKey, Vertex> getVertices() {
    return vertices;
  }

  protected void extractAllVertices(FunctionalChainInvolvement current,
      LinkedList<FunctionalChainReference> currentReferenceHierarchy) {

    if (current instanceof FunctionalChainInvolvementFunction) {
      FunctionalChainInvolvementFunction function = (FunctionalChainInvolvementFunction) current;

      boolean created = createVertex(function, currentReferenceHierarchy);

      if (!created) {
        LOGGER.warn("Vertex already exists: [" + function + " " + currentReferenceHierarchy + "]");
      }

    } else if (current instanceof FunctionalChainReference) {
      FunctionalChainReference reference = (FunctionalChainReference) current;
      FunctionalChain chain = reference.getReferencedFunctionalChain();
      EList<FunctionalChainInvolvement> childrenFunctionalChainInvolvements = chain
          .getOwnedFunctionalChainInvolvements();

      currentReferenceHierarchy.addFirst(reference);

      for (FunctionalChainInvolvement childInvolvement : childrenFunctionalChainInvolvements) {
        extractAllVertices(childInvolvement, currentReferenceHierarchy);
      }

      currentReferenceHierarchy.removeFirst();
    }

  }

  protected void extractAllEdges(FunctionalChainInvolvement current,
      LinkedList<FunctionalChainReference> currentReferenceHierarchy) {

    if (current instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) current;

      Vertex sourceVertex = getSourceVertex(currentReferenceHierarchy, link);
      Vertex targetVertex = getTargetVertex(currentReferenceHierarchy, link);

      if (sourceVertex != null && targetVertex != null) {
        createEdge(link, sourceVertex, targetVertex);
      } else {
        LOGGER.warn(
            "Either source or target vertices are null: [" + link + " " + sourceVertex + " " + targetVertex + "]");
      }
    } else if (current instanceof FunctionalChainReference) {
      FunctionalChainReference reference = (FunctionalChainReference) current;
      FunctionalChain chain = reference.getReferencedFunctionalChain();
      EList<FunctionalChainInvolvement> childrenFunctionalChainInvolvements = chain
          .getOwnedFunctionalChainInvolvements();

      currentReferenceHierarchy.addFirst(reference);

      for (FunctionalChainInvolvement childInvolvement : childrenFunctionalChainInvolvements) {
        extractAllEdges(childInvolvement, currentReferenceHierarchy);
      }

      currentReferenceHierarchy.removeFirst();
    }
  }

  protected boolean createVertex(FunctionalChainInvolvementFunction function,
      List<FunctionalChainReference> referenceHierarchy) {

    List<FunctionalChainReference> functionHierarchy = new ArrayList<>(referenceHierarchy);
    VertexKey vertexKey = new VertexKey(function, functionHierarchy);

    if (vertices.containsKey(vertexKey)) {
      return false;
    }

    Vertex vertex = new Vertex(function, functionHierarchy);
    vertices.put(vertexKey, vertex);

    return true;
  }

  protected Vertex getVertex(FunctionalChainInvolvementFunction function,
      List<FunctionalChainReference> functionReferenceHierarchy) {
    VertexKey vertexKey = new VertexKey(function, functionReferenceHierarchy);

    return vertices.get(vertexKey);
  }

  protected void createEdge(FunctionalChainInvolvementLink link, Vertex sourceVertex, Vertex targetVertex) {
    Edge edge = new Edge(link, sourceVertex, targetVertex);
    sourceVertex.outgoingEdges.add(edge);
    targetVertex.incomingEdges.add(edge);
  }

  protected Vertex getTargetVertex(LinkedList<FunctionalChainReference> currentReferenceHierarchy,
      FunctionalChainInvolvementLink link) {

    List<FunctionalChainReference> linkTargetReferenceHierarchy = link.getTargetReferenceHierarchy();
    FunctionalChainInvolvementFunction targetFunction = link.getTarget();
    List<FunctionalChainReference> targetFunctionReferenceHierarchy = combineHierarchies(linkTargetReferenceHierarchy,
        currentReferenceHierarchy);

    return getVertex(targetFunction, targetFunctionReferenceHierarchy);
  }

  protected Vertex getSourceVertex(LinkedList<FunctionalChainReference> currentReferenceHierarchy,
      FunctionalChainInvolvementLink link) {

    List<FunctionalChainReference> linkSourceReferenceHierarchy = link.getSourceReferenceHierarchy();
    FunctionalChainInvolvementFunction sourceFunction = link.getSource();
    List<FunctionalChainReference> sourceFunctionReferenceHierarchy = combineHierarchies(linkSourceReferenceHierarchy,
        currentReferenceHierarchy);

    return getVertex(sourceFunction, sourceFunctionReferenceHierarchy);
  }

  protected List<FunctionalChainReference> combineHierarchies(List<FunctionalChainReference> linkReferenceHierarchy,
      List<FunctionalChainReference> contextReferenceHierarchy) {

    List<FunctionalChainReference> resultHierarchy = new ArrayList<>(linkReferenceHierarchy);
    resultHierarchy.addAll(contextReferenceHierarchy);

    return resultHierarchy;
  }

  /**
   * Detects the presence of any cycles in the graph, by using a recursive DFS with O(V + E) complexity.
   */
  public boolean hasCycle() {
    Set<Vertex> visited = new HashSet<>();
    Set<Vertex> path = new HashSet<>();

    for (Vertex vertex : vertices.values()) {
      if (hasCycle(vertex, visited, path)) {
        return true;
      }
    }

    return false;
  }

  protected boolean hasCycle(Vertex current, Set<Vertex> visited, Set<Vertex> path) {
    if (path.contains(current)) {
      return true;
    }

    if (visited.contains(current)) {
      return false;
    }

    visited.add(current);

    path.add(current);

    for (Edge edge : current.outgoingEdges) {
      Vertex target = edge.target;
      if (hasCycle(target, visited, path)) {
        return true;
      }
    }

    path.remove(current);

    return false;
  }

  public static class VertexKey {
    protected FunctionalChainInvolvementFunction function;
    protected List<FunctionalChainReference> referenceHierarchy;

    public VertexKey(FunctionalChainInvolvementFunction function, List<FunctionalChainReference> referenceHierarchy) {
      this.function = function;
      this.referenceHierarchy = referenceHierarchy;
    }

    public FunctionalChainInvolvementFunction getFunction() {
      return function;
    }

    @Override
    public int hashCode() {
      return Objects.hash(function, referenceHierarchy);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof VertexKey)) {
        return false;
      }
      VertexKey other = (VertexKey) obj;
      return Objects.equals(function, other.function) && Objects.equals(referenceHierarchy, other.referenceHierarchy);
    }

    @Override
    public String toString() {
      return "VertexKey [function=" + function + ", referenceHierarchy=" + referenceHierarchy + "]";
    }
  }

  public static interface Element {
  }

  public static class Vertex extends VertexKey implements Element {
    protected List<Edge> outgoingEdges;
    protected List<Edge> incomingEdges;

    public Vertex(FunctionalChainInvolvementFunction function, List<FunctionalChainReference> referenceHierarchy) {
      super(function, referenceHierarchy);
      this.outgoingEdges = new ArrayList<>();
      this.incomingEdges = new ArrayList<>();
    }

    public List<Edge> getOutgoingEdges() {
      return outgoingEdges;
    }

    public List<Edge> getIncomingEdges() {
      return incomingEdges;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(outgoingEdges);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!super.equals(obj)) {
        return false;
      }
      if (!(obj instanceof Vertex)) {
        return false;
      }
      Vertex other = (Vertex) obj;
      return Objects.equals(outgoingEdges, other.outgoingEdges);
    }

    @Override
    public String toString() {
      return "Vertex [function=" + function + ", referenceHierarchy=" + referenceHierarchy + "]";
    }

  }

  public static class Edge implements Element {
    protected FunctionalChainInvolvementLink link;
    protected Vertex source;
    protected Vertex target;

    public FunctionalChainInvolvementLink getLink() {
      return link;
    }

    public Vertex getSource() {
      return source;
    }

    public Vertex getTarget() {
      return target;
    }

    public Edge(FunctionalChainInvolvementLink link, Vertex source, Vertex target) {
      this.link = link;
      this.source = source;
      this.target = target;
    }

    @Override
    public String toString() {
      return "Edge [link=" + link + ", source=" + source + ", target=" + target + "]";
    }
  }

}
