/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Specific key for {@link DNode}.
 */
public class CapellaNodeFormatDataKey extends CapellaDecoratorFormatDataKey {

  DSemanticDecorator decorator;

  /**
   * @return the decorator
   */
  public DSemanticDecorator getDecorator() {
    return decorator;
  }

  /**
   * Default constructor.
   * 
   * @param key
   *          The key
   */
  public CapellaNodeFormatDataKey(DSemanticDecorator inputDecorator, AbstractCapellaFormatDataKey key) {
    super(key);
    decorator = inputDecorator;

    EObject parent = getRelatedParent(decorator);

    if ((decorator == null) || (decorator instanceof AbstractDNode)) {
      addDecoration(DiagramPackage.Literals.ABSTRACT_DNODE);
    }

    if ((decorator != null) && (decorator.getTarget() != null)) {
      EObject semanticElement = decorator.getTarget();

      // An exchangeCategory is displayed as a link and two colored ports.
      // It is possible to have on a function, two ports of the same category with differents colors, so we add the
      // color as differenciator
      if (semanticElement instanceof ExchangeCategory) {
        if (parent != null) {
          addDecoration(parent);
        }
        // This should not work with copyFormat between phases
        addDecoration(((DDiagramElement) decorator).getTarget());
        addDecoration(((DDiagramElement) decorator.eContainer()).getTarget());
        StyleDescription desc = ((DDiagramElement) decorator).getStyle().getDescription();
        if (desc instanceof SquareDescription) {
          SquareDescription sd = (SquareDescription) desc;
          ColorDescription color = sd.getColor();
          super.addDecoration(color);
        }

      } else if (semanticElement instanceof FunctionalChainInvolvement
          && FunctionalChainServices.getFunctionalChainServices().isFCRegion(decorator.eContainer())) {
        // If it is an involvement owned by a FunctionalChainReference in a FCD, then we add the hierarchy to be able to distingish 
        // FCR in the same diagram
        FunctionalChain mainChain = (FunctionalChain) ((DSemanticDiagram) ((DDiagramElement) decorator)
            .getParentDiagram()).getTarget();
        addDecoration(mainChain);
        List<FunctionalChainReference> references = FunctionalChainServices.getFunctionalChainServices()
            .computeFCReferenceHierarchy((EdgeTarget) decorator, mainChain);
        for (FunctionalChainReference ref : references) {
          addDecoration(ref);
        }

      } else if (parent != null && !(parent instanceof DDiagram)) {
        // Add decoration on parent. Mostly used when an element is displayed several times. 
        // (e.g. multipart xAB displaying several times same function in one diagram)
        addDecoration(parent);
      }

    }

  }

  /**
   * @param decorator
   * @return
   */
  protected EObject getRelatedParent(DSemanticDecorator inputDecorator) {
    if (inputDecorator == null) {
      return null;
    }
    return inputDecorator.eContainer();
  }

  /**
   * @param object
   */
  @Override
  protected void addDecoration(EObject object) {

    if (object != null) {

      if (object instanceof DSemanticDecorator) {
        DSemanticDecorator sourceDecorator = (DSemanticDecorator) object;
        if (sourceDecorator.getTarget() != null) {
          addDecoration(sourceDecorator.getTarget());
        }

      } else {
        super.addDecoration(object);
      }

    } else {
      super.addDecoration(object);
    }
  }
}
