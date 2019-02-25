/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.helpers;

import java.util.List;

import org.eclipse.sirius.diagram.EdgeTarget;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;

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

}
