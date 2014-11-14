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
package org.polarsys.capella.core.sirius.ui.copylayout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import org.polarsys.capella.core.data.fa.ExchangeCategory;

/**
 * Specific key for {@link DNode}.
 */
public class CapellaNodeLayoutDataKey extends CapellaDecoratorLayoutDataKey {

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
  public CapellaNodeLayoutDataKey(DSemanticDecorator decorator_p, AbstractCapellaLayoutDataKey key_p) {
    super(key_p);
    decorator = decorator_p;

    //Add decoration on parent
    EObject parent = getRelatedParent(decorator_p);
    if (parent != null) {
      addDecoration(parent);
    }

    if ((decorator_p == null) || (decorator_p instanceof AbstractDNode)) {
      addDecoration(ViewpointPackage.Literals.ABSTRACT_DNODE);
    }
    if ((decorator_p != null) && (decorator_p.getTarget() != null)) {
      if (decorator_p.getTarget() instanceof ExchangeCategory) {
        //This should not work with copyLayout between phases
        addDecoration(((DDiagramElement) decorator_p).getTarget());
        addDecoration(((DDiagramElement) decorator_p.eContainer()).getTarget());
        StyleDescription desc = ((DDiagramElement) decorator_p).getStyle().getDescription();
        if (desc instanceof SquareDescription) {
          SquareDescription sd = (SquareDescription) desc;
          ColorDescription color = sd.getColor();
          super.addDecoration(color);
        }
      }

    }
  }

  /**
   * @param decorator_p
   * @return
   */
  protected EObject getRelatedParent(DSemanticDecorator decorator_p) {
    if (decorator_p == null) {
      return null;
    }
    return decorator_p.eContainer();
  }

  /**
   * @param sourceNode_p
   */
  @Override
  protected void addDecoration(EObject object_p) {

    if (object_p != null) {

      if (object_p instanceof DSemanticDecorator) {
        DSemanticDecorator sourceDecorator = (DSemanticDecorator) object_p;
        if (sourceDecorator.getTarget() != null) {
          addDecoration(sourceDecorator.getTarget());
        }

      } else {
        super.addDecoration(object_p);
      }

    } else {
      super.addDecoration(object_p);
    }
  }
}
