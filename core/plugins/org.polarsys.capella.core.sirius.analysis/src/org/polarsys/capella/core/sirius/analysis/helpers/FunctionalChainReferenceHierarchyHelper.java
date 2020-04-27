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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class FunctionalChainReferenceHierarchyHelper {

  private FunctionalChainReferenceHierarchyHelper() {
    // private constructor
  }

  /**
   * Computes the Functional Chain Reference hierarchy until reaching the endFunctionalChain. The hierarchy is
   * maintained in a bottom to top fashion, meaning that the first element is the immediate Functional Chain Reference,
   * and the last element is the Functional Chain Reference contained by the endFunctionalChain.
   * 
   * @param view
   *          the selected view.
   * 
   * @param endFunctionalChain
   *          the end point of the hierarchy
   * @return the Functional Chain Reference hierarchy.
   */
  public static List<FunctionalChainReference> computeHierarchy(EdgeTarget edgeTarget,
      FunctionalChain endFunctionalChain) {

    return new InvolvementHierarchyContext(edgeTarget).getReferences(endFunctionalChain);
  }

  /**
   * Computes the Functional Chain Reference hierarchy. The hierarchy is maintained in a bottom to top fashion, meaning
   * that the first element is the immediate Functional Chain Reference.
   * 
   * @param view
   *          the selected view.
   * 
   * @param endFunctionalChain
   *          the end point of the hierarchy
   * @return the Functional Chain Reference hierarchy.
   */
  public static List<FunctionalChainReference> computeHierarchy(EdgeTarget edgeTarget) {
    return new InvolvementHierarchyContext(edgeTarget).getReferences();
  }

  /**
   * Computes the Functional Chain that will serve as container for the new edge.
   * 
   * @param sourceView
   *          the source view
   * @param targetView
   *          the target view
   * @return the Functional Chain that will serve as container for the new edge.
   */
  public static FunctionalChain computeContainerFunctionalChain(EdgeTarget sourceView, EdgeTarget targetView) {

    InvolvementHierarchyContext sourceHierarchyContext = new InvolvementHierarchyContext(sourceView);
    InvolvementHierarchyContext targetHierarchyContext = new InvolvementHierarchyContext(targetView);

    return sourceHierarchyContext.extractCommonFunctionalChain(targetHierarchyContext);
  }

  /**
   * From a DNode (FCIF) or DNodeContainer (FCR) get its upper container whose target is at the top of the reference
   * hierarchy. If the reference hierarchy is empty and diagram element is DNode, return it.
   * 
   * If there is no container, return null.
   * 
   * @param referenceHierarchy
   *          the reference hierarchy
   * @param startingDiagramElement
   *          Should be the DNode (FCIF) or DNodeContainer (FCR)
   * @return the parent of the reference hierarchy for a valid hierarchy, an empty optional otherwise.
   */
  public static EObject getDiagramElementForTopHierarchy(List<FunctionalChainReference> referenceHierarchy,
      DDiagramElement startingDiagramElement) {

    List<EObject> semanticHierarchy = new ArrayList<>(referenceHierarchy);

    EObject diagramElementTarget = startingDiagramElement.getTarget();

    if (startingDiagramElement instanceof DNode && diagramElementTarget instanceof SequenceLinkEnd) {
      semanticHierarchy.add(0, diagramElementTarget);
    }

    int startIndex = semanticHierarchy.indexOf(diagramElementTarget);
    if (startIndex == -1) {
      return null;
    }

    DDiagramElement topContainer = startingDiagramElement;

    for (int i = startIndex; i < semanticHierarchy.size() - 1; i++) {
      DDiagramElement upperContainer = (DDiagramElement) topContainer.eContainer().eContainer();
      if (upperContainer == null) {
        return null;
      }

      EObject nextSemanticInHierarchy = semanticHierarchy.get(i + 1);
      if (!nextSemanticInHierarchy.equals(upperContainer.getTarget())) {
        return null;
      }

      topContainer = upperContainer;
    }

    return topContainer;
  }

}
