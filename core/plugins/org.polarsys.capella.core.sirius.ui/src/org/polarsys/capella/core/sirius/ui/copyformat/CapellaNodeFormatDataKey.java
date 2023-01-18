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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;

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
   *            The key
   */
  public CapellaNodeFormatDataKey(DSemanticDecorator inputDecorator, AbstractCapellaFormatDataKey key) {
    super(key);
    decorator = inputDecorator;

    EObject parent = getRelatedParent(decorator);
    EObject parentSemanticElement = null;

    if ((decorator == null) || (decorator instanceof AbstractDNode)) {
      addDecoration(DiagramPackage.Literals.ABSTRACT_DNODE);
    }
    if ((decorator != null) && (decorator.getTarget() != null)) {
      EObject semanticElement = decorator.getTarget();
      if (semanticElement instanceof ExchangeCategory) {
        if (parent != null)
          addDecoration(parent);
        //This should not work with copyFormat between phases
        addDecoration(((DDiagramElement) decorator).getTarget());
        addDecoration(((DDiagramElement) decorator.eContainer()).getTarget());
        StyleDescription desc = ((DDiagramElement) decorator).getStyle().getDescription();
        if (desc instanceof SquareDescription) {
          SquareDescription sd = (SquareDescription) desc;
          ColorDescription color = sd.getColor();
          super.addDecoration(color);
        }
      } else if (semanticElement instanceof FunctionalChainInvolvementFunction) {
        FunctionalChainInvolvementFunction fcif = (FunctionalChainInvolvementFunction) semanticElement;
        AbstractFunction involved = (AbstractFunction) fcif.getInvolved();

        // Add the involved function as a decorator
        addDecoration(involved);

        for (FunctionalChain involvingFC : involved.getInvolvingFunctionalChains()) {
          if (!decorations.contains(involvingFC))
            addDecoration(involvingFC);

        }

      }

      else if (semanticElement instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement ppi = (PhysicalPathInvolvement) semanticElement;
        InvolvedElement involved = (InvolvedElement) ppi.getInvolved();

        // Add the involved element as a decorator
        addDecoration(involved);

      }

      else if (semanticElement instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) semanticElement;
        addDecoration(function);
        for (FunctionalChain involvingFC : function.getInvolvingFunctionalChains()) {
          if (!decorations.contains(involvingFC))
            addDecoration(involvingFC);

        }

      } else if (semanticElement instanceof FunctionalChain) {
        if (!(decorator instanceof DNodeContainer)) {
          // If not a DNodeContainer, the decorator represents the square FC
          addDecoration(DiagramPackage.Literals.DNODE);
        }
      }

      else {
        if (parent != null)
          // Add decoration on parent
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
