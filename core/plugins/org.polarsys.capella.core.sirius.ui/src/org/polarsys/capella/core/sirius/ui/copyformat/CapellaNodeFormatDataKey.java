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
package org.polarsys.capella.core.sirius.ui.copyformat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.polarsys.capella.core.data.fa.ExchangeCategory;

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

    //Add decoration on parent
    EObject parent = getRelatedParent(inputDecorator);
    if (parent != null) {
      addDecoration(parent);
    }

    if ((inputDecorator == null) || (inputDecorator instanceof AbstractDNode)) {
      addDecoration(DiagramPackage.Literals.ABSTRACT_DNODE);
    }
    if ((inputDecorator != null) && (inputDecorator.getTarget() != null)) {
      if (inputDecorator.getTarget() instanceof ExchangeCategory) {
        //This should not work with copyFormat between phases
        addDecoration(((DDiagramElement) inputDecorator).getTarget());
        addDecoration(((DDiagramElement) inputDecorator.eContainer()).getTarget());
        StyleDescription desc = ((DDiagramElement) inputDecorator).getStyle().getDescription();
        if (desc instanceof SquareDescription) {
          SquareDescription sd = (SquareDescription) desc;
          ColorDescription color = sd.getColor();
          super.addDecoration(color);
        }
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
