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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.dnd.DND;

/**
 * Encapsulate basic constraints for a drag/drop operation.
 */
public class BasicDropConstraints {

  /**
   * Returns whether the drop is a COPY or not
   */
  protected boolean isCopy(int operation_p) {
    return operation_p == DND.DROP_COPY;
  }

  /**
   * Returns whether the element can be drop inside one of dragged elements
   */
  protected boolean allowInside(int operation_p) {
    return true;
  }

  /**
   * Returns whether the element can be drop inside one of parent of dragged elements
   */
  protected boolean allowInsideParent(int operation_p) {
    return true;
  }

  /**
   * Checks if basic drop constraints are satisfied:
   * 
   * - If the drop is not a copy drop, one cannot drop an element onto itself or its direct parent
   * - One can never drop an element onto one of its children
   * 
   * @param dragged_p elements being dragged
   * @param target_p the current drop target
   * @param operation_p the DND operation (copy, move, etc).
   * @return true if basic constraints are satisfied, false otherwise.
   */
  public boolean canDrop(List<EObject> dragged_p, EObject target_p, int operation_p) {
    boolean isCopy = isCopy(operation_p);
    boolean allowInside = allowInside(operation_p);
    boolean allowInsideParent = allowInsideParent(operation_p);

    for (EObject dragged : dragged_p) {

      // drag an element on itself is ok if we have copy semantics
      if (dragged == target_p) {
        if (!isCopy || !allowInside) {
          return false;
        }
      }

      // drag an element on its parent is ok if we have copy semantics
      if (dragged.eContainer() == target_p) {
        if (!isCopy || !allowInsideParent) {
          return false;
        }
      }

      // dragging an element onto one of its children is confusing,
      // we disallow this. even if it might theoretically be
      // valid in a copy operation. 
      EObject container = target_p.eContainer();
      while (container != null) {
        if (dragged == container) {
          return false;
        }
        container = container.eContainer();
      }
    }
    return true;
  }
}
