/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;

public class InvolvementHierarchyContext {

  private List<FunctionalChainReference> hierarchyContext;
  private EdgeTarget edgeTarget;

  public InvolvementHierarchyContext(EdgeTarget edgeTarget) {
    this.edgeTarget = edgeTarget;
    this.hierarchyContext = computeHierarchyContext(edgeTarget);
  }

  protected InvolvementHierarchyContext(EdgeTarget edgeTarget, List<FunctionalChainReference> hierarchyContext) {
    this.edgeTarget = edgeTarget;
    this.hierarchyContext = hierarchyContext;
  }

  /**
   * Computes the Functional Chain Reference hierarchy. The hierarchy is maintained in a bottom to top fashion, meaning
   * that the first element is the immediate Functional Chain Reference, and the last element is the root Functional
   * Chain.
   * 
   * @param edgeTarget
   *          the edge target.
   * @return the Functional Chain Reference hierarchy.
   */
  private List<FunctionalChainReference> computeHierarchyContext(EdgeTarget edgeTarget) {

    List<FunctionalChainReference> referenceHierarchy = new ArrayList<>();
    DSemanticDecorator container = (DSemanticDecorator) edgeTarget.eContainer();

    while (container != null) {

      if (container.getTarget() instanceof FunctionalChainReference) {
        FunctionalChainReference functionalChainReference = (FunctionalChainReference) container.getTarget();
        referenceHierarchy.add(functionalChainReference);
      }

      container = (DSemanticDecorator) container.eContainer();
    }

    return referenceHierarchy;
  }

  /**
   * Returns the parent hierarchy context of the current hierarchy context.
   * 
   * @return the parent hierarchy context of the current hierarchy context.
   */
  public InvolvementHierarchyContext getParentHierarchyContext() {

    List<FunctionalChainReference> parentHierarchy = Collections.emptyList();
    int depth = this.getDepth();

    if (depth != 0) {
      parentHierarchy = hierarchyContext.subList(1, depth);
    }

    return new InvolvementHierarchyContext(edgeTarget, parentHierarchy);
  }

  /**
   * Returns a normalized version of the current context in regards to the already normalized context, that has the same
   * depth.
   * 
   * Example: current context -> [A, B, C, D], normalized context -> [X, Y], current context normalized-> [C, D]
   * 
   * @param targetContext
   *          the normalized context.
   * @return a normalized version of the current context in regards to the already normalized context.
   */
  public InvolvementHierarchyContext normalize(InvolvementHierarchyContext targetContext) {

    int currentDepth = this.getDepth();
    int targetContextDepth = targetContext.getDepth();
    int deltaDepth = currentDepth - targetContextDepth;

    if (deltaDepth > 0) {
      List<FunctionalChainReference> normalizedHierarchyList = this.hierarchyContext.subList(deltaDepth, currentDepth);
      return new InvolvementHierarchyContext(edgeTarget, normalizedHierarchyList);
    }

    return this;
  }

  /**
   * Extracts the common hierarchy between the current and the other context.
   * 
   * Example: current context -> [A, B, C, D, E], other context -> [A, B, X, D, E], common context -> [D, E]
   * 
   * @param other
   *          the other context.
   * @return the common hierarchy between the current and the other context.
   */
  protected InvolvementHierarchyContext extractCommonContext(InvolvementHierarchyContext other) {
    InvolvementHierarchyContext currentHierarchyContex = this;
    InvolvementHierarchyContext otherHierarchyContext = other;

    int thisDepth = currentHierarchyContex.getDepth();
    int otherDepth = otherHierarchyContext.getDepth();

    if (thisDepth > otherDepth) {
      currentHierarchyContex = currentHierarchyContex.normalize(otherHierarchyContext);
    } else if (otherDepth > thisDepth) {
      otherHierarchyContext = otherHierarchyContext.normalize(currentHierarchyContex);
    }

    while (!currentHierarchyContex.equals(otherHierarchyContext)) {

      if (!currentHierarchyContex.isEmpty()) {
        currentHierarchyContex = currentHierarchyContex.getParentHierarchyContext();
      }

      if (!otherHierarchyContext.isEmpty()) {
        otherHierarchyContext = otherHierarchyContext.getParentHierarchyContext();
      }
    }

    return new InvolvementHierarchyContext(null, currentHierarchyContex.hierarchyContext);
  }

  /**
   * Extracts the common functionalChain between the current and the other context. The common functional chain is the
   * first functional chain in the common hierarchy, or is the root functional chain otherwise.
   * 
   * @param other
   *          the other context.
   * @return
   */
  public FunctionalChain extractCommonFunctionalChain(InvolvementHierarchyContext other) {

    InvolvementHierarchyContext commonHierarchy = this.extractCommonContext(other);

    // no common hierarchy
    if (commonHierarchy.isEmpty()) {
      return getRootFunctionalChain();
    }

    // a common hierarchy exists -> take the referenced chain
    return getFunctionalChainOfFirstReference(commonHierarchy);
  }

  public int getDepth() {
    return hierarchyContext.size();
  }

  public boolean isEmpty() {
    return hierarchyContext.isEmpty();
  }

  public List<FunctionalChainReference> getReferences() {
    return hierarchyContext;
  }

  public List<FunctionalChainReference> getReferences(FunctionalChain endFunctionalChain) {

    List<FunctionalChainReference> references = new ArrayList<>();

    for (FunctionalChainReference reference : hierarchyContext) {
      if (reference.getReferencedFunctionalChain() == endFunctionalChain) {
        break;
      }

      references.add(reference);
    }

    return references;
  }

  private FunctionalChain getFunctionalChainOfFirstReference(InvolvementHierarchyContext commonHierarchy) {
    return commonHierarchy.hierarchyContext.get(0).getReferencedFunctionalChain();
  }

  private FunctionalChain getRootFunctionalChain() {

    if (hierarchyContext.isEmpty()) {
      // no hierarchy context exists -> the container of the edgeTarget is the common functional chain
      DSemanticDecorator container = (DSemanticDecorator) edgeTarget.eContainer();
      return (FunctionalChain) container.getTarget();
    }

    // a hierarchy context exists -> take the container of the last reference
    FunctionalChainReference lastHierarchyMember = this.hierarchyContext.get(this.getDepth() - 1);
    return (FunctionalChain) lastHierarchyMember.eContainer();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hierarchyContext == null) ? 0 : hierarchyContext.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof InvolvementHierarchyContext)) {
      return false;
    }
    InvolvementHierarchyContext other = (InvolvementHierarchyContext) obj;
    if (hierarchyContext == null) {
      if (other.hierarchyContext != null) {
        return false;
      }
    } else if (!hierarchyContext.equals(other.hierarchyContext)) {
      return false;
    }
    return true;
  }

}
